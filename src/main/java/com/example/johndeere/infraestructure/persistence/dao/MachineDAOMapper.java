package com.example.johndeere.infraestructure.persistence.dao;

import com.example.johndeere.domain.model.Machine;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MachineDAOMapper {

    Machine toDomain(MachineDAO machineDAO);

    List<Machine> toDomain(List<MachineDAO> machineDAOList);

    MachineDAO toDAO(Machine machine);
}
