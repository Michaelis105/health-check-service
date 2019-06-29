package com.michaellouie.healthcheckservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import com.michaellouie.healthcheckservice.Constants;
import com.michaellouie.healthcheckservice.Utils;
import com.michaellouie.healthcheckservice.model.HealthStatus;
import com.michaellouie.healthcheckservice.model.HealthStatusResponse;
import com.michaellouie.healthcheckservice.model.StatusResponse;
import com.michaellouie.healthcheckservice.model.WebService;
import com.michaellouie.healthcheckservice.spring.configuration.YamlProperties;

import static com.michaellouie.healthcheckservice.model.HealthStatus.HealthCondition.*;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {

    @Autowired
    private YamlProperties serviceConfig;

    private final Logger _log = LoggerFactory.getLogger(HealthCheckServiceImpl.class);

    // TODO: Externalize to config, use Spring Trigger to dynamically set next execution time
    private final int syncRateInSeconds = 60;


    @Override
    @Scheduled(fixedRate = syncRateInSeconds*1000)
    @CacheEvict(value = {"serviceStatuses"}, allEntries = true)
    public StatusResponse syncHealthStatuses() {
        getHealthStatusAll();

        Date curDate = new Date();
        StringBuffer syncSb = new StringBuffer();
        syncSb.append("----------------------------");
        syncSb.append("Synced health statuses at ");
        syncSb.append(curDate);
        syncSb.append("----------------------------");
        _log.debug(syncSb.toString());

        return new StatusResponse(curDate);
    }

    @Override
    public HealthStatusResponse getHealthStatusByServiceName(String serviceName) {
        _log.trace("Enter with service: " + serviceName);

        Set<String> suppresed = serviceConfig.getSuppress();
        if (suppresed != null && suppresed.contains(serviceName)) {
            return new HealthStatusResponse(new HealthStatus(serviceName, UNIMPLEMENTED, "Suppressed"));
        }

        String serviceHealthCheckUrl = getServiceUrl(serviceName);
        String msg = null;
        HealthStatus.HealthCondition healthCondition = UNKNOWN;
        if (StringUtils.isEmpty(serviceHealthCheckUrl)) {
            msg = "Could not find URL for service name " + serviceName;
        } else if (Constants.NULL.equals(serviceHealthCheckUrl)) {
            msg = "Failed to find service by name of " + serviceName;
        } else if (Constants.UNDEFINED.equals(serviceHealthCheckUrl)) {
            msg = "Undefined health check URL for service name " + serviceName;
        }

        // Catch all getServiceUrl errors
        if (!StringUtils.isEmpty(msg)) {
            _log.debug(msg);
            return new HealthStatusResponse(new HealthStatus(serviceName, healthCondition, msg));
        }

        String response;
        try {
            response = (serviceHealthCheckUrl.contains("http") ?
                    Utils.doHttpGet(serviceHealthCheckUrl, serviceConfig.getHealthCheckTimeoutThreshold()) :
                    Utils.doHttpsGet(serviceHealthCheckUrl, serviceConfig.getHealthCheckTimeoutThreshold()));
        } catch (Exception e) {
            String error = "Exception retrieving status of service " + serviceName + " at URL " + serviceHealthCheckUrl;
            _log.error(error, e);
            return new HealthStatusResponse(new HealthStatus(serviceName, CRITICAL, error));
        }

        _log.error("Exit...");
        return new HealthStatusResponse(interpretServiceHealth(serviceName, response));
    }

    @Override
    @Cacheable("serviceStatuses")
    public HealthStatusResponse getHealthStatusAll() {
        _log.trace("Enter...");

        List<HealthStatus> healthStatusList = new LinkedList<>();
        for (WebService webService : serviceConfig.getServices().values()) {
            healthStatusList.addAll(getHealthStatusByServiceName(webService.getName()).getHealthStatuses());
        }

        Collections.sort(healthStatusList);

        _log.trace("Exit...");
        return new HealthStatusResponse(healthStatusList);
    }

    private String getServiceUrl(String serviceName) {
        _log.trace("Enter with service name: " + serviceName);

        WebService webService = serviceConfig.getServiceByName(serviceName);
        if (null == webService) return Constants.NULL;
        else if (Constants.UNDEFINED.equals(webService.getProtocol()) || Constants.UNDEFINED.equals(webService.getSubdomain())
            || Constants.UNDEFINED.equals(webService.getEndpoint())) {
            return Constants.UNDEFINED;
        }

        StringBuffer serviceUrlSb = new StringBuffer();
        serviceUrlSb.append(webService.getProtocol());
        serviceUrlSb.append("://");
        serviceUrlSb.append(webService.getSubdomain());
        serviceUrlSb.append(serviceConfig.getDomain());
        serviceUrlSb.append(webService.getEndpoint());

        _log.trace("Exit with service URL: " + serviceUrlSb.toString());
        return serviceUrlSb.toString();
    }

    private HealthStatus interpretServiceHealth(String serviceName, String responsePayload) {
        _log.trace("Enter with service name: " + serviceName);

        HealthStatus healthStatus = new HealthStatus(serviceName);

        // To handle JSON responses
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        // Implement to handle each additional service in switch here.
        switch (serviceName) {
            case "someSampleService":
                healthStatus.setHealthStatus(responsePayload.contains("HEALTHY") ? HEALTHY : CRITICAL);
                break;
            default:
                // Caller passed in unrecognized service name.
                // Caller should check service name or ensure a corresponding interpreter is listed in this method.
                String error = "Failed to find matching health interpreter for service " + serviceName;
                _log.error(error);
                healthStatus.setHealthStatus(UNKNOWN);
                healthStatus.addReason(error);
        }

        _log.trace("Exit with status: " + healthStatus.getHealthStatus());
        return healthStatus;
    }
}
