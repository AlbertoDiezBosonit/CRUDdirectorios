package com.example.demo.profesor.application.port;

import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorInputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProfesorServicePort {
    ProfesorOutputDto insertaProfesor(ProfesorInputDto profesor) throws NotFoundException;

    List<ProfesorOutputDto> retornaTodos();

    ProfesorOutputDto actualizaProfesor(String id, ProfesorInputDto persona) throws NotFoundException;

    void eliminaProfesorPorId(String id);

    ProfesorOutputDto retornaPorIdOutput(String id);

    ProfesorOutputDto retornaPorIdOutputFull(String id);
}
