package com.livi.servicepoller.application;


import com.livi.servicepoller.application.inmemory.*;
import com.livi.servicepoller.domain.*;
import com.livi.servicepoller.domain.common.ServicePollerInstrumentation;

import java.util.Scanner;
import java.util.Set;

public final class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final ServicePollerInstrumentation instrumentation = new SimpleServicePollerInstrumentation();
        final ServiceRegistry serviceRegistry = new ServiceRegistry(instrumentation, new InMemoryServiceDefinitionRepository());
        final InMemoryServiceStatuses serviceStatuses = new InMemoryServiceStatuses();
        final ServiceMonitor serviceMonitor = ServiceMonitorFactory.inMemory(instrumentation, serviceRegistry, serviceStatuses);
        final SimpleCron simpleCron = new SimpleCron(serviceMonitor::checkStatuses);

        System.out.println("Welcome to the simple service poller interface:");
        System.out.println("Please see app.log file to see service health status changes.");
        while (true) {
            System.out.println("");
            System.out.println("1 - to add a service");
            System.out.println("2 - to remove a service");
            System.out.println("3 - to list all services");
            System.out.println("4 - find the health status of a service");
            System.out.println("5 - to exit");
            final String n = scanner.next();
            switch (n) {
                case "1":
                    System.out.println("========== You want to add a service ==========");
                    final String serviceURLToAdd = askUserForTheServiceURL();
                    serviceRegistry.register(new ServiceDefinition(serviceURLToAdd));
                    break;
                case "2":
                    System.out.println("========== You want to remove a service ==========");
                    System.out.println("This functionality is not implemented yet");
                    break;
                case "3":
                    System.out.println("========== You want to list all services ==========");
                    listAllServicesToConsole(serviceRegistry.findAll());
                    break;
                case "4":
                    System.out.println("========== You want to find the health status of a service ==========");
                    final String serviceURLToRetrieveStatus = askUserForTheServiceURL();
                    final Status status = serviceStatuses.find(serviceURLToRetrieveStatus);
                    System.out.println("The service " + serviceURLToRetrieveStatus + " is " + status);
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    simpleCron.shutdown();
                    System.exit(0);
                default:
                    System.out.println("I did not understand please try again");
            }
        }

    }

    private static String askUserForTheServiceURL() {
        System.out.println("Please provide me the url of the service:");
        return scanner.next();
    }

    private static void listAllServicesToConsole(final Set<ServiceDefinition> serviceDefinitions) {
        serviceDefinitions.forEach(sd -> System.out.println(sd.url()));
    }
}
