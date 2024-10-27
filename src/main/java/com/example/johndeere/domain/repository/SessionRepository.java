package com.example.johndeere.domain.repository;

import com.example.johndeere.domain.model.MachineSession;
import com.example.johndeere.infraestructure.persistence.dao.MachineSessionDAO;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {

    Optional<MachineSession> findByMachineWithOpenSessions(String machineId);

    MachineSessionDAO save(MachineSession machineSession);
}
