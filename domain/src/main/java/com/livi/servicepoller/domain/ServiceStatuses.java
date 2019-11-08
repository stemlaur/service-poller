package com.livi.servicepoller.domain;

public interface ServiceStatuses {
    Status find(String url);

    void save(String url, Status ok);
}
