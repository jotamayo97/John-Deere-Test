package com.example.johndeere.domain.repository;

import com.example.johndeere.domain.model.Machine;

import java.util.Optional;

public interface MachineRepository {
    Optional<Machine> findById(String id);
    Machine save(Machine machine);
}
