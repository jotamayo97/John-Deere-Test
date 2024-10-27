package com.example.johndeere.application;

import com.example.johndeere.domain.model.Machine;
import com.example.johndeere.domain.model.MachineSession;
import com.example.johndeere.domain.repository.MachineRepository;
import com.example.johndeere.domain.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SessionServiceTest {
    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private MachineRepository machineRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testNewSession_NoActiveSession_MachineDoesNotExist() {
        // Arrange
        String machineId = "machine-2";
        String sessionId = "session-2";
        Instant startAt = Instant.now();

        Machine newMachine = Machine.builder()
                .machineId(machineId)
                .createdAt(startAt)
                .build();

        MachineSession inputSession = MachineSession.builder()
                .machine(Machine.builder().machineId(machineId).build())
                .sessionId(sessionId)
                .startAt(startAt)
                .build();

        when(sessionRepository.findByMachineWithOpenSessions(machineId))
                .thenReturn(Optional.empty());

        when(machineRepository.findById(machineId))
                .thenReturn(Optional.empty());

        when(machineRepository.save(any(Machine.class)))
                .thenReturn(newMachine);

        // Act
        sessionService.newSession(inputSession);

        // Assert
        ArgumentCaptor<Machine> machineCaptor =
                ArgumentCaptor.forClass(Machine.class);
        verify(machineRepository).save(machineCaptor.capture());

        Machine savedMachine = machineCaptor.getValue();
        assertEquals(machineId, savedMachine.getMachineId());
        assertEquals(startAt, savedMachine.getCreatedAt());

        ArgumentCaptor<MachineSession> sessionCaptor =
                ArgumentCaptor.forClass(MachineSession.class);
        verify(sessionRepository).save(sessionCaptor.capture());
        MachineSession savedSession = sessionCaptor.getValue();

        assertEquals(machineId, savedSession.getMachine().getMachineId());
        assertEquals(sessionId, savedSession.getSessionId());
        assertEquals(startAt, savedSession.getStartAt());
        assertNull(savedSession.getEndAt());
    }

    @Test
    @Transactional
    public void testNewSession_ActiveSessionExists() {
        // Arrange
        String machineId = "machine-3";
        String newSessionId = "session-3";
        Instant newSessionStartAt = Instant.now();
        UUID activeSessionId = UUID.randomUUID();
        Machine machine = Machine.builder()
                .machineId(machineId)
                .createdAt(newSessionStartAt.minusSeconds(7200))
                .build();

        MachineSession activeSession = MachineSession.builder()
                .machineSessionId(activeSessionId)
                .machine(machine)
                .sessionId("active-session")
                .startAt(newSessionStartAt.minusSeconds(3600))
                .build();

        MachineSession inputSession = MachineSession.builder()
                .machine(machine)
                .sessionId(newSessionId)
                .startAt(newSessionStartAt)
                .build();

        when(sessionRepository.findByMachineWithOpenSessions(machineId))
                .thenReturn(Optional.of(activeSession));

        when(machineRepository.findById(machineId))
                .thenReturn(Optional.of(machine));

        // Act
        sessionService.newSession(inputSession);

        // Assert
        ArgumentCaptor<MachineSession> sessionCaptor =
                ArgumentCaptor.forClass(MachineSession.class);

        verify(sessionRepository, times(2)).save(sessionCaptor.capture());
        List<MachineSession> savedSessions = sessionCaptor.getAllValues();

        MachineSession closedSession = savedSessions.get(0);
        assertEquals(activeSession.getMachineSessionId(),
                closedSession.getMachineSessionId());
        assertEquals(activeSession.getStartAt(), closedSession.getStartAt());
        assertEquals(newSessionStartAt, closedSession.getEndAt());


        MachineSession newSession = savedSessions.get(1);
        assertEquals(machineId, newSession.getMachine().getMachineId());
        assertEquals(newSessionId, newSession.getSessionId());
        assertEquals(newSessionStartAt, newSession.getStartAt());
        assertNull(newSession.getEndAt());
    }

}