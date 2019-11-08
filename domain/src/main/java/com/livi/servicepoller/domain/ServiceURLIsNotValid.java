package com.livi.servicepoller.domain;

import lombok.EqualsAndHashCode;

import static java.lang.String.format;

@EqualsAndHashCode(callSuper = true)
public final class ServiceURLIsNotValid extends RuntimeException {
    private final String url;

    public ServiceURLIsNotValid(final String url) {
        super(format("Service url %s is not valid", url));
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
