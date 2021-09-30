package com.example.demo.persona.domain;


import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.profesor.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


/*
   create table persona(
  id varchar(20) primary key auto_increment,
 user varchar(20),
 password varchar(20),
name varchar(20),
 surname varchar(20),
 company_email varchar(20),
 personal_email varchar(20),
 city varchar(20),
 active varchar(20),
 created_date date,
 imagen_url varchar(20),
 termination_date date

);
* */





@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona")
@Service
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    String id_persona;

/*
    @Column(name="edad")
    String edad;
    @Column(name="nombre")
    String nombre;
    @Column(name="poblacion")
    String poblacion;*/

    String user ;
    String password ;
    String name ;
    String surname ;
    String company_email;
    String personal_email;
    String city;
    String active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public Estudiante getEstudiante(){
        return this.estudiante;
    }

    public Profesor getProfesor(){
        return this.profesor;
    }

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL,orphanRemoval=true)
    Estudiante estudiante;

    @OneToOne(mappedBy = "persona" , cascade=CascadeType.ALL, orphanRemoval=true)
    Profesor profesor;



}
