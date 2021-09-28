package com.example.demo.persona.infraestructure.dto;

import com.example.demo.estudiante.infraestructure.dto.EstudianteOutputDto;
import com.example.demo.persona.domain.Persona;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;

public class PersonaOutputDtoFull extends  PersonaOutputDto{
    EstudianteOutputDto estudiante;

    public EstudianteOutputDto getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteOutputDto estudiante) {
        this.estudiante = estudiante;
    }

    public ProfesorOutputDto getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorOutputDto profesor) {
        this.profesor = profesor;
    }

    ProfesorOutputDto profesor;

    public PersonaOutputDtoFull(Persona p) {
        super(p);
    }
}
