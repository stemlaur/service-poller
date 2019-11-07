package com.livi.servicepoller.domain;

public interface ServiceDefinitionRepository {
    boolean exists(String urlName);

    void save(ServiceDefinition definition);
}
