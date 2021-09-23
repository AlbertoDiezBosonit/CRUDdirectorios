package com.example.demo.persona.application;

import com.example.demo.persona.application.port.PersonaRepositoryPort;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.BeanUnprocesableException;
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
    public PersonaOutputDto insertaPersona(Persona p) {
        p.setActive("activo");
        p.setCreated_date(new java.sql.Date(new java.util.Date().getTime()));
        if(validaPersona(p)) {
            personaRepositoryPort.save(p);
            return new PersonaOutputDto(p);
        }
        return null;

    }

    @Override
    public PersonaOutputDto insertaPersona(PersonaInputDto p) {
        // vamos a eliminar el id para insertarlo en la base de datos
        return insertaPersona(p.toPersona());
    }

    @Override
    public  PersonaOutputDto actualizaPersona(Long id, PersonaInputDto p){
        Persona persona;
        try {
            persona = personaRepositoryPort.getById(id);
            persona=p.toPersona(persona);
        }catch (EntityNotFoundException e){
            return null;
        }

        //persona.setCreated_date(new java.sql.Date(new java.util.Date().getTime()));
        return actualizaPersona(persona);
        //return persona==personaRepository.save(persona);
    }

    @Override
    public  PersonaOutputDto actualizaPersona(Persona p) {
        p.setActive("true");
        if(this.validaPersona(p)) {
            personaRepositoryPort.save(p);
            return new PersonaOutputDto(p);
        }
        return null;
    }

    @Override
    public void eliminaPersona(Persona p) {
        personaRepositoryPort.delete(p);
    }

    @Override
    public void eliminaPersona(PersonaInputDto p) {
        personaRepositoryPort.delete(p.toPersona());
    }

    @Override
    public boolean eliminaPersonaPorId(Long id){
        try {
            Persona p = personaRepositoryPort.getById(id);
            if (p.getUser() != null) { // hasta que no se hae un get que no sea del id no salta la excepcion
                personaRepositoryPort.delete(p);
                return true;
            }
            return false;
        }catch(EntityNotFoundException e){
            return false;
        }
    }




    @Override
    public List<Persona> listaPersonas() {
        if(personaRepositoryPort !=null)
            return personaRepositoryPort.findAll();
        return null;
    }

    @Override
    public List<PersonaOutputDto> listaPersonasOutput(){
        if(personaRepositoryPort !=null) {
            return personaRepositoryPort.findAll().stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Optional<Persona> retornaPorId(Long id) {
        Optional<Persona> retorno= personaRepositoryPort.findById(id);
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
