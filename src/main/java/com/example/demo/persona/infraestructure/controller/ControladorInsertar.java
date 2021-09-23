//package application;
package com.example.demo.persona.infraestructure.controller;


import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("persona")
public class ControladorInsertar {
    @Autowired
    PersonaServicePort personaService;

    @PostMapping(value="/addPersona")
    @Transactional(rollbackOn = Exception.class)
    public PersonaOutputDto addPersona(/*@ModelAttribute */ @RequestBody PersonaInputDto persona ) throws Exception {
        return personaService.insertaPersona(persona);
        // se lanza exception si no se puede insertar
    }




}
