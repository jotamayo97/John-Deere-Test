package com.example.johndeere.infraestructure.messages.dto;

import com.example.johndeere.domain.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionEventsDTOMapper {

    default List<Event> toDomainList(SessionEventsDTO sessionEventsDTO) {
        return sessionEventsDTO.getEvents().stream()
                .map(eventDTO -> toEvent(eventDTO, sessionEventsDTO.getSessionId()))
                .toList();
    }

    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "machineSession.sessionId", source = "sessionId")
    Event toEvent(EventDTO eventDTO, String sessionId);

}