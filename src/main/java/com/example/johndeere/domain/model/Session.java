package com.example.johndeere.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Session {

    private final String sessionId;
    private final String machineId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

}