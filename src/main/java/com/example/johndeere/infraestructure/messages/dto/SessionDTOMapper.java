package com.example.johndeere.infraestructure.messages.dto;

import com.example.johndeere.domain.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface SessionDTOMapper {

    @Mapping(target = "endAt", ignore = true)
    @Mapping(target = "startAt", expression = "java(convertTimestamp(sessionDTO.getStartAt()))")
    Session toDomain(SessionDTO sessionDTO);

    default LocalDateTime convertTimestamp(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);
    }

}
