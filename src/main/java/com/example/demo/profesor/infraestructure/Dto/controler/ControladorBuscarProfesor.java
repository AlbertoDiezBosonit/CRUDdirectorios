package com.example.demo.profesor.infraestructure.Dto.controler;


import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import com.example.demo.profesor.application.port.ProfesorServicePort;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ProfesorOutputDto retorna(@PathVariable String id, @RequestParam(value = "outputType",defaultValue = "simple") String itemid){
        if(itemid.equals("simple"))
            return profesorService.retornaPorIdOutput(id);
        else if(itemid.equals("full"))
            return profesorService.retornaPorIdOutputFull(id);
        return null;
        // se lanza excepcion si no se encuentra
    }
}
