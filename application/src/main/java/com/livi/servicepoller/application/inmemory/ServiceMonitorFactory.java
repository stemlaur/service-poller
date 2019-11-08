package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceRegistry;
import com.livi.servicepoller.domain.ServiceMonitor;
import com.livi.servicepoller.domain.common.ServicePollerInstrumentation;

public final class ServiceMonitorFactory {

    public static ServiceMonitor inMemory(final ServicePollerInstrumentation instrumentation,
                                          final ServiceRegistry serviceRegistry,
                                          final InMemoryServiceStatuses serviceStatuses) {
        return new ServiceMonitor(
                instrumentation,
                new InMemoryEventBus(),
                serviceRegistry,
                serviceStatuses,
                new FakeServiceHealth()
        );
    }
}
