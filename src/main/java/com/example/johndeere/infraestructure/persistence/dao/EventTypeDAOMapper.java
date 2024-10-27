package com.example.johndeere.infraestructure.persistence.dao;

import com.example.johndeere.domain.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventTypeDAOMapper {

    List<Event> toDomainList(List<EventTypeDAO> eventTypeDAOS);
}
