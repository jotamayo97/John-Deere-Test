package com.example.johndeere.infraestructure.persistence.dao;

import com.example.johndeere.domain.model.MachineSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MachineSessionDAOMapper {

    MachineSession toDomain(MachineSessionDAO machineSessionDAO);

    MachineSessionDAO toDAO(MachineSession machineSession);
}
