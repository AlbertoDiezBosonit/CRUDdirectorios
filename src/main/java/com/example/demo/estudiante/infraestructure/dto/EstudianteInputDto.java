package com.example.demo.estudiante.infraestructure.dto;


import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.persona.application.PersonaServicePortImpl;
import com.example.demo.persona.application.PersonaServicePortImpl;
//import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.domain.Persona;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Data
//@AllArgsConstructor
@Component
public class EstudianteInputDto {


    //Long id_persona;
    Integer id_persona;
    Long num_hours_week;
    String comments;
    String branch;

    public Estudiante toEstudiante() throws NotFoundException{
        Estudiante retorno=new Estudiante();

        //Long i=this.id_persona;

        retorno.setNum_hours_week(this.getNum_hours_week());
        retorno.setComments(this.getComments());
        retorno.setBranch(this.getBranch());
        return retorno;
    }

    public Estudiante toEstudiante(Estudiante retorno){
        /*if(this.id_persona!=null) {
            Persona p = personaService.retornaPorId(this.id_persona);
            if (p != null) // si no la encontramos podríamos lanzar una excepcion
                retorno.setPersona(p);
        }*/
       // if(this.getId_persona()!=null)
         //   retorno.getPersona().setId_persona(this.getId_persona());
        if(this.getNum_hours_week()!=null)
            retorno.setNum_hours_week(this.getNum_hours_week());
        if(this.getComments()!=null)
            retorno.setComments(this.getComments());
        if(this.getBranch()!=null)
            retorno.setBranch(this.getBranch());
        return retorno;
    }

}
