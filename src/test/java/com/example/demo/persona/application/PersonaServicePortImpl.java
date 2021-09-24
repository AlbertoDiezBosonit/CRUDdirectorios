package com.example.demo.persona.application;

import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.domain.PersonaRepository;
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
    PersonaRepository personaRepository;


    @Override
    public boolean validaPersona(Persona p){
        String mensajeError="";
        if(6> p.getUser().length() || 10<p.getUser().length())
            mensajeError+="La longitud de User no es la correcta"+System.lineSeparator();
        if(p.getPassword()==null)
            mensajeError+="No hay mensaje de error"+System.lineSeparator();
        if( p.getName()==null)
            mensajeError+="No hay nombre"+System.lineSeparator();
        if( p.getCity()==null)
            mensajeError+="No se ha indicado una ciudad"+System.lineSeparator();
        if( p.getCreated_date()==null)
            mensajeError+="No se ha aportado una fecha de creación"+System.lineSeparator();
        if(!p.getCompany_email().contains("@"))
            mensajeError+="No se ha aportado un email de empresa correcto"+System.lineSeparator();
        if(!p.getPersonal_email().contains("@"))
            mensajeError+="No se ha aportado un email personal correcto"+System.lineSeparator();
        if(!mensajeError.equals(""))
            throw new BeanUnprocesableException(mensajeError);
        return true;
    }

    @Override
    public PersonaOutputDto insertaPersona(PersonaInputDto pi) {
        // vamos a eliminar el id para insertarlo en la base de datos
        Persona p=pi.toPersona();
        p.setActive("activo");
        p.setCreated_date(new java.sql.Date(new java.util.Date().getTime()));
        if(validaPersona(p)) {
            personaRepository.save(p);
            return new PersonaOutputDto(p);
        }
        return null;
    }

    @Override
    public  PersonaOutputDto actualizaPersona(Long id,PersonaInputDto p){
        Persona persona;
        try {
            persona = personaRepository.getById(id);
            persona=p.toPersona(persona);
        }catch (EntityNotFoundException e){
            throw new BeanNotFoundException("No se ha encontrado la persona a actualizar");
        }

        //persona.setCreated_date(new java.sql.Date(new java.util.Date().getTime()));
        if(this.validaPersona(persona)) {
            personaRepository.save(persona);
            return new PersonaOutputDto(persona);
        }
        throw new BeanNotFoundException("No se ha encontrado la persona a actualizar");
        // no llegaremos nunca hasta aqui

    }


    @Override
    public boolean eliminaPersonaPorId(Long id){
        try {
            Persona p = personaRepository.getById(id);
            if (p.getUser() != null) { // hasta que no se hae un get que no sea del id no salta la excepcion
                personaRepository.delete(p);
                return true;
            }
            return false;
        }catch(EntityNotFoundException e){
            return false;
        }
    }




    @Override
    public List<Persona> listaPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public List<PersonaOutputDto> listaPersonasOutput(){
        return personaRepository.findAll().stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<Persona> retornaPorId(Long id) {
        Optional<Persona> retorno= personaRepository.findById(id);
        if(retorno.isEmpty())
            return null;
        return retorno;
    }

   @Override
   public PersonaOutputDto retornaPorIdOutput( Long id){
        Optional<Persona> p=retornaPorId(id);
        if (p!=null && !p.isEmpty()) {
            return new PersonaOutputDto(p.get());
        }
        return null;
   }

    @Override
    public List<Persona>  mostrarPorNombre(String nombre) {
        return personaRepository.findByName(nombre);
    }

    @Override
    public List<PersonaOutputDto> mostrarPorNombreOutput(String nombre){
        return personaRepository.findByName(nombre).stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
    }

    @Override
    public List<PersonaOutputDto> retornaPorUserOutput( String user){
        return personaRepository.findByUser(user).stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
    }
}
