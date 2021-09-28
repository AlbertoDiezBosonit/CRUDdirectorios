package com.example.demo.estudiante.application.port;

import com.example.demo.estudiante.application.port.EstudianteServicePort;
import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDtoFull;
import com.example.demo.exception.BeanNotFoundException;
import com.example.demo.exception.BeanUnprocesableException;
import com.example.demo.persona.application.port.PersonaRepositoryPort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public EstudianteOutputDto insertaEstudiante(EstudianteInputDto estudiante) throws NotFoundException {
         if(validaEstudiante(estudiante)) {
             if(!personaRepositoryPort.existsById(estudiante.getId_persona())) {
                 System.out.println("No encontrado");
                 throw new NotFoundException("No se ha encontrado persona para ese estudiante");
             }
             if(null!=estudianteRepositoryPort.findByPersona(personaRepositoryPort.getById(estudiante.getId_persona()) )){
                 throw new BeanUnprocesableException("Ya hay una asignada");
             }
             // quedaria tambien hacer si hay un profesor con esa persona asignada
             Persona p=personaRepositoryPort.getById(estudiante.getId_persona());
             Estudiante e= estudiante.toEstudiante();
             e.setPersona(p);
             e=estudianteRepositoryPort.save(e);
             return new EstudianteOutputDto(e);
         }
         throw new BeanUnprocesableException("No se ha podido insertar el estudiante");
   }


   @Override
   public List<EstudianteOutputDto> retornaTodos(){
       return estudianteRepositoryPort.findAll().stream().map(p -> new EstudianteOutputDto(p)).collect(Collectors.toList());
   }

   @Override
   public EstudianteOutputDto actualizaEstudiante(String id, EstudianteInputDto e){
       Estudiante estudiante;
       try {
           estudiante = estudianteRepositoryPort.getById(id);
           estudiante=e.toEstudiante(estudiante);
       }catch (EntityNotFoundException ex){
           throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
       }

       //persona.setCreated_date(new java.sql.Date(new java.util.Date().getTime()));
       if(this.validaEstudiante(e)) {
           Persona p=personaRepositoryPort.getById(e.getId_persona());
           if(null!=estudianteRepositoryPort.findByPersona(p )){
               throw new BeanUnprocesableException("Ya hay una asignada");
           }
           estudiante.setPersona(p);
           estudianteRepositoryPort.save(estudiante);
           return new EstudianteOutputDto(estudiante);
       }
       throw new BeanNotFoundException("No se ha podido actualizar por que no se ha encontrado");
   }

    public void eliminaEstudiantePorId(String id){
        try {

                estudianteRepositoryPort.deleteById(id);


           // throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
        }catch(Exception e){
            throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
        }
    }

    public EstudianteOutputDto retornaPorIdOutput(String id){
        try {
            Optional<Estudiante> retorno = estudianteRepositoryPort.findById(id);
            return new EstudianteOutputDto(retorno.get());
        }catch(Exception e) {
            throw new BeanNotFoundException("No se ha encontrado registro con esa id");
        }

    }

    public EstudianteOutputDtoFull retornaPorIdOutputFull(String id){
        Estudiante preRetorno=estudianteRepositoryPort.getById(id);
        Persona persona=preRetorno.getPersona();
        EstudianteOutputDtoFull retorno=new EstudianteOutputDtoFull(preRetorno,persona);
        return retorno;
    }



}
