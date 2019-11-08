package com.livi.servicepoller.domain;

import com.livi.servicepoller.domain.common.ServicePollerInstrumentation;

import java.util.Set;

public class ServiceRegistry {
    private final ServicePollerInstrumentation instrumentation;
    private final ServiceDefinitionRepository repository;

    public ServiceRegistry(final ServicePollerInstrumentation instrumentation,
                           final ServiceDefinitionRepository repository) {
        this.instrumentation = instrumentation;
        this.repository = repository;
    }

    public void register(final ServiceDefinition definition) {
        this.instrumentation.registeringServiceDefinition(definition);
        if (!this.repository.exists(definition.url())) {
            this.repository.save(definition);
        }
        this.instrumentation.serviceDefinitionRegistered(definition);
    }

    public ServiceDefinition find(final String url) {
        if (this.repository.exists(url)) {
            return new ServiceDefinition(url);
        } else {
            return null;
        }
    }

    public Set<ServiceDefinition> findAll() {
        return this.repository.findAll();
    }
}
