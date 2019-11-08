package com.livi.servicepoller.domain;

import org.junit.Test;

public class ServiceDefinitionTest {

    @Test(expected = ServiceURLIsNotValid.class)
    public void should_throw_an_exception_when_url_is_blank() {
        new ServiceDefinition("");
    }

    @Test(expected = ServiceURLIsNotValid.class)
    public void should_throw_an_exception_when_url_is_not_valid() {
        new ServiceDefinition("http:/toto/");
    }

    @Test
    public void should_create_servicedefinition_when_url_is_valid() {
        new ServiceDefinition("http://example.com");
        new ServiceDefinition("https://example.com");
        new ServiceDefinition("https://example.com/test.html");
    }

}