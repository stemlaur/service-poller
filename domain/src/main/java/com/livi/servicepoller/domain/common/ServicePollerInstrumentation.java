package com.livi.servicepoller.domain.common;

import com.livi.servicepoller.domain.ServiceDefinition;

public interface ServicePollerInstrumentation {
    void registeringServiceDefinition(ServiceDefinition definition);

    void serviceDefinitionRegistered(ServiceDefinition definition);

    void checkingStatuses();

    void statusesChecked();

    void checkingStatusFor(ServiceDefinition serviceDefinition);

    void statusCheckedFor(ServiceDefinition serviceDefinition);
}
