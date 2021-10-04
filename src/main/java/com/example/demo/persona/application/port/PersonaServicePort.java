package com.example.demo.persona.application.port;

import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.dto.PersonaInputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDtoFull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //el service es necesario
public interface PersonaServicePort {


    // ahora toda la logica de persona
    boolean validaPersona(Persona p);

    PersonaOutputDto insertaPersona(PersonaInputDto p);

    boolean eliminaPersonaPorId(/*Long*//*String*/Integer id);

    PersonaOutputDto actualizaPersona(/*Long*/Integer id,PersonaInputDto p);

    List<Persona> listaPersonas();

    List<PersonaOutputDto> listaPersonasOutput();

    Persona retornaPorId( /*Long*/Integer id);

    PersonaOutputDto retornaPorIdOutput( /*Long*/Integer id);

    List<PersonaOutputDto> retornaPorUserOutput( Integer user);

    List<Persona> mostrarPorNombre(Integer nombre);

    List<PersonaOutputDto> mostrarPorNombreOutput(Integer nombre);


    PersonaOutputDtoFull retornaPorIdOutputFull(Integer id);
}
