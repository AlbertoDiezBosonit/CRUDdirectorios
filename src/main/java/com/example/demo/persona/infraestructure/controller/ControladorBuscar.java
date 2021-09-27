package com.example.demo.persona.infraestructure.controller;


import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("persona")
public class ControladorBuscar {
    @Autowired
    PersonaServicePort personaService;

    @GetMapping("/{id}")
    public PersonaOutputDto retorna(@PathVariable /*Long*/String id){
        return personaService.retornaPorIdOutput(id);
        // se lanza excepcion si no se encuentra
    }

    @GetMapping("/nombre/{id}")
    public List<PersonaOutputDto> mostrarPorNombre2(@PathVariable String id){
        return personaService.mostrarPorNombreOutput(id);
    }

    @GetMapping("/user/{id}")
    public List<PersonaOutputDto> mostrarPorUser(@PathVariable String id){
        return personaService.retornaPorUserOutput(id);
    }

    @GetMapping
    public List<PersonaOutputDto> retornaTodas(){
        return personaService.listaPersonasOutput();
    }
}
