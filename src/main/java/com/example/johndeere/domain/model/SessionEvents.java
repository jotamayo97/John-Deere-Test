package com.example.johndeere.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class SessionEvents {

    private final String sessionId;
    private final List<Event> events;
}