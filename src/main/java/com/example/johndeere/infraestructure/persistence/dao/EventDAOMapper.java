package com.example.johndeere.infraestructure.persistence.dao;

import com.example.johndeere.domain.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = MachineSessionDAOMapper.class)
public interface EventDAOMapper {

    @Mapping(target = "machineSession.sessionId", source = "machineSession.sessionId")
    @Mapping(target = "eventId", source = "eventId")
    Event toDomain(EventDAO eventDAO);


    @Mapping(target = "eventId", ignore = true)
    EventDAO toDAO(Event event);


    List<Event> toDomainList(List<EventDAO> eventDAOList);
    List<EventDAO> toDAOList(List<Event> eventList);

}
