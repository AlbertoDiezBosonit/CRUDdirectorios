package com.example.demo.profesor.infraestructure.Dto;

import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import com.example.demo.profesor.domain.Profesor;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;

public class ProfesorOutputDtoFull extends ProfesorOutputDto {
    public PersonaOutputDto getPersona() {
        return this.personaOutputDto;
    }

    public void setPersona(PersonaOutputDto p) {
        this.personaOutputDto = p;
    }

    PersonaOutputDto personaOutputDto;

    public ProfesorOutputDtoFull(Profesor profesor) {
        super(profesor);
    }

    public ProfesorOutputDtoFull(Profesor profesor,PersonaOutputDto persona) {
        super(profesor);
        this.personaOutputDto=persona;
    }
}
