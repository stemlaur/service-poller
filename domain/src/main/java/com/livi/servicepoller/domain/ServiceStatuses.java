package com.livi.servicepoller.domain;

public interface ServiceStatuses {
    Status find(String urlName);

    void save(String urlName, Status ok);
}
