package com.example.johndeere.infraestructure.messages.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class SessionEventsDTO {
    private String sessionId;
    private List<EventDTO> events;
}
