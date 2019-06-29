package com.michaellouie.healthcheckservice.model;

import org.springframework.core.style.ToStringCreator;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Standardized health status
 */
public class HealthStatus implements Comparable<HealthStatus> {

    public enum HealthCondition {
        HEALTHY ("Healthy"),
        UNHEALTHY ("Unhealthy"),
        CRITICAL("Critical"),
        UNKNOWN("Unknown"),
        UNIMPLEMENTED("Unimplemented"); // Placeholder - not intended to be actual health condition

        String healthName;
        HealthCondition(String healthName) { this.healthName = healthName; }
        String getHealthName() { return healthName; }
    }

    private String serviceName;
    private String healthStatus;    // Healthy, Unhealthy, Critical
    private List<String> reasons;   // Deatiled explanation reflecting specific status

    public HealthStatus(String serviceName) {
        this.serviceName = serviceName;
        this.setHealthStatus(HealthCondition.UNKNOWN);
        reasons = new LinkedList<>();
    }

    public HealthStatus(String serviceName, String healthStatus, List<String> reasons) {
        this.serviceName = serviceName;
        setHealthStatus(healthStatus);
        this.reasons = reasons;
    }

    public HealthStatus(String serviceName, HealthCondition healthCondition, String reason) {
        this(serviceName);
        setHealthStatus(healthCondition);
        addReason(reason);
    }

    public String getServiceName() { return serviceName; }

    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getHealthStatus() { return healthStatus; }

    public void setHealthStatus(String healthStatus) {
        for (HealthCondition healthCondition : HealthCondition.values()) {
            if (healthCondition.healthName.equals(healthStatus)) {
                this.healthStatus = healthStatus;
                break;
            }
        }
    }

    public void setHealthStatus(HealthCondition healthCondition) { this.healthStatus = healthCondition.getHealthName(); }

    public List<String> getReasons() { return reasons; }

    public void setReasons(List<String> reasons) { this.reasons = reasons; }

    public void addReason(String reason) { reasons.add(reason); }

    @Override
    public int compareTo(HealthStatus healthStatus) {
        if (healthStatus == null) return 1;
        else {
            if (StringUtils.isEmpty(this.healthStatus) && StringUtils.isEmpty(healthStatus.serviceName)) return 0;
            else if (StringUtils.isEmpty(this.healthStatus)) return -1;
            return this.healthStatus.compareTo(healthStatus.healthStatus);
        }
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("serviceName", this.serviceName)
                .append("healthStatus", this.healthStatus)
                .append("reasons", this.reasons)
                .toString();
    }
}
