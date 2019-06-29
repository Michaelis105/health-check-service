package com.michaellouie.healthcheckservice.model.servicestatus;

import java.util.LinkedList;
import java.util.List;

/**
 * Generic health status representative of health status reported by some service.
 *
 * Extend this class to wrap around health status sent by a specific service
 * since not all services are expected to return the same formatted health status response.
 */
public abstract class GenericHealthStatus {

    String healthStatus;    // Healthy, Unhealthy, Critical
    List<String> reasons;   // Detailed explanation reflecting specific status code
    String lastCheckDate;   // Date health check waqs previously run
    String dateInit;        // Date web service was started/initialized

    public GenericHealthStatus() { reasons = new LinkedList<>(); }

    public String getHealthStatus() { return healthStatus; }

    public void setHealthStatus(String healthStatus) { this.healthStatus = healthStatus; }

    public List<String> getReasons() { return reasons; }

    public void setReasons(List<String> reasons) { this.reasons = reasons; }

    public void addReason(String reason) { reasons.add(reason); }

    public String getLastCheckDate() { return lastCheckDate; }

    public void setLastCheckDate(String lastCheckDate) { this.lastCheckDate = lastCheckDate; }

    public String getDateInit() { return dateInit; }

    public void setDateInit(String dateInit) { this.dateInit = dateInit; }

}
