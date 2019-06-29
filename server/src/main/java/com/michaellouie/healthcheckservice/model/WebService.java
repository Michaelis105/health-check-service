package com.michaellouie.healthcheckservice.model;

import org.springframework.core.style.ToStringCreator;

/**
 * Wrapper representing specific URL and service attributes to identify health check endpoint.
 * Intended to be used for configuration purposes only.
 */
public class WebService {

    private String name;
    private String protocol;
    private String subdomain;
    private String endpoint;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getProtocol() { return protocol; }

    public void setProtocol(String protocol) { this.protocol = protocol; }

    public String getSubdomain() { return subdomain; }

    public void setSubdomain(String subdomain) { this.subdomain = subdomain; }

    public String getEndpoint() { return endpoint; }

    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("name", this.name)
                .append("protocol", this.protocol)
                .append("subdomain", this.subdomain)
                .append("endpoint", this.endpoint)
                .toString();
    }
}
