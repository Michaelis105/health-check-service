package com.michaellouie.healthcheckservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Routes to heath check dashboard or other miscellaneous services
 */
@Controller
@RequestMapping("/")
public class WebpageController {

    private final Logger _log = LoggerFactory.getLogger(WebpageController.class);

    String dashboardPageFilename = "index";

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET, produces = "text/html")
    public String dashboard() {
        _log.trace("Retrieving dashboard...");
        return dashboardPageFilename;
    }
}
