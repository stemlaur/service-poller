package com.livi.servicepoller.domain;

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
}
