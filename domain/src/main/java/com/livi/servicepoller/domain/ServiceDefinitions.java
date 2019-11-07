package com.livi.servicepoller.domain;

import java.util.Set;

public class ServiceDefinitions {
    private final ServicePollerInstrumentation instrumentation;
    private final ServiceDefinitionRepository repository;

    public ServiceDefinitions(final ServicePollerInstrumentation instrumentation,
                              final ServiceDefinitionRepository repository) {
        this.instrumentation = instrumentation;
        this.repository = repository;
    }

    public void create(final ServiceDefinition definition) {
        this.instrumentation.creatingServiceDefinition(definition);
        if (!this.repository.exists(definition.urlName())) {
            this.repository.save(definition);
        }
        this.instrumentation.serviceDefinitionCreated(definition);
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
