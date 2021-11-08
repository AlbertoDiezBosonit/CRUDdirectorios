package com.example.demo.profesor.infraestructure.Dto;


import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.profesor.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class ProfesorInputDto {
    String coments,branch;
    String id_persona;
    List<Integer> estudiantes;

    public Profesor toProfesor(Profesor p){
        if(this.getBranch()!=null)
            p.setBranch(this.getBranch());
        if(this.getComents()!=null)
            p.setComents(this.getComents()) ;
        //if(this.getId_persona()!=null)
          //  p.set
        return p;
    }

    public Profesor toProfesor() {
        Profesor retorno=new Profesor();
        return this.toProfesor(retorno);
    }
}
