package com.example.johndeere.infraestructure.messages.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessionDTO {
    private String sessionId;
    private String machineId;
    private Long startAt;
}
