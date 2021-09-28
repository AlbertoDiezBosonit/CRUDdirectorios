package com.example.demo.profesor.infraestructure.Dto.controler;


import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import com.example.demo.profesor.application.port.ProfesorServicePort;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("profesor")
public class ControladorBuscarProfesor {
    @Autowired
    ProfesorServicePort profesorService;


    @GetMapping
    public List<ProfesorOutputDto> retornaTodas(){
        return profesorService.retornaTodos();
    }
}
