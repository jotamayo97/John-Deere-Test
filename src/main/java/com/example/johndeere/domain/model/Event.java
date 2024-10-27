package com.example.johndeere.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Event {

    private final UUID eventId;
    private final MachineSession machineSession;
    private final Instant eventAt;
    private final String eventType;
    private final Long numericEventValue;

    public Event withMachineSession(MachineSession machineSession) {
        return Event.builder()
                .eventId(eventId)
                .machineSession(machineSession)
                .eventAt(eventAt)
                .eventType(eventType)
                .numericEventValue(numericEventValue)
                .build();
    }
}
