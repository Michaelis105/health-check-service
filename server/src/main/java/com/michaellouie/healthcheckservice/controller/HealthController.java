package com.michaellouie.healthcheckservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.michaellouie.healthcheckservice.model.HealthStatusResponse;
import com.michaellouie.healthcheckservice.model.StatusResponse;
import com.michaellouie.healthcheckservice.service.HealthCheckService;

/**
 * Endpoints for various health check APIs
 */
@RestController
@RequestMapping("api")
@Api(description = "Set of endpoints for obtaining various services' health statuses")
public class HealthController {

    private final Logger _log = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    HealthCheckService healthCheckService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/forcedHealthCheckSync", method = RequestMethod.GET)
    @ApiOperation(value = "Force evict health check statuses in cache and update with latest service health check information.")
    public StatusResponse forcedHealthCheckSync() {
        _log.trace("Enter...");
        StatusResponse statusResponse = healthCheckService.syncHealthStatuses();
        _log.trace("Exit...");
        return statusResponse;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/healthCheckByServiceName", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves health check status for a specific service.")
    public HealthStatusResponse healthCheckByServiceName(
            @ApiParam(name =  "servicename", value = "${swagger.controller.serviceNameDescription}")
            @RequestParam(value = "servicename", required = false) String serviceName) {
        _log.trace("Enter with servicename: " + serviceName);
        HealthStatusResponse healthStatusResponse = healthCheckService.getHealthStatusByServiceName(serviceName);
        _log.trace("Exit...");
        return healthStatusResponse;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/healthCheckAll", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves health check statuses for all known services.")
    public HealthStatusResponse healthCheckAll() {
        _log.trace("Enter...");
        HealthStatusResponse healthStatusResponses = healthCheckService.getHealthStatusAll();
        _log.trace("Exit...");
        return healthStatusResponses;
    }
}