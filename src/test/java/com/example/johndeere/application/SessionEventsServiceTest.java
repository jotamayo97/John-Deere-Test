package com.example.johndeere.application;

import com.example.johndeere.domain.model.Event;
import com.example.johndeere.domain.model.Machine;
import com.example.johndeere.domain.model.MachineSession;
import com.example.johndeere.domain.repository.EventRepository;
import com.example.johndeere.domain.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.*;

import org.mockito.ArgumentCaptor;

@ExtendWith(MockitoExtension.class)
class SessionEventsServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionEventsService sessionEventsService;

    @Test
    void testNewSessionEvents_SessionFound() {
        // Arrange
        String sessionId = "session123";
        UUID machineSessionId = UUID.randomUUID();
        Instant startAt = Instant.now().minusSeconds(3600);
        Instant endAt = null; // Sesión aún activa
        Instant eventAt = Instant.now().minusSeconds(1800);

        Machine machine = new Machine("machineId", Instant.now());

        MachineSession session = MachineSession.builder()
                .machineSessionId(machineSessionId)
                .sessionId(sessionId)
                .machine(machine)
                .startAt(startAt)
                .endAt(endAt)
                .build();

        Event event = Event.builder()
                .eventId(UUID.randomUUID())
                .machineSession(session)
                .eventAt(eventAt)
                .eventType("EVENT_TYPE")
                .numericEventValue(100L)
                .build();

        List<Event> sessionEvents = Collections.singletonList(event);

        when(sessionRepository.findOneSessionActiveById(sessionId))
                .thenReturn(Optional.of(session));

        // Act
        sessionEventsService.newSessionEvents(sessionEvents);

        //Assert
        verify(eventRepository, times(1)).saveAll(anyList());
        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Event>> captor = ArgumentCaptor.forClass(List.class);
        verify(eventRepository).saveAll(captor.capture());
        List<Event> savedEvents = captor.getValue();


        assertEquals(1, savedEvents.size());
        for (Event savedEvent : savedEvents) {
            assertEquals(session, savedEvent.getMachineSession());
        }
    }

    @Test
    void testNewSessionEvents_SessionNotFound() {
        // Arrange
        String sessionId = "session123";
        Instant eventAt = Instant.now();


        Machine machine = new Machine("machineId", Instant.now());
        MachineSession session = MachineSession.builder()
                .machineSessionId(UUID.randomUUID())
                .sessionId(sessionId)
                .machine(machine)
                .startAt(Instant.now().minusSeconds(7200))
                .endAt(Instant.now().minusSeconds(3600)) // Sesión terminó hace una hora
                .build();

        Event event = Event.builder()
                .eventId(UUID.randomUUID())
                .machineSession(session)
                .eventAt(eventAt)
                .eventType("EVENT_TYPE")
                .numericEventValue(150L)
                .build();

        List<Event> sessionEvents = Collections.singletonList(event);

        when(sessionRepository.findOneSessionActiveById(sessionId))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            sessionEventsService.newSessionEvents(sessionEvents);
        });

        assertEquals("No se encontró una MachineSession activa para sessionId:" + sessionId, exception.getMessage());

        verify(eventRepository, times(0)).saveAll(anyList());
    }
}