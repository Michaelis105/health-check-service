package com.michaellouie.healthcheckservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Routes to heath check dashboard or other miscellaneous services
 */
@Controller
@Api(description = "Set of endpoints for viewing health check dashboards or other webpages")
public class WebpageController {

    private final Logger _log = LoggerFactory.getLogger(WebpageController.class);

    String dashboardPageFilename = "monitorDashboard.html";

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    @ApiOperation(value = "Renders dashboard for health checking all services")
    public String dashboard() {
        _log.trace("Retrieving dashboard...");
        return dashboardPageFilename;
    }
}
