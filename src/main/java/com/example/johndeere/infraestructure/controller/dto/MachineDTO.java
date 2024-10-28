package com.example.johndeere.infraestructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class MachineDTO {
    private final String machineId;
    private final Instant createdAt;

}