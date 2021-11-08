package com.example.demo.profesor.infraestructure.Dto;


import com.example.demo.profesor.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDto {
    String coments,branch;
    String id_persona,id;
    public  ProfesorOutputDto(Profesor p){
        this.setId(p.getId_profesor());
        this.setId_persona(p.getPersona().getId_persona());
        this.setComents(p.getComents());
        this.setBranch(p.getBranch());
        this.setId(p.getId_profesor());
    }
}
