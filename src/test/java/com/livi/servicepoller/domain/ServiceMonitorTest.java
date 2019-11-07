package com.livi.servicepoller.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class ServiceMonitorTest {
    public static final String URL_NAME = "https://www.livi.fr/";
    private ServiceMonitor serviceMonitor;
    @Mock
    private EventBus eventBus;
    @Mock
    private ServiceDefinitions serviceDefinitions;
    @Mock
    private ServiceStatuses serviceStatuses;
    @Mock
    private ServiceHealth serviceHealth;

    @Before
    public void setUp() {
        this.serviceMonitor = new ServiceMonitor(this.eventBus, this.serviceDefinitions, this.serviceStatuses, this.serviceHealth);
    }

    @Test
    public void should_emit_status_changed_when_current_status_is_different() {
        when(this.serviceDefinitions.findAll()).thenReturn(Collections.singleton(new ServiceDefinition("https://www.livi.fr/")));
        when(this.serviceStatuses.find(URL_NAME)).thenReturn(Status.UKNOWN);
        when(this.serviceHealth.check("https://www.livi.fr/")).thenReturn(Status.OK);

        this.serviceMonitor.checkStatuses();

        verify(eventBus, times(1)).publish(new StatusChanged(URL_NAME, Status.OK));
    }

    @Test
    public void should_not_emit_status_changed_when_current_status_is_the_same() {
        when(this.serviceDefinitions.findAll()).thenReturn(Collections.singleton(new ServiceDefinition("https://www.livi.fr/")));
        when(this.serviceStatuses.find(URL_NAME)).thenReturn(Status.OK);
        when(this.serviceHealth.check("https://www.livi.fr/")).thenReturn(Status.OK);

        this.serviceMonitor.checkStatuses();

        verify(eventBus, never()).publish(any());
    }
}
