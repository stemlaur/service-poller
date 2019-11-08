package com.livi.servicepoller.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ServiceDefinitionRepository {
    boolean exists(String url);

    void save(ServiceDefinition definition);

    Set<ServiceDefinition> findAll();
}
