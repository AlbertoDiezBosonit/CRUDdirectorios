package com.example.demo.estudiante.application.port;

import com.example.demo.estudiante.application.port.EstudianteServicePort;
import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.exception.BeanUnprocesableException;
import com.example.demo.persona.application.port.PersonaRepositoryPort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements EstudianteServicePort {
    @Autowired
    EstudianteRepositoryPort estudianteRepositoryPort;

    @Autowired
    PersonaRepositoryPort personaRepositoryPort;

    private boolean validaEstudiante(EstudianteInputDto estudiante) {
        return true;
    }

    @Override
    public EstudianteOutputDto insertaEstudiante(EstudianteInputDto estudiante) throws  NotFoundException {
         if(validaEstudiante(estudiante)) {
             Persona p=personaRepositoryPort.getById(estudiante.getId_persona());
             if(p==null){
                throw new NotFoundException("No se ha encontrado persona para ese estudiante");
             }
             Estudiante e= estudiante.toEstudiante();
             e.setPersona(p);
                e=estudianteRepositoryPort.save(e);
                return new EstudianteOutputDto(e);
         }
         throw new BeanUnprocesableException("No se ha podido insertar el estudiante");
   }



}
