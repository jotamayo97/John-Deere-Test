package com.example.johndeere.application;

import com.example.johndeere.domain.model.Machine;
import com.example.johndeere.domain.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> findAllMachines(){
        return machineRepository.getAll();
    }
}
