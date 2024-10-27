package com.example.johndeere.infraestructure.controller.dto;

import com.example.johndeere.domain.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {

    EventTypeDTO eventToEventTypeDTO(Event event);
    List<EventTypeDTO> toDTOList(List<Event> events);

}
