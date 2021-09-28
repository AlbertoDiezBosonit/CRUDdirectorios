package com.example.demo.persona.application;

import com.example.demo.persona.application.port.PersonaRepositoryPort;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.exception.BeanNotFoundException;
import com.example.demo.exception.BeanUnprocesableException;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //el service es necesario
public class PersonaServicePortImpl implements PersonaServicePort {

    @Autowired
    PersonaRepositoryPort personaRepositoryPort;


    @Override
    public boolean validaPersona(Persona p){
        if(6> p.getUser().length() || 10<p.getUser().length())
            throw new BeanUnprocesableException("La longitud de User no es la correcta");
        if(p.getPassword()==null)
            throw new BeanUnprocesableException("No hay mensaje de error");
        if( p.getName()==null)
            throw new BeanUnprocesableException("No hay nombre");
        if( p.getCity()==null)
            throw new BeanUnprocesableException("No se ha indicado una ciudad");
        if( p.getCreated_date()==null)
            throw new BeanUnprocesableException("No se ha aportado una fecha de creación");
        if(!p.getCompany_email().contains("@"))
            throw new BeanUnprocesableException("No se ha aportado un email de empresa correcto");
        if(!p.getPersonal_email().contains("@"))
            throw new BeanUnprocesableException("No se ha aportado un email personal correcto");
        return true;
    }

    @Override
    public PersonaOutputDto insertaPersona(PersonaInputDto pi) {
        // vamos a eliminar el id para insertarlo en la base de datos
        Persona p=pi.toPersona();
        p.setActive("activo");
        p.setCreated_date(new java.sql.Date(new java.util.Date().getTime()));
        if(validaPersona(p)) {
            personaRepositoryPort.save(p);
            return new PersonaOutputDto(p);
        }
        throw new BeanUnprocesableException("No se ha podido insertar la persona");

    }

    @Override
    public  PersonaOutputDto actualizaPersona(/*Long*/String id, PersonaInputDto p){
        Persona persona;
        try {
            persona = personaRepositoryPort.getById(id);
            persona=p.toPersona(persona);
        }catch (EntityNotFoundException e){
            throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
        }

        //persona.setCreated_date(new java.sql.Date(new java.util.Date().getTime()));
        if(this.validaPersona(persona)) {
            personaRepositoryPort.save(persona);
            return new PersonaOutputDto(persona);
        }
        throw new BeanNotFoundException("No se ha podido actualizar por que no se ha encontrado");
        //return persona==personaRepository.save(persona);
    }

    @Override
    public boolean eliminaPersonaPorId(/*Long id*/String id){
        try {
            Persona p = personaRepositoryPort.getById(id);
            if (p.getUser() != null) { // hasta que no se hae un get que no sea del id no salta la excepcion
                personaRepositoryPort.delete(p);
                return true;
            }
            throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
        }catch(EntityNotFoundException e){
            throw new BeanNotFoundException("No se ha podido eliminar por que no se ha encontrado");
        }
    }

    @Override
    public List<Persona> listaPersonas() {
        return personaRepositoryPort.findAll();
    }

    @Override
    public List<PersonaOutputDto> listaPersonasOutput(){
        return personaRepositoryPort.findAll().stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
    }

    @Override
    public Persona retornaPorId(/*Long id*/ String id) throws BeanNotFoundException {
      //  if(id!=null)
        //    throw new BeanNotFoundException("No se ha encontrado registro con esa id");
      //  System.out.println("hola "+id);
        Optional<Persona> retorno= personaRepositoryPort.findById(id);
      //  System.out.println("hola");
        if(retorno!=null )
            return retorno.get();
        throw new BeanNotFoundException("No se ha encontrado registro con esa id");
    }

   @Override
   public PersonaOutputDto retornaPorIdOutput(/* Long id*/String id){
        Persona p=retornaPorId(id);
        if (p!=null ) {
            return new PersonaOutputDto(p);
        }
        throw new BeanNotFoundException("No se ha encontrado registro con esa id");
   }

    @Override
    public List<Persona>  mostrarPorNombre(String nombre) {
        return personaRepositoryPort.findByName(nombre);
    }

    @Override
    public List<PersonaOutputDto> mostrarPorNombreOutput(String nombre){
        return personaRepositoryPort.findByName(nombre).stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
    }

    @Override
    public List<PersonaOutputDto> retornaPorUserOutput( String user){
        return personaRepositoryPort.findByUser(user).stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
    }
}
