package com.livi.servicepoller.domain;

public final class ServiceMonitor {
    private final EventBus eventBus;
    private final ServiceDefinitions serviceDefinitions;
    private final ServiceStatuses serviceStatuses;
    private final ServiceHealth serviceHealth;

    public ServiceMonitor(final EventBus eventBus,
                          final ServiceDefinitions serviceDefinitions,
                          final ServiceStatuses serviceStatuses,
                          final ServiceHealth serviceHealth) {

        this.eventBus = eventBus;
        this.serviceDefinitions = serviceDefinitions;
        this.serviceStatuses = serviceStatuses;
        this.serviceHealth = serviceHealth;
    }

    public void checkStatuses() {
        this.serviceDefinitions.findAll()
                .forEach(serviceDefinition -> {
                    final String urlName = serviceDefinition.urlName();
                    final Status currentStatus = this.serviceStatuses.find(urlName);
                    final Status newStatus = serviceHealth.check(urlName);
                    if (currentStatus != newStatus) {
                        this.eventBus.publish(new StatusChanged(urlName, newStatus));
                    }
                });
    }
}
