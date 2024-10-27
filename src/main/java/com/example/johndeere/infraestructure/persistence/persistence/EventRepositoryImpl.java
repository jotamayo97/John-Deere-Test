package com.example.johndeere.infraestructure.persistence.persistence;

import com.example.johndeere.domain.model.Event;
import com.example.johndeere.domain.repository.EventRepository;
import com.example.johndeere.infraestructure.persistence.dao.EventDAOMapper;
import com.example.johndeere.infraestructure.persistence.jpa.EventsRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private EventsRepositoryJPA eventsRepositoryJPA;

    @Autowired
    private EventDAOMapper eventDAOMapper;

    @Override
    public List<Event> saveAll(List<Event> events) {
        return eventDAOMapper
                .toDomainList(
                        eventsRepositoryJPA.saveAll(eventDAOMapper.toDAOList(events)));

    }
}
