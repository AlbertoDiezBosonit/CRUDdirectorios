package com.example.demo.estudiante.infraestructure.controler;


import com.example.demo.estudiante.application.port.EstudianteServicePort;
import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("estudiante")
public class ControladorInsertarEstudiante {
    @Autowired
    EstudianteServicePort estudianteService;

    @PostMapping(value="/addEstudiante")
    @Transactional(rollbackOn = Exception.class)
    public EstudianteOutputDto addEstudiante(@RequestBody EstudianteInputDto estudiante ) throws Exception {
        return estudianteService.insertaEstudiante(estudiante);
        //return null;
        // se lanza exception si no se puede insertar
    }
}
