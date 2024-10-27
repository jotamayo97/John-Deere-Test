package com.example.johndeere.infraestructure.persistence.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity(name = "Machines")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MachineDAO {

    @Id
    private String machineId;

    @Column(nullable = false)
    private Instant createdAt;
}
