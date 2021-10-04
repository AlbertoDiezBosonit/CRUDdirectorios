package com.example.demo.persona.infraestructure.controller;


import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class ControladorBuscar {
    @Autowired
    PersonaServicePort personaService;



    @GetMapping("/nombre/{id}")
    public List<PersonaOutputDto> mostrarPorNombre2(@PathVariable Integer id){
            return personaService.mostrarPorNombreOutput(id);
    }

    @GetMapping("/{id}")
    public PersonaOutputDto retorna(@PathVariable Integer id, @RequestParam(value = "outputType",defaultValue = "simple") String itemid) {
        if(itemid.equals("simple"))
            return personaService.retornaPorIdOutput(id);
        else if(itemid.equals("full"))
            return personaService.retornaPorIdOutputFull(id);
        return null;
        // se lanza excepcion si no se encuentra
    }

    @GetMapping
    public List<PersonaOutputDto> retornaTodas(){
        return personaService.listaPersonasOutput();
    }
}
