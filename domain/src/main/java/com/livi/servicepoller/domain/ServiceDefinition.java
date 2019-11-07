package com.livi.servicepoller.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.Validate;

/**
 * This entity represents the definition of a service.
 * A service is unique regarding its urlName.
 */
@EqualsAndHashCode
@ToString
public final class ServiceDefinition {
    private final String urlName;

    public ServiceDefinition(final String urlName) {
        this.urlName = Validate.notBlank(urlName);
    }

    public String urlName() {
        return this.urlName;
    }
}
