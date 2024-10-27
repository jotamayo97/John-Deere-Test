package com.example.johndeere.infraestructure.persistence.persistence;

import com.example.johndeere.domain.model.MachineSession;
import com.example.johndeere.domain.repository.SessionRepository;
import com.example.johndeere.infraestructure.persistence.dao.MachineSessionDAO;
import com.example.johndeere.infraestructure.persistence.dao.MachineSessionDAOMapper;
import com.example.johndeere.infraestructure.persistence.jpa.SessionRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    @Autowired
    private SessionRepositoryJPA sessionRepositoryJPA;

    @Autowired
    private MachineSessionDAOMapper mapper;

    @Override
    public Optional<MachineSession> findByMachineWithOpenSessions(String machineId) {
        return sessionRepositoryJPA.findByMachineWithOpenSessions(machineId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<MachineSession> findOneSessionActiveById(String sessionId) {
        return sessionRepositoryJPA.findActiveSessionBySessionId(sessionId)
                .map(mapper::toDomain);
    }

    @Override
    public MachineSessionDAO save(MachineSession machineSession) {
        return sessionRepositoryJPA.save(mapper.toDAO(machineSession));
    }
}
