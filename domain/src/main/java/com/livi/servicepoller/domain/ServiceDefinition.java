package com.livi.servicepoller.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.Validate;

/**
 * This entity represents the definition of a service.
 * A service is unique regarding its url.
 */
@EqualsAndHashCode
@ToString
public final class ServiceDefinition {
    private final String url;

    public ServiceDefinition(final String url) {
        this.url = Validate.notBlank(url);
    }

    public String url() {
        return this.url;
    }
}
