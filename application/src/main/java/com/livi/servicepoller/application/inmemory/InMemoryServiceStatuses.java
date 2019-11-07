package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceStatuses;
import com.livi.servicepoller.domain.Status;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryServiceStatuses implements ServiceStatuses {
    private Map<String, Status> statuses = new HashMap<>();

    @Override
    public Status find(final String urlName) {
        return this.statuses.getOrDefault(urlName, Status.UKNOWN);
    }

    @Override
    public void save(final String urlName, final Status status) {
        this.statuses.put(urlName, status);
    }
}
