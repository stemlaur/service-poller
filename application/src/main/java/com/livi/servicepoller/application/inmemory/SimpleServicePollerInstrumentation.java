package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceDefinition;
import com.livi.servicepoller.domain.ServicePollerInstrumentation;

import static java.lang.String.format;

public final class SimpleServicePollerInstrumentation implements ServicePollerInstrumentation {

    @Override
    public void creatingServiceDefinition(final ServiceDefinition definition) {
        System.out.println(format("Creating service definition %s", definition.urlName()));
    }

    @Override
    public void serviceDefinitionCreated(final ServiceDefinition definition) {
        System.out.println(format("Service definition %s created", definition.urlName()));
    }

    @Override
    public void checkingStatuses() {
        System.out.println("Checking all statuses");
    }

    @Override
    public void statusesChecked() {
        System.out.println("All statuses checked");
    }

    @Override
    public void checkingStatusFor(final ServiceDefinition definition) {
        System.out.println(format("Checking status for %s", definition.urlName()));
    }

    @Override
    public void statusCheckedFor(final ServiceDefinition definition) {
        System.out.println(format("Status checked for %s", definition.urlName()));
    }
}
