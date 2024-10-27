package com.example.johndeere.infraestructure.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventTypeDTO {

    private String eventType;
    private Long numericEventValue;
}