package com.example.johndeere.infraestructure.messages.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class EventDTO {

    private String eventType;
    private Long numericEventValue;
    private Instant eventAt;
}