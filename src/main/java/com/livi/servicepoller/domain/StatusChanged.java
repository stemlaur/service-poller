package com.livi.servicepoller.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class StatusChanged {
    private final String urlName;
    private final Status newStatus;

    public StatusChanged(final String urlName, final Status newStatus) {
        this.urlName = urlName;
        this.newStatus = newStatus;
    }

    public String urlName() {
        return this.urlName;
    }

    public Status newStatus() {
        return this.newStatus;
    }
}
