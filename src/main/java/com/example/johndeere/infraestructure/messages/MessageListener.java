package com.example.johndeere.infraestructure.messages;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.johndeere.infraestructure.messages.RabbitMQConfig.EVENTS;
import static com.example.johndeere.infraestructure.messages.RabbitMQConfig.SESSIONS;

@Component
public class MessageListener {

    @RabbitListener(queues = SESSIONS)
    public void handleSessions(String message) {
        System.out.println("Received session message: " + message);
    }

    @RabbitListener(queues = EVENTS)
    public void handleEvents(String message) {
        System.out.println("Received event message: " + message);
    }
}