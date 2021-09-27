package com.example.demo.estudiante.application.port;

import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface EstudianteServicePort {

    EstudianteOutputDto insertaEstudiante(EstudianteInputDto estudiante) throws NotFoundException;
}
