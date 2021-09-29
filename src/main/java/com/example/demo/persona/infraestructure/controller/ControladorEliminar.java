package com.example.demo.persona.infraestructure.controller;

import com.example.demo.persona.application.port.PersonaServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
@RequestMapping("persona")
public class ControladorEliminar {
    @Autowired
    PersonaServicePort personaService;


    @DeleteMapping("/{id}")
    @Transactional(rollbackOn = Exception.class)
    public void borraPersona(@PathVariable String id) throws Exception{
        personaService.eliminaPersonaPorId(id);
        // se lanza excepcion si no se encuentra, en caso de salir bien no retorna nada
    }
}
