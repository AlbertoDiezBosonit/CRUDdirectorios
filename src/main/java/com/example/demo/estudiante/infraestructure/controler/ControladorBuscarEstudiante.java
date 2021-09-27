package com.example.demo.estudiante.infraestructure.controler;


import com.example.demo.estudiante.application.port.EstudianteServicePort;
import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("estudiante")
public class ControladorBuscarEstudiante {

    @Autowired
    EstudianteServicePort estudianteService;


    @GetMapping
    public List<EstudianteOutputDto> retornaTodds() {
        return estudianteService.retornaTodos();
    }

    @GetMapping("/{id}")
    public EstudianteOutputDto retorna(@PathVariable /*Long*/String id){
        return estudianteService.retornaPorIdOutput(id);
        // se lanza excepcion si no se encuentra
    }


}
