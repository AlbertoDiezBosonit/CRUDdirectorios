package com.example.demo.profesor.infraestructure.Dto.controler;


import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.profesor.application.port.ProfesorServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("profesor")
public class ControladorEliminarProfesor {

    @Autowired
    ProfesorServicePort profesorService;


    @DeleteMapping("/{id}")
    @Transactional(rollbackOn = Exception.class)
    public void borraProfesor(@PathVariable Integer id) throws Exception{
        profesorService.eliminaProfesorPorId(id);
        // se lanza excepcion si no se encuentra, en caso de salir bien no retorna nada
    }
}
