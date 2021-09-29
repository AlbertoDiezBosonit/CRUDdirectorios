package com.example.demo.estudiante_asignatura.application;

import com.example.demo.estudiante.application.port.EstudianteRepositoryPort;
import com.example.demo.estudiante.application.port.EstudianteServicePort;
import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.estudiante_asignatura.application.port.Estudiante_asignaturaRepositoryPort;
import com.example.demo.estudiante_asignatura.application.port.Estudiante_asignaturaServicePort;
import com.example.demo.estudiante_asignatura.domain.Estudiante_Asignatura;
import com.example.demo.estudiante_asignatura.infraestructure.Estudiante_asignaturaInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Estudiante_asignaturaService implements Estudiante_asignaturaServicePort {
    @Autowired
    Estudiante_asignaturaRepositoryPort estudiante_asignaturaRepositoryPort;
    @Autowired
    EstudianteRepositoryPort estudianteRepositoryPort;

    public EstudianteOutputDto addAsignaturas(String id,List<Estudiante_asignaturaInputDto> asignaturas){
        // cogemos una lista de asignaturas y las introducimos
        Estudiante estudiante=estudianteRepositoryPort.getById(id);
        asignaturas.stream().forEach(e -> {
            Estudiante_Asignatura estudiante_asignatura=e.to_Estudiante_Asignatura();
            estudiante_asignatura.setEstudiante(estudiante);
            estudiante_asignaturaRepositoryPort.save(estudiante_asignatura);
        });
        return new EstudianteOutputDto(estudiante);
    }

    public   Integer deleteAsignaturas(String id, List<String> asignaturas){
        asignaturas.stream().forEach(s -> estudiante_asignaturaRepositoryPort.deleteById(s));
        return asignaturas.size();
    }
}
