package com.example.demo.estudiante.infraestructure.controler;


import com.example.demo.estudiante.application.port.EstudianteServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("estudiante")
public class ControladorEliminarEstudiante {
    @Autowired
    EstudianteServicePort estudianteService;

    @DeleteMapping("/{id}")
    @Transactional(rollbackOn = Exception.class)
    public void borraEstudiante(@PathVariable String id) throws Exception{
        estudianteService.eliminaEstudiantePorId(id);
        // se lanza excepcion si no se encuentra, en caso de salir bien no retorna nada
    }
}
