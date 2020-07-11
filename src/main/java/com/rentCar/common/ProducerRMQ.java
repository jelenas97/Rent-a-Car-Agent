package com.rentCar.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class ProducerRMQ {

    public ProducerRMQ(String message) throws KeyManagementException, URISyntaxException, NoSuchAlgorithmException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt");
        factory.setPassword("4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt");
        factory.setHost("bertrand162@ifchuck.com");
        factory.setUri("amqp://asdlnfka:4Es7xnijzf1DSgPxlcYcGTKhjifJQnIt@emu.rmq.cloudamqp.com/asdlnfka");
        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();
            //red
            channel.queueDeclare("rabbitmq-message", false, false, false, null);
            //sending message
            channel.basicPublish("", "rabbitmq-message", false, null, message.getBytes());

            System.out.println("Message sent");

        } catch (NullPointerException | IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
