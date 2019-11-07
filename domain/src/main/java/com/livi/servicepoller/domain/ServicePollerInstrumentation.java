package com.livi.servicepoller.domain;

public interface ServicePollerInstrumentation {
    void creatingServiceDefinition(ServiceDefinition definition);

    void serviceDefinitionCreated(ServiceDefinition definition);

    void checkingStatuses();

    void statusesChecked();

    void checkingStatusFor(ServiceDefinition serviceDefinition);

    void statusCheckedFor(ServiceDefinition serviceDefinition);
}
