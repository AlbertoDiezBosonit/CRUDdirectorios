package com.example.demo.persona.infraestructure.controller;


import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("persona")
public class ControladorActualizar {
    @Autowired
    PersonaServicePort personaService;

    @PutMapping("{id}") // actualizamos la persona, hay que estar atentos a la id
    @Transactional(rollbackOn = Exception.class)
    public PersonaOutputDto actualizar(@PathVariable /*Long*/String id, /*@ModelAttribute*/@RequestBody PersonaInputDto persona ) throws Exception {
        System.out.println(id);
        return personaService.actualizaPersona(id,persona);
        // se lanza excepcion si no se encuentra
    }
}
