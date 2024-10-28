package com.example.johndeere.infraestructure.controller.dto;

import com.example.johndeere.domain.model.Machine;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface MachineDTOMapper {

    MachineDTO toDTO(Machine Machine);
    List<MachineDTO> toDTOList(List<Machine> machines);
}
