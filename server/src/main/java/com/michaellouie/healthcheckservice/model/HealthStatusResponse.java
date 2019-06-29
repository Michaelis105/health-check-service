package com.michaellouie.healthcheckservice.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Standardized health status as response to caller (e.g. dashboard)
 */
public class HealthStatusResponse extends StatusResponse {

    private List<HealthStatus> healthStatuses;

    public HealthStatusResponse(List<HealthStatus> healthStatuses) {
        super();
        this.healthStatuses = healthStatuses;
    }

    public HealthStatusResponse(HealthStatus healthStatus) {
        super();
        this.healthStatuses = new LinkedList<>();
        this.healthStatuses.add(healthStatus);
    }

    public List<HealthStatus> getHealthStatuses() { return healthStatuses; }

    public void setHealthStatuses(List<HealthStatus> healthStatuses) { this.healthStatuses = healthStatuses; }
}
