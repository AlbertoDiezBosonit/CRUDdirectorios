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

    EstudianteOutputDto actualizaEstudiante(Integer id, EstudianteInputDto persona);

    void eliminaEstudiantePorId(Integer id);

    EstudianteOutputDto retornaPorIdOutput(Integer id);

    EstudianteOutputDtoFull retornaPorIdOutputFull(Integer id);
}
