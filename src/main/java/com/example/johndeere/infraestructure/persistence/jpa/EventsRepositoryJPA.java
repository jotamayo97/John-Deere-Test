package com.example.johndeere.infraestructure.persistence.jpa;

import com.example.johndeere.infraestructure.persistence.dao.EventDAO;
import com.example.johndeere.infraestructure.persistence.dao.EventTypeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventsRepositoryJPA extends JpaRepository<EventDAO, String> {
    @Query("SELECT new com.example.johndeere.infraestructure.persistence.dao.EventTypeDAO(e.eventType, MAX(e.numericEventValue)) " +
            "FROM Event e " +
            "WHERE e.machineSession.machine.machineId = :machineId " +
            "AND e.machineSession.sessionId = :sessionId " +
            "GROUP BY e.eventType")
    List<EventTypeDAO> findAggregatedEventsByMachineAndSession(
            @Param("machineId") String machineId,
            @Param("sessionId") String sessionId);
}

