package com.example.demo.estudiante.application.port;

import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDtoFull;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstudianteServicePort {

    EstudianteOutputDto insertaEstudiante(EstudianteInputDto estudiante) throws NotFoundException;

    List<EstudianteOutputDto> retornaTodos();

    EstudianteOutputDto actualizaEstudiante(String id, EstudianteInputDto persona);

    void eliminaEstudiantePorId(String id);

    EstudianteOutputDto retornaPorIdOutput(String id);

    EstudianteOutputDtoFull retornaPorIdOutputFull(String id);
}
