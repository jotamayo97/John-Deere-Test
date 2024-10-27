package com.example.johndeere.infraestructure.persistence.jpa;

import com.example.johndeere.infraestructure.persistence.dao.MachineDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepositoryJPA extends JpaRepository<MachineDAO, String> {}