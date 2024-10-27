package com.example.johndeere.infraestructure.messages.dto;

import com.example.johndeere.domain.model.Machine;
import com.example.johndeere.domain.model.MachineSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface SessionDTOMapper {

    @Mapping(target = "endAt", ignore = true)
    @Mapping(target = "machineSessionId", ignore = true)
    @Mapping(target = "machine", source = "machineId")
    @Mapping(target = "startAt", expression = "java(convertTimestamp(sessionDTO.getStartAt()))")
    MachineSession toDomain(SessionDTO sessionDTO);

    default Machine mapMachine(String machineId) {
        return Machine.builder()
                .machineId(machineId)
                .build();
    }

    default Instant convertTimestamp(Long timestamp) {
        return Instant.ofEpochSecond(timestamp);
    }

}
