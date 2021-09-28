package com.example.demo.estudiante_asignatura.infraestructure;

import com.example.demo.estudiante_asignatura.domain.Estudiante_Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante_asignaturaInputDto {
    String asignatura,coments;
    Date initial_date,finish_date;

    public Estudiante_Asignatura to_Estudiante_Asignatura(){
        Estudiante_Asignatura retorno=new Estudiante_Asignatura();
        retorno.setAsignatura(this.getAsignatura());
        retorno.setComents(this.getComents());
        retorno.setInitial_date(this.getInitial_date());
        retorno.setFinish_date(this.getFinish_date());
        return retorno;
    }
}
