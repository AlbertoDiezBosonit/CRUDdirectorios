package com.example.demo.estudiante.infraestructure.dto;

import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


public class EstudianteOutputDtoFull extends EstudianteOutputDto{
    String user ;
    String password ;
    String name ;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCompany_email() {
        return company_email;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public String getCity() {
        return city;
    }

    public String getActive() {
        return active;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public Date getTermination_date() {
        return termination_date;
    }

    String surname ;
    String company_email;
    String personal_email;
    String city;
    String active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public void setTermination_date(Date termination_date) {
        this.termination_date = termination_date;
    }





    public EstudianteOutputDtoFull(Estudiante estudiante,Persona p) {
        super(estudiante);
        this.user=p.getUser();
        this.password=p.getPassword();
        this.name=p.getName();
        this.surname=p.getSurname();
        this.company_email=p.getCompany_email();
        this.personal_email=p.getPersonal_email();
        this.city=p.getCity();
        this.active=p.getActive();
        this.created_date=p.getCreated_date();
        this.imagen_url=p.getImagen_url();
        this.termination_date=p.getTermination_date();
    }


}
