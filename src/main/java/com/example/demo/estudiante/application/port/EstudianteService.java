package com.example.demo.estudiante.application.port;

import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.estudiante.infraestructure.dto.EstudianteInputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDtoFull;
import com.example.demo.exception.BeanNotFoundException;
import com.example.demo.exception.BeanUnprocesableException;
import com.example.demo.persona.application.port.PersonaRepositoryPort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.profesor.application.port.ProfesorRepositoryPort;
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

    @Autowired
    ProfesorRepositoryPort profesorRepositoryPort;

    private boolean validaEstudiante(EstudianteInputDto estudiante) {
        return true;
    }

    @Override
    public EstudianteOutputDto insertaEstudiante(EstudianteInputDto estudiante) throws NotFoundException {
         if(validaEstudiante(estudiante)) {
             // estas comprobaciones se pueden hacer mas sencillas comprobando nulos en los campos de los objetos
             // aunque tambien depende si las tablas hijas se cargan automaticamente al cargar una fila,
             // depende de la configuración hecha desde las anotaciones
             Persona p=personaRepositoryPort.getById(estudiante.getId_persona());
             if(!personaRepositoryPort.existsById(estudiante.getId_persona())) {
                 throw new NotFoundException("No se ha encontrado persona para ese estudiante");
             }


                if (p != null) {
                    if (estudianteRepositoryPort.findByPersona(p)!=null) {
                        throw new BeanUnprocesableException("Ya hay una persona asignada a un estudiante");
                    }

                    if (profesorRepositoryPort.findByPersona(p)!=null) {
                        throw new BeanUnprocesableException("Ya hay una persona asignada a un profesor");
                    }
                }
             // con salvar el estudiante es suficiente, si hay que cambiar tablas hijas se supone que lo hace
             // automaticamente jpa

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
   public EstudianteOutputDto actualizaEstudiante(/*String*/Integer id, EstudianteInputDto e){
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

    public void eliminaEstudiantePorId(Integer id){
        try {
                estudianteRepositoryPort.deleteById(id);
        }catch(Exception e){
            throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
        }
    }

    public EstudianteOutputDto retornaPorIdOutput(Integer id){
        try {
            Optional<Estudiante> retorno = estudianteRepositoryPort.findById(id);
            return new EstudianteOutputDto(retorno.get());
        }catch(Exception e) {
            throw new BeanNotFoundException("No se ha encontrado registro de estudiante con esa id");
        }
    }

    public EstudianteOutputDtoFull retornaPorIdOutputFull(Integer id){
        Estudiante preRetorno=estudianteRepositoryPort.getById(id);
        Persona persona=preRetorno.getPersona();
        return new EstudianteOutputDtoFull(preRetorno,persona);
    }
}
