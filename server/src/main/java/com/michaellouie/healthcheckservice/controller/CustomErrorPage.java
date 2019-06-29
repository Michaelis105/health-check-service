package com.michaellouie.healthcheckservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * Routes all requests with errors to respective paths.
 */
@Controller
@ControllerAdvice
public class CustomErrorPage implements ErrorController {

    private final Logger _log = LoggerFactory.getLogger(CustomErrorPage.class);

    @ExceptionHandler({
            Exception.class
    })
    public final ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        _log.error("Exception", ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getErrorPath() {
        _log.error("Redirect to error");
        return "error";
    }

    @RequestMapping("/error")
    public String handleError() {
        _log.error("Error out for some reason...");
        return "error";
    }

}
