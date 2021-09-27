package com.example.demo.estudiante.infraestructure.dto;


import com.example.demo.estudiante.domain.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstudianteOutputDto {
    //Long id;
    String id;
    Long id_persona;
    Long num_hours_week;
    String comments;
    String branch;

    public EstudianteOutputDto(Estudiante estudiante){
        this.id=estudiante.getId();
        this.id_persona=estudiante.getPersona().getId();
        this.num_hours_week=estudiante.getNum_hours_week();
        this.comments=estudiante.getComments();
        this.branch=estudiante.getBranch();
    }

}
