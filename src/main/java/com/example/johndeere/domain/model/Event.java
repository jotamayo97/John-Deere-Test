package com.example.johndeere.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
@Builder
public class Event {

    private final String eventId;
    private final Instant eventAt;
    private final String eventType;
    private final double numericEventValue;
}
