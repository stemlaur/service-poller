package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceDefinitions;
import com.livi.servicepoller.domain.ServiceMonitor;
import com.livi.servicepoller.domain.ServicePollerInstrumentation;

public final class ServiceMonitorFactory {

    public static ServiceMonitor inMemory(final ServicePollerInstrumentation instrumentation,
                                          final ServiceDefinitions serviceDefinitions,
                                          final InMemoryServiceStatuses serviceStatuses) {
        return new ServiceMonitor(
                instrumentation,
                new InMemoryEventBus(),
                serviceDefinitions,
                serviceStatuses,
                new FakeServiceHealth()
        );
    }
}
