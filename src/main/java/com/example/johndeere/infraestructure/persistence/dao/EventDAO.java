package com.example.johndeere.infraestructure.persistence.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;


@Entity(name = "Event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private MachineSessionDAO machineSession;
    private Instant eventAt;
    private String eventType;
    private Long numericEventValue;

}