package com.example.johndeere.infraestructure.persistence.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "MachineSession")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MachineSessionDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID machineSessionId;

    private String sessionId;

    @ManyToOne()
    @JoinColumn(name = "machine_id")
    private MachineDAO machine;

    @Column(nullable = false)
    private Instant startAt;

    private Instant endAt;

}
