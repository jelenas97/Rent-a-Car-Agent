package com.rentCar.config;

import com.rentCar.soap.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.rentCar.RentCar.wsdl");
        return marshaller;
    }


    @Bean
    public AdClient adClient(Jaxb2Marshaller marshaller) {
        AdClient client = new AdClient();
        client.setDefaultUri("http://localhost:8084/advertisement/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public ReportClient reportClient(Jaxb2Marshaller marshaller) {
        ReportClient client = new ReportClient();
        client.setDefaultUri("http://localhost:8084/advertisement/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public MessageClient messageClient(Jaxb2Marshaller marshaller) {
        MessageClient client = new MessageClient();
        client.setDefaultUri("http://localhost:8087/microservices/messages/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public RentClient rentClient(Jaxb2Marshaller marshaller) {
        RentClient client = new RentClient();
        client.setDefaultUri("http://localhost:8095/microservices/rent/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public StatisticsClient statisticsClient(Jaxb2Marshaller marshaller) {
        StatisticsClient client = new StatisticsClient();
        client.setDefaultUri("http://localhost:8090/microservices/statistics/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
