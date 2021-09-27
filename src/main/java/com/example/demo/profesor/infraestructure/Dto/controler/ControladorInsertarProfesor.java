package com.example.demo.profesor.infraestructure.Dto.controler;


import com.example.demo.profesor.application.port.ProfesorServicePort;
import com.example.demo.profesor.infraestructure.Dto.ProfesorInputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("profesor")
public class ControladorInsertarProfesor {
    @Autowired
    ProfesorServicePort profesorServicePort;

    @PostMapping(value="/addProfesor")
    @Transactional(rollbackOn = Exception.class)
    public ProfesorOutputDto addPersona(@RequestBody ProfesorInputDto profesor ) throws Exception {
        return profesorServicePort.insertaProfesor(profesor);
        // se lanza exception si no se puede insertar
    }


}
