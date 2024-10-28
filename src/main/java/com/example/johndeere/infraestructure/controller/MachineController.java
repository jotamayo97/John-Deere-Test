package com.example.johndeere.infraestructure.controller;

import com.example.johndeere.application.MachineService;
import com.example.johndeere.infraestructure.controller.dto.MachineDTO;
import com.example.johndeere.infraestructure.controller.dto.MachineDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;
    @Autowired
    private MachineDTOMapper machineDTOMapper;

    @GetMapping
    public List<MachineDTO> getAllMachines(){
        return machineDTOMapper.toDTOList(machineService.findAllMachines());
    }
}
