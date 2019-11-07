package com.livi.servicepoller.application;


import com.livi.servicepoller.application.inmemory.InMemoryServiceDefinitionRepository;
import com.livi.servicepoller.application.inmemory.ServiceMonitorFactory;
import com.livi.servicepoller.application.inmemory.SimpleCron;
import com.livi.servicepoller.application.inmemory.SimpleServicePollerInstrumentation;
import com.livi.servicepoller.domain.ServiceDefinition;
import com.livi.servicepoller.domain.ServiceDefinitions;
import com.livi.servicepoller.domain.ServiceMonitor;
import com.livi.servicepoller.domain.ServicePollerInstrumentation;

import java.util.Scanner;

public final class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final ServicePollerInstrumentation instrumentation = new SimpleServicePollerInstrumentation();
        final ServiceDefinitions serviceDefinitions = new ServiceDefinitions(instrumentation, new InMemoryServiceDefinitionRepository());
        final ServiceMonitor serviceMonitor = ServiceMonitorFactory.inMemory(instrumentation, serviceDefinitions);
        final SimpleCron simpleCron = new SimpleCron(serviceMonitor::checkStatuses);

        serviceDefinitions.create(new ServiceDefinition("http://google.fr"));
        serviceDefinitions.create(new ServiceDefinition("http://mozilla.fr"));

        while (true) {
            System.out.println("Please press N to stop");
            final String n = scanner.nextLine();
            if(n.trim().equals("N")) {
                simpleCron.shutdown();
                System.exit(0);
            }
        }

    }
}
