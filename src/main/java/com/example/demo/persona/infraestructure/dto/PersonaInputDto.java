package com.example.demo.persona.infraestructure.dto;

import com.example.demo.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class PersonaInputDto {
    // clase DTO para el tipo persona
//    Long id; // el input no debe tener un id
    String user ;
    String password ;
    String name ;
    String surname ;
    String company_email;
    String personal_email;
    String city;
    String imagen_url;
    Date termination_date;
    Date created_date;

    public PersonaInputDto(Persona p){
        if(p!=null){
            this.setUser(p.getUsere());
            this.setPassword(p.getPassword());
            this.setName(p.getName());
            this.setSurname(p.getSurname());
            this.setCompany_email(p.getCompany_email());
            this.setPersonal_email(p.getPersonal_email());
            this.setCity(p.getCity());
            this.setImagen_url(p.getImagen_url());
            this.setTermination_date(p.getTermination_date());
            this.setCreated_date(p.getCreated_date());
        }
    }

    public Persona toPersona(){
        Persona p=new Persona();
        p.setUsere(this.getUser());
        p.setPassword(this.getPassword());
        p.setName(this.getName());
        p.setSurname(this.getSurname());
        p.setCompany_email(this.getCompany_email());
        p.setPersonal_email(this.getPersonal_email());
        p.setCity(this.getCity());
        p.setImagen_url(this.getImagen_url());
        p.setTermination_date(this.getTermination_date());
        p.setCreated_date(this.getCreated_date());
        return p;
    }

    public Persona toPersona(Persona p){
        if(p==null)
            return this.toPersona();
        // solo actualizamos los datos que existen, no tienen por que estar todos
        if(this.getUser()!=null)
            p.setUsere(this.getUser());
        if(this.getPassword()!=null)
            p.setPassword(this.getPassword());
        if(this.getName()!=null)
            p.setName(this.getName());
        if(this.getSurname()!=null)
            p.setSurname(this.getSurname());
        if(this.getCompany_email()!=null)
            p.setCompany_email(this.getCompany_email());
        if(this.getPersonal_email()!=null)
            p.setPersonal_email(this.getPersonal_email());
        if(this.getCity()!=null)
            p.setCity(this.getCity());
        if(this.getImagen_url()!=null)
            p.setImagen_url(this.getImagen_url());
        if(this.getTermination_date()!=null)
            p.setTermination_date(this.getTermination_date());
        if(this.getCreated_date()!=null)
            p.setCreated_date(this.getCreated_date());
        return p;
    }

}
