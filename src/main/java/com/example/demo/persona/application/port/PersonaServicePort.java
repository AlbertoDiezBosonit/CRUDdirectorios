package com.example.demo.persona.application.port;

import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //el service es necesario
public interface PersonaServicePort {


    // ahora toda la logica de persona
    boolean validaPersona(Persona p);

    PersonaOutputDto insertaPersona(PersonaInputDto p);

    boolean eliminaPersonaPorId(/*Long*/String id);

    PersonaOutputDto actualizaPersona(/*Long*/String id,PersonaInputDto p);

    List<Persona> listaPersonas();

    List<PersonaOutputDto> listaPersonasOutput();

    Persona retornaPorId( /*Long*/String id);

    PersonaOutputDto retornaPorIdOutput( /*Long*/String id);

    List<PersonaOutputDto> retornaPorUserOutput( String user);

    List<Persona> mostrarPorNombre(String nombre);

    List<PersonaOutputDto> mostrarPorNombreOutput(String nombre);


}
