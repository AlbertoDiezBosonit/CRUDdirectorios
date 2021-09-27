package com.example.demo.profesor.application;

import com.example.demo.estudiante.application.port.EstudianteRepositoryPort;
import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.exception.BeanUnprocesableException;
import com.example.demo.persona.application.port.PersonaRepositoryPort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.profesor.application.port.ProfesorRepositoryPort;
import com.example.demo.profesor.application.port.ProfesorServicePort;
import com.example.demo.profesor.domain.Profesor;
import com.example.demo.profesor.infraestructure.Dto.ProfesorInputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService implements ProfesorServicePort {
    @Autowired
    EstudianteRepositoryPort estudianteRepositoryPort;

    @Autowired
    PersonaRepositoryPort personaRepositoryPort;

    @Autowired
    ProfesorRepositoryPort profesorRepositoryPort;


    private boolean valida_profesor(ProfesorInputDto profesor){
        return true;
    }

    @Override
    public ProfesorOutputDto insertaProfesor(ProfesorInputDto profesor) throws NotFoundException {
        if(this.valida_profesor(profesor)){
           if(!personaRepositoryPort.existsById(profesor.getId_persona())) {
                System.out.println("No encontrado");
                throw new NotFoundException("No se ha encontrado persona para ese estudiante");
            }
            if(null!=profesorRepositoryPort.findByPersona(personaRepositoryPort.getById(profesor.getId_persona()) )){
                throw new BeanUnprocesableException("Ya hay una asignada");
            }
            Persona p=personaRepositoryPort.getById(profesor.getId_persona());
            Profesor pro= profesor.toProfesor();
            pro.setPersona(p);
            pro=profesorRepositoryPort.save(pro);

            // ahora toca insertar los estudiantes
            return new ProfesorOutputDto(pro);

        }
        throw new BeanUnprocesableException("No se ha podido insertar el profesor");

    }

    @Override
    public List<ProfesorOutputDto> retornaTodos() {
        return null;
    }

    @Override
    public ProfesorOutputDto actualizaEstudiante(String id, ProfesorInputDto persona) {
        return null;
    }

    @Override
    public void eliminaProfesorPorId(String id) {

    }

    @Override
    public ProfesorOutputDto retornaPorIdOutput(String id) {
        return null;
    }
}
