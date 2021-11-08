package com.example.demo.estudiante.infraestructure.controler;


import com.example.demo.estudiante.application.port.EstudianteServicePort;
import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.estudiante_asignatura.application.port.Estudiante_asignaturaServicePort;
import com.example.demo.estudiante_asignatura.infraestructure.Estudiante_asignaturaInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("estudiante")
public class ControladorAsignaturasEstudiante {
    @Autowired
    Estudiante_asignaturaServicePort estudiante_asignaturaServicePort;

    @PostMapping("addAsignatura/{id}") // actualizamos la persona, hay que estar atentos a la id
    @Transactional(rollbackOn = Exception.class)
    public EstudianteOutputDto addAsignatura(@PathVariable String id, @RequestBody List<Estudiante_asignaturaInputDto> asignaturas) throws Exception {
        return estudiante_asignaturaServicePort.addAsignaturas(id,asignaturas);
        // se lanza excepcion si no se encuentra
    }

    @DeleteMapping("DeleteAsignatura/{id}") // actualizamos la persona, hay que estar atentos a la id
    @Transactional(rollbackOn = Exception.class)
    public Integer deleteAsignatura(@PathVariable String id, @RequestBody List<String> asignaturas) throws Exception {
        return estudiante_asignaturaServicePort.deleteAsignaturas(id,asignaturas);
        // se lanza excepcion si no se encuentra
    }
}
