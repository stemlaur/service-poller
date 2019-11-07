package com.livi.servicepoller.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceDefinitionsTest {
    private static final String URL_NAME = "https://amazon.com";
    private ServiceDefinitions serviceDefinitions;

    @Mock
    private ServiceDefinitionRepository serviceDefinitionRepository;

    @Before
    public void setUp() {
        this.serviceDefinitions = new ServiceDefinitions(this.serviceDefinitionRepository);
    }

    @Test
    public void should_not_create_a_definition_when_already_exists() {
        when(this.serviceDefinitionRepository.exists("https://amazon.com")).thenReturn(true);
        this.serviceDefinitions.create(new ServiceDefinition("https://amazon.com"));
        verify(this.serviceDefinitionRepository, never()).save(any());
    }

    @Test
    public void should_create_a_definition_when_doesnot_exist() {
        when(this.serviceDefinitionRepository.exists(URL_NAME)).thenReturn(false);
        this.serviceDefinitions.create(new ServiceDefinition("https://amazon.com"));
        verify(this.serviceDefinitionRepository, times(1)).save(any());
    }
}