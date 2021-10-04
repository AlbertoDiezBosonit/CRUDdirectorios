package com.example.demo.persona.infraestructure.dto;


import com.example.demo.persona.domain.Persona;
import lombok.*;

import java.sql.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto {
    // clase DTO para el tipo persona

    //Long id;
    Integer id;
    String user ;
    String password ;
    String name ;
    String surname ;
    String company_email;
    String personal_email;
    String city;
    String imagen_url;
    Date termination_date;

    public PersonaOutputDto(Persona p){
        if(p!=null) {
            if(p.getId_persona()!=null)
                this.setId(p.getId_persona());
            this.setUser(p.getUsere());
            this.setPassword(p.getPassword());
            this.setName(p.getName());
            this.setSurname(p.getSurname());
            this.setCompany_email(p.getCompany_email());
            this.setPersonal_email(p.getPersonal_email());
            this.setCity(p.getCity());
            this.setImagen_url(p.getImagen_url());
            this.setTermination_date(p.getTermination_date());
        }
    }

    public Persona toPersona(){
        Persona p=new Persona();
        if(this.getId()!=null)
            p.setId_persona(this.getId());
        p.setUsere(this.getUser());
        p.setPassword(this.getPassword());
        p.setName(this.getName());
        p.setSurname(this.getSurname());
        p.setCompany_email(this.getCompany_email());
        p.setPersonal_email(this.getPersonal_email());
        p.setCity(this.getCity());
        p.setImagen_url(this.getImagen_url());
        p.setTermination_date(this.getTermination_date());
        return p;
    }

    public Persona toPersona(Persona p){
        if(p==null)
            return this.toPersona();
        if(this.getId()!=null)
            p.setId_persona(this.getId());
        p.setUsere(this.getUser());
        p.setPassword(this.getPassword());
        p.setName(this.getName());
        p.setSurname(this.getSurname());
        p.setCompany_email(this.getCompany_email());
        p.setPersonal_email(this.getPersonal_email());
        p.setCity(this.getCity());
        p.setImagen_url(this.getImagen_url());
        p.setTermination_date(this.getTermination_date());
        return p;
    }


}
