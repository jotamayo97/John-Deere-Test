package com.example.johndeere.infraestructure.persistence.jpa;

import com.example.johndeere.infraestructure.persistence.dao.EventDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepositoryJPA extends JpaRepository<EventDAO, String> {}

