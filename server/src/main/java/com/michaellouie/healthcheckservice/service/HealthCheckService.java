package com.michaellouie.healthcheckservice.service;

import com.michaellouie.healthcheckservice.model.HealthStatusResponse;
import com.michaellouie.healthcheckservice.model.StatusResponse;

public interface HealthCheckService {

    StatusResponse syncHealthStatuses();
    HealthStatusResponse getHealthStatusByServiceName(String serviceName);
    HealthStatusResponse getHealthStatusAll();

}
