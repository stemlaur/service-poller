package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceDefinition;
import com.livi.servicepoller.domain.ServiceDefinitionRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class InMemoryServiceDefinitionRepository implements ServiceDefinitionRepository {
    private Set<ServiceDefinition> definitions = new HashSet<>();

    @Override
    public boolean exists(final String url) {
        return this.definitions.contains(new ServiceDefinition(url));
    }

    @Override
    public void save(final ServiceDefinition definition) {
        this.definitions.add(definition);
    }

    @Override
    public Set<ServiceDefinition> findAll() {
        return Collections.unmodifiableSet(definitions);
    }
}
