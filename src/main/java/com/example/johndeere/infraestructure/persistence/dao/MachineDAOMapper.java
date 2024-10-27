package com.example.johndeere.infraestructure.persistence.dao;

import com.example.johndeere.domain.model.Machine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MachineDAOMapper {

    Machine toDomain(MachineDAO machineDAO);

    MachineDAO toDAO(Machine machine);
}
