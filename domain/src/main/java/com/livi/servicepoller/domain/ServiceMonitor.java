package com.livi.servicepoller.domain;

import com.livi.servicepoller.domain.common.EventBus;
import com.livi.servicepoller.domain.common.ServicePollerInstrumentation;

/**
 * The ServiceMonitor is responsible of calling heath checks for each
 * service definition and publishes whether a status has changed.
 */
public final class ServiceMonitor {
    private final ServicePollerInstrumentation instrumentation;
    private final EventBus eventBus;
    private final ServiceRegistry serviceRegistry;
    private final ServiceStatuses serviceStatuses;
    private final ServiceHealth serviceHealth;

    public ServiceMonitor(final ServicePollerInstrumentation instrumentation,
                          final EventBus eventBus,
                          final ServiceRegistry serviceRegistry,
                          final ServiceStatuses serviceStatuses,
                          final ServiceHealth serviceHealth) {
        this.instrumentation = instrumentation;
        this.eventBus = eventBus;
        this.serviceRegistry = serviceRegistry;
        this.serviceStatuses = serviceStatuses;
        this.serviceHealth = serviceHealth;
    }

    public void checkStatuses() {
        this.instrumentation.checkingStatuses();
        this.serviceRegistry.findAll()
                .forEach(serviceDefinition -> {
                    this.instrumentation.checkingStatusFor(serviceDefinition);

                    final String url = serviceDefinition.url();
                    final Status currentStatus = this.serviceStatuses.find(url);
                    final Status newStatus = serviceHealth.check(url);

                    if (currentStatus != newStatus) {
                        this.serviceStatuses.save(url, newStatus);
                        this.eventBus.publish(new StatusChanged(url, newStatus));
                    }

                    this.instrumentation.statusCheckedFor(serviceDefinition);
                });
        this.instrumentation.statusesChecked();
    }
}
