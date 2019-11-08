package com.livi.servicepoller.domain;

import com.livi.servicepoller.domain.common.EventBus;
import com.livi.servicepoller.domain.common.ServicePollerInstrumentation;
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
    private ServicePollerInstrumentation instrumentation;
    @Mock
    private EventBus eventBus;
    @Mock
    private ServiceRegistry serviceRegistry;
    @Mock
    private ServiceStatuses serviceStatuses;
    @Mock
    private ServiceHealth serviceHealth;

    @Before
    public void setUp() {
        this.serviceMonitor = new ServiceMonitor(instrumentation, this.eventBus, this.serviceRegistry, this.serviceStatuses, this.serviceHealth);
    }

    @Test
    public void should_emit_status_changed_when_current_status_is_different() {
        when(this.serviceRegistry.findAll()).thenReturn(Collections.singleton(new ServiceDefinition("https://www.livi.fr/")));
        when(this.serviceStatuses.find(URL_NAME)).thenReturn(Status.UKNOWN);
        when(this.serviceHealth.check("https://www.livi.fr/")).thenReturn(Status.OK);

        this.serviceMonitor.checkStatuses();

        verify(eventBus, times(1)).publish(new StatusChanged(URL_NAME, Status.OK));
    }

    @Test
    public void should_not_emit_status_changed_when_current_status_is_the_same() {
        when(this.serviceRegistry.findAll()).thenReturn(Collections.singleton(new ServiceDefinition("https://www.livi.fr/")));
        when(this.serviceStatuses.find(URL_NAME)).thenReturn(Status.OK);
        when(this.serviceHealth.check("https://www.livi.fr/")).thenReturn(Status.OK);

        this.serviceMonitor.checkStatuses();

        verify(eventBus, never()).publish(any());
    }

    @Test
    public void should_store_new_status_when_current_status_is_different() {
        when(this.serviceRegistry.findAll()).thenReturn(Collections.singleton(new ServiceDefinition("https://www.livi.fr/")));
        when(this.serviceStatuses.find(URL_NAME)).thenReturn(Status.UKNOWN);
        when(this.serviceHealth.check("https://www.livi.fr/")).thenReturn(Status.OK);

        this.serviceMonitor.checkStatuses();

        verify(this.serviceStatuses, times(1)).save(URL_NAME, Status.OK);
    }

    @Test
    public void should_not_store_new_status_when_current_status_is_the_same() {
        when(this.serviceRegistry.findAll()).thenReturn(Collections.singleton(new ServiceDefinition("https://www.livi.fr/")));
        when(this.serviceStatuses.find(URL_NAME)).thenReturn(Status.OK);
        when(this.serviceHealth.check("https://www.livi.fr/")).thenReturn(Status.OK);

        this.serviceMonitor.checkStatuses();

        verify(this.serviceStatuses, never()).save(any(), any());
    }
}
