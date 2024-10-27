package com.example.johndeere.infraestructure.persistence.persistence;

import com.example.johndeere.domain.model.Machine;
import com.example.johndeere.domain.repository.MachineRepository;
import com.example.johndeere.infraestructure.persistence.dao.MachineDAOMapper;
import com.example.johndeere.infraestructure.persistence.jpa.MachineRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MachineRepositoryImpl implements MachineRepository {

    @Autowired
    private MachineRepositoryJPA machineRepositoryJPA;

    @Autowired
    private MachineDAOMapper machineDAOMapper;

    @Override
    public Optional<Machine> findById(String id) {
        return machineRepositoryJPA.findById(id).map(machineDAOMapper::toDomain);
    }

    public Machine save (Machine machine){
        return machineDAOMapper.toDomain(
                machineRepositoryJPA.save(machineDAOMapper.toDAO(machine)));
    }
}
