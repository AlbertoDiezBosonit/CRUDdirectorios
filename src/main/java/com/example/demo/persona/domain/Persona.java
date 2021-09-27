package com.example.demo.persona.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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
    //@OneToOne(fetch=FetchType.LAZY)
    Long id;
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

}
