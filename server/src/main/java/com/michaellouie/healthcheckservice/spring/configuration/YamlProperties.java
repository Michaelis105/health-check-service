package com.michaellouie.healthcheckservice.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.style.ToStringCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.michaellouie.healthcheckservice.Constants;
import com.michaellouie.healthcheckservice.model.WebService;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YamlProperties {

    private int healthCheckTimeoutThreshold;
    private String domain;
    private Map<String, WebService> services;
    private Set<String> suppress;

    public int getHealthCheckTimeoutThreshold() { return healthCheckTimeoutThreshold; }
    public void setHealthCheckTimeoutThreshold(int healthCheckTimeoutThreshold) { this.healthCheckTimeoutThreshold = healthCheckTimeoutThreshold; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public Map<String, WebService> getServices() { return services; }
    public void setServices(List<WebService> services) {
        this.services = new HashMap<>();
        for (WebService webService : services) {
            if (!Constants.UNDEFINED.equals(webService.getName())) {
                this.services.put(webService.getName(), webService);
            }
        }
    }

    public WebService getServiceByName(String serviceName) { return services.get(serviceName); }

    public Set<String> getSuppress() { return suppress; }

    public void setSuppress(Set<String> suppress) { this.suppress = suppress; }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("healthCheckTimeoutThreshold", this.healthCheckTimeoutThreshold)
                .append("domain", this.domain)
                .append("services", this.services)
                .toString();
    }


}
