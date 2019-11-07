package com.livi.servicepoller.domain;

import java.util.Collection;
import java.util.Set;

public final class ServiceDefinitions {
    private final ServiceDefinitionRepository repository;

    public ServiceDefinitions(final ServiceDefinitionRepository repository) {

        this.repository = repository;
    }

    public void create(final ServiceDefinition definition) {
        if (!this.repository.exists(definition.urlName())) {
            this.repository.save(definition);
        }
    }

    public ServiceDefinition find(final String urlName) {
        if (this.repository.exists(urlName)) {
            return new ServiceDefinition(urlName);
        } else {
            return null;
        }
    }

    public Set<ServiceDefinition> findAll() {
        return this.repository.findAll();
    }
}
