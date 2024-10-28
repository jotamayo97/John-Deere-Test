package com.example.johndeere.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class MachineSession {
    private final UUID machineSessionId;
    private final String sessionId;
    private final Machine machine;
    private final Instant startAt;
    private final Instant endAt;

    public MachineSession finishSession(Instant closeDate){
        return MachineSession.builder()
                .machineSessionId(machineSessionId)
                .machine(machine)
                .sessionId(sessionId)
                .startAt(startAt)
                .endAt(closeDate)
                .build();
    }
}