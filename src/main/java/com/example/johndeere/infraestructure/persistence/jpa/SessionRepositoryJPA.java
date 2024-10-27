package com.example.johndeere.infraestructure.persistence.jpa;

import com.example.johndeere.infraestructure.persistence.dao.MachineSessionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepositoryJPA extends JpaRepository<MachineSessionDAO, String> {

    @Query("SELECT s FROM MachineSession s WHERE s.machine.machineId = :machineId AND s.endAt IS NULL")
    Optional<MachineSessionDAO> findByMachineWithOpenSessions(@Param("machineId") String machineId);

    @Query("SELECT s FROM MachineSession s WHERE s.sessionId = :sessionId AND s.endAt IS NULL")
    Optional<MachineSessionDAO> findActiveSessionBySessionId(@Param("sessionId") String sessionId);
}

