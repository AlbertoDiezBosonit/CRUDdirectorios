package com.example.demo.profesor.infraestructure.Dto.controler;


import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import com.example.demo.profesor.application.port.ProfesorServicePort;
import com.example.demo.profesor.infraestructure.Dto.ProfesorInputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("profesor")
public class ControladorActualizarProfesor {
    @Autowired
    ProfesorServicePort profesorService;

    @PutMapping("{id}") // actualizamos la persona, hay que estar atentos a la id
    @Transactional(rollbackOn = Exception.class)
    public ProfesorOutputDto actualizar(@PathVariable String id, @RequestBody ProfesorInputDto p ) throws Exception {
        return profesorService.actualizaProfesor(id,p);
        // se lanza excepcion si no se encuentra
    }
}
