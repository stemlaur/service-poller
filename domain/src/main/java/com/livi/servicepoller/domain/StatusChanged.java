package com.livi.servicepoller.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class StatusChanged {
    private final String url;
    private final Status newStatus;

    public StatusChanged(final String url, final Status newStatus) {
        this.url = url;
        this.newStatus = newStatus;
    }
}
