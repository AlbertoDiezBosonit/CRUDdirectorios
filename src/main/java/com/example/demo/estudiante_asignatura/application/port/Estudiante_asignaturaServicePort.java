package com.example.demo.estudiante_asignatura.application.port;

import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.estudiante_asignatura.infraestructure.Estudiante_asignaturaInputDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface Estudiante_asignaturaServicePort {
    EstudianteOutputDto addAsignaturas(String id,List<Estudiante_asignaturaInputDto> asignaturas);

    Integer deleteAsignaturas(String id, List<String> asignaturas);
}
