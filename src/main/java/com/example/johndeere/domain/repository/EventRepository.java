package com.example.johndeere.domain.repository;

import com.example.johndeere.domain.model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> saveAll(List<Event> event);
}
