package com.example.johndeere.domain.repository;

import com.example.johndeere.domain.model.MachineSession;
import com.example.johndeere.infraestructure.persistence.dao.MachineSessionDAO;

import java.util.Optional;

public interface SessionRepository {

    Optional<MachineSession> findByMachineWithOpenSessions(String machineId);

    Optional<MachineSession> findOneSessionActiveById(String sessionId);

    MachineSessionDAO save(MachineSession machineSession);
}
