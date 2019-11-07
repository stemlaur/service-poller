package com.livi.servicepoller.domain;

/**
 * The service monitor is responsible of call heath check for each
 * services definition and publishes whether a status has changed.
 */
public final class ServiceMonitor {
    private final ServicePollerInstrumentation instrumentation;
    private final EventBus eventBus;
    private final ServiceDefinitions serviceDefinitions;
    private final ServiceStatuses serviceStatuses;
    private final ServiceHealth serviceHealth;

    public ServiceMonitor(final ServicePollerInstrumentation instrumentation,
                          final EventBus eventBus,
                          final ServiceDefinitions serviceDefinitions,
                          final ServiceStatuses serviceStatuses,
                          final ServiceHealth serviceHealth) {
        this.instrumentation = instrumentation;
        this.eventBus = eventBus;
        this.serviceDefinitions = serviceDefinitions;
        this.serviceStatuses = serviceStatuses;
        this.serviceHealth = serviceHealth;
    }

    public void checkStatuses() {
        this.instrumentation.checkingStatuses();
        this.serviceDefinitions.findAll()
                .forEach(serviceDefinition -> {
                    this.instrumentation.checkingStatusFor(serviceDefinition);

                    final String urlName = serviceDefinition.urlName();
                    final Status currentStatus = this.serviceStatuses.find(urlName);
                    final Status newStatus = serviceHealth.check(urlName);

                    if (currentStatus != newStatus) {
                        this.serviceStatuses.save(urlName, newStatus);
                        this.eventBus.publish(new StatusChanged(urlName, newStatus));
                    }

                    this.instrumentation.statusCheckedFor(serviceDefinition);
                });
        this.instrumentation.statusesChecked();
    }
}
