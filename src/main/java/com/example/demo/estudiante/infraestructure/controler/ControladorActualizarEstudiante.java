package com.example.demo.estudiante.infraestructure.controler;

import com.example.demo.estudiante.application.port.EstudianteServicePort;
import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@RequestMapping("estudiante")
public class ControladorActualizarEstudiante {
    @Autowired
    EstudianteServicePort estudianteService;

    @PutMapping("{id}") // actualizamos la persona, hay que estar atentos a la id
    @Transactional(rollbackOn = Exception.class)
    public EstudianteOutputDto actualizar(@PathVariable Integer id, @RequestBody EstudianteInputDto estudiante ) throws Exception {
        return estudianteService.actualizaEstudiante(id,estudiante);
        // se lanza excepcion si no se encuentra
    }
}
