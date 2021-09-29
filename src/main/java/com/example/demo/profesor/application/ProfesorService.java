package com.example.demo.profesor.application;

import com.example.demo.estudiante.application.port.EstudianteRepositoryPort;
import com.example.demo.exception.BeanNotFoundException;
import com.example.demo.exception.BeanUnprocesableException;
import com.example.demo.persona.application.port.PersonaRepositoryPort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import com.example.demo.profesor.application.port.ProfesorRepositoryPort;
import com.example.demo.profesor.application.port.ProfesorServicePort;
import com.example.demo.profesor.domain.Profesor;
import com.example.demo.profesor.infraestructure.Dto.ProfesorInputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDtoFull;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Persona p=personaRepositoryPort.getById(profesor.getId_persona());
           if(!personaRepositoryPort.existsById(profesor.getId_persona())) {
                throw new NotFoundException("No se ha encontrado persona para ese estudiante");
            }

            if (estudianteRepositoryPort.findByPersona(p)!=null) {
                throw new BeanUnprocesableException("Ya hay una persona asignada a un estudiante");
            }

            if (profesorRepositoryPort.findByPersona(p)!=null) {
                throw new BeanUnprocesableException("Ya hay una persona asignada a un profesor");
            }

            Profesor pro= profesor.toProfesor();
            pro.setPersona(p);
            // no hace falta actualizar los estudiantes, lo hace directamente jpa
            pro=profesorRepositoryPort.save(pro);
            return new ProfesorOutputDto(pro);
        }
        throw new BeanUnprocesableException("No se ha podido insertar el profesor");
    }

    @Override
    public List<ProfesorOutputDto> retornaTodos() {
        return profesorRepositoryPort.findAll().stream().map(p -> new ProfesorOutputDto(p)).collect(Collectors.toList());
    }

    @Override
    public ProfesorOutputDto actualizaProfesor(String id, ProfesorInputDto profesor) throws NotFoundException {
        Profesor pro;
        pro=profesorRepositoryPort.getById(id);

        if(this.valida_profesor(profesor)){
            if(!personaRepositoryPort.existsById(profesor.getId_persona())) {
               // System.out.println("No encontrado "+profesor.getId_persona());
                throw new NotFoundException("No se ha encontrado persona para ese estudiante");
            }
            if(pro.getPersona().getId_persona()!=profesor.getId_persona()) {
                if (null != profesorRepositoryPort.findByPersona(personaRepositoryPort.getById(profesor.getId_persona()))) {
                    throw new BeanUnprocesableException("Ya hay una asignada");
                }
            }
            Persona p=personaRepositoryPort.getById(profesor.getId_persona());
            pro=profesor.toProfesor(pro);
            pro.setPersona(p);
            // no hace falta actualizar los estudiantes, lo hace directamente jpa
            pro=profesorRepositoryPort.save(pro);
            return new ProfesorOutputDto(pro);
        }
        throw new BeanUnprocesableException("No se ha podido insertar el profesor");
    }

    @Override
    public void eliminaProfesorPorId(String id) {
        try {
            Profesor p = profesorRepositoryPort.getById(id);
            profesorRepositoryPort.delete(p);
        }catch(EntityNotFoundException e){
            e.printStackTrace();
            throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
        }
    }

    //@Override
    public Profesor retornaPorId(String id) {
        Optional<Profesor> retorno= profesorRepositoryPort.findById(id);
        if(retorno!=null )
            return retorno.get();
        throw new BeanNotFoundException("No se ha encontrado registro con esa id");
    }

    @Override
    public ProfesorOutputDto retornaPorIdOutput(/* Long id*/String id){
        Profesor p=retornaPorId(id);
        if (p!=null ) {
            return new ProfesorOutputDto(p);
        }
        throw new BeanNotFoundException("No se ha encontrado registro con esa id");
    }


    public ProfesorOutputDtoFull retornaPorIdOutputFull(String id){
        Optional<Profesor> retorno= profesorRepositoryPort.findById(id);
        if(retorno!=null ) {
            return new ProfesorOutputDtoFull(retorno.get(),new PersonaOutputDto(retorno.get().getPersona() ));
        }
        throw new BeanNotFoundException("No se ha encontrado registro con esa id");
    }
}
