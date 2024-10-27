package com.example.johndeere.application;

import com.example.johndeere.domain.model.Event;
import com.example.johndeere.domain.model.MachineSession;
import com.example.johndeere.domain.repository.EventRepository;
import com.example.johndeere.domain.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessionEventsService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Transactional
    public void newSessionEvents(List<Event> sessionEvents) {
        //TO-DO Not active session, session what was active during each eventAt
        Optional<MachineSession> activeSession = sessionRepository.findOneSessionActiveById(sessionEvents.get(0).getMachineSession().getSessionId());
        List<Event> updatedEvents = sessionEvents.stream()
                .map(event -> event.withMachineSession(
                        activeSession.orElseThrow(() -> new EntityNotFoundException("No se encontró una MachineSession activa para sessionId:" + sessionEvents.get(0).getMachineSession().getSessionId()))
                ))
                .toList();
        eventRepository.saveAll(updatedEvents);
    }
}
