package com.michaellouie.healthcheckservice.model;

import org.springframework.core.style.ToStringCreator;

import java.util.Date;

/**
 * A generic status response returned to caller.
 */
public class StatusResponse {

    // Use this for other purposes aside from storing when health check was run or request sent.
    // i.e. use date to be more informative
    private Date date;

    public StatusResponse() { this.date = new Date(); }

    public StatusResponse(Date date) { this.date = date; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("date", this.date)
                .toString();
    }
}
