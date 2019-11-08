package com.livi.servicepoller.application.inmemory;

import com.livi.servicepoller.domain.ServiceDefinition;
import com.livi.servicepoller.domain.ServicePollerInstrumentation;
import org.slf4j.Logger;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

public final class SimpleServicePollerInstrumentation implements ServicePollerInstrumentation {
    private static final Logger LOGGER = getLogger(SimpleServicePollerInstrumentation.class);


    @Override
    public void creatingServiceDefinition(final ServiceDefinition definition) {
        LOGGER.info(format("Creating service definition %s", definition.urlName()));
    }

    @Override
    public void serviceDefinitionCreated(final ServiceDefinition definition) {
        LOGGER.info(format("Service definition %s created", definition.urlName()));
    }

    @Override
    public void checkingStatuses() {
        LOGGER.debug("Checking all statuses");
    }

    @Override
    public void statusesChecked() {
        LOGGER.debug("All statuses checked");
    }

    @Override
    public void checkingStatusFor(final ServiceDefinition definition) {
        LOGGER.debug(format("Checking status for %s", definition.urlName()));
    }

    @Override
    public void statusCheckedFor(final ServiceDefinition definition) {
        LOGGER.debug(format("Status checked for %s", definition.urlName()));
    }
}
