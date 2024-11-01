package com.example.johndeere.application;

import com.example.johndeere.domain.model.Machine;
import com.example.johndeere.domain.model.MachineSession;
import com.example.johndeere.domain.repository.MachineRepository;
import com.example.johndeere.domain.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class SessionService {


    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private MachineRepository machineRepository;

    @Transactional
    public void newSession(MachineSession session) {

        Machine machine = checkAndCloseMachineOpenSessions(session);

        MachineSession newSession = MachineSession.builder()
                .machine(machine)
                .sessionId(session.getSessionId())
                .startAt(session.getStartAt())
                .build();

        sessionRepository.save(newSession);
    }

    private Machine checkAndCloseMachineOpenSessions(MachineSession session) {
        return sessionRepository.findByMachineWithOpenSessions(session.getMachine().getMachineId())
                .map(activeSession -> {
                    closeActiveSession(activeSession, session);
                    return machineRepository.findById(session.getMachine().getMachineId())
                            .orElse(createMachine(session.getMachine().getMachineId(), session.getStartAt()));
                })
                .orElseGet(() -> createMachine(session.getMachine().getMachineId(), session.getStartAt()));
    }

    private void closeActiveSession(MachineSession activeSession, MachineSession newSession) {
        MachineSession sessionClosed = activeSession.finishSession(newSession.getStartAt());
        sessionRepository.save(sessionClosed);
    }

    private Machine createMachine(String machineId, Instant startAt) {
        Machine newMachine = Machine.builder()
                .machineId(machineId)
                .createdAt(startAt)
                .build();
        return machineRepository.save(newMachine);
    }




}
