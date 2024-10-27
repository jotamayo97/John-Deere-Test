package com.example.johndeere.infraestructure.messages;

import com.example.johndeere.application.SessionEventsService;
import com.example.johndeere.application.SessionService;
import com.example.johndeere.infraestructure.messages.dto.SessionDTO;
import com.example.johndeere.infraestructure.messages.dto.SessionDTOMapper;
import com.example.johndeere.infraestructure.messages.dto.SessionEventsDTO;
import com.example.johndeere.infraestructure.messages.dto.SessionEventsDTOMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.johndeere.infraestructure.messages.RabbitMQConfig.EVENTS;
import static com.example.johndeere.infraestructure.messages.RabbitMQConfig.SESSIONS;

@Component
public class MessageListener {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionDTOMapper sessionMapper;
    @Autowired
    private SessionEventsDTOMapper eventsMapper;
    @Autowired
    private SessionEventsService sessionEventsService;

    @RabbitListener(queues = SESSIONS)
    public void handleSessions(String message) {
        SessionDTO sessionDTO = null;
        try {
            sessionDTO = objectMapper.readValue(message, SessionDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }

        sessionService.newSession(sessionMapper.toDomain(sessionDTO));
    }

    @RabbitListener(queues = EVENTS)
    public void handleEvents(String message) {
        SessionEventsDTO sessionEventsDTO = null;
        try {
            sessionEventsDTO = objectMapper.readValue(message, SessionEventsDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }

        sessionEventsService.newSessionEvents(eventsMapper.toDomainList(sessionEventsDTO));
    }
}