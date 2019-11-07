package com.livi.servicepoller.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceDefinitionsTest {
    private static final String URL_NAME = "https://amazon.com";
    private ServiceDefinitions serviceDefinitions;

    @Mock
    private ServicePollerInstrumentation instrumentation;
    @Mock
    private ServiceDefinitionRepository serviceDefinitionRepository;

    @Before
    public void setUp() {
        this.serviceDefinitions = new ServiceDefinitions(instrumentation, this.serviceDefinitionRepository);
    }

    @Test
    public void should_not_create_a_definition_when_already_exists() {
        when(this.serviceDefinitionRepository.exists(URL_NAME)).thenReturn(true);
        this.serviceDefinitions.create(new ServiceDefinition("https://amazon.com"));
        verify(this.serviceDefinitionRepository, never()).save(any());
    }

    @Test
    public void should_create_a_definition_when_doesnot_exist() {
        when(this.serviceDefinitionRepository.exists(URL_NAME)).thenReturn(false);
        this.serviceDefinitions.create(new ServiceDefinition("https://amazon.com"));
        verify(this.serviceDefinitionRepository, times(1)).save(any());
    }

    @Test
    public void should_retrieve_definition_when_exists() {
        when(this.serviceDefinitionRepository.exists(URL_NAME)).thenReturn(true);
        final ServiceDefinition actual = this.serviceDefinitions.find(URL_NAME);
        final ServiceDefinition expected = new ServiceDefinition(URL_NAME);
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_null_when_definition_does_not_exist() {
        when(this.serviceDefinitionRepository.exists(URL_NAME)).thenReturn(false);
        final ServiceDefinition actual = this.serviceDefinitions.find(URL_NAME);
        assertNull(actual);
    }

    @Test
    public void should_return_the_set_of_definitions() {
        when(this.serviceDefinitionRepository.findAll())
                .thenReturn(Collections.singleton(new ServiceDefinition(URL_NAME)));
        final Set<ServiceDefinition> actual = this.serviceDefinitions.findAll();
        final Set<ServiceDefinition> expected = Collections.singleton(new ServiceDefinition(URL_NAME));
        assertEquals(expected, actual);
    }
}