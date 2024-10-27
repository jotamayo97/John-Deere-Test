package com.example.johndeere.infraestructure.controller;

import com.example.johndeere.application.SessionEventsService;
import com.example.johndeere.infraestructure.controller.dto.EventTypeDTO;
import com.example.johndeere.infraestructure.controller.dto.EventTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private SessionEventsService eventService;
    @Autowired
    private EventTypeMapper eventTypeMapper;

    @GetMapping("/machine/{machineId}/session/{sessionId}")
    public List<EventTypeDTO> getAggregatedEvents(
            @PathVariable String machineId,
            @PathVariable String sessionId) {
        return eventTypeMapper.toDTOList(eventService.getAggregatedEvents(machineId, sessionId));
    }
}
