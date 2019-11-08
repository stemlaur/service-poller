package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceStatuses;
import com.livi.servicepoller.domain.Status;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryServiceStatuses implements ServiceStatuses {
    private Map<String, Status> statuses = new HashMap<>();

    @Override
    public Status find(final String url) {
        return this.statuses.getOrDefault(url, Status.UKNOWN);
    }

    @Override
    public void save(final String url, final Status status) {
        this.statuses.put(url, status);
    }
}
