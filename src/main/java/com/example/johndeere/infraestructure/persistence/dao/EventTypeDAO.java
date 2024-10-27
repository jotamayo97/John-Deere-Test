package com.example.johndeere.infraestructure.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class EventTypeDAO {
    private String eventType;
    private Long numericEventValue;
}
