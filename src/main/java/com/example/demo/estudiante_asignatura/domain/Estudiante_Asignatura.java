package com.example.demo.estudiante_asignatura.domain;


import com.example.demo.estudiante.domain.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Date;


/*
create table estudiante_asignatura
(
  id_asignatura varchar(20) primary key auto_increment,
  id_estudiante varchar(20),
  asignatura varchar(20),
 coments varchar(20),
 initial_date date not null,
 finish_date date  ,
FOREIGN KEY (id_estudiante)  REFERENCES estudiante(id)
);


* */


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiante_asignatura")
@Service
public class Estudiante_Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_asignatura")
    String id_estudiante_asignatura;


    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    Estudiante estudiante;
    String asignatura; // un comentario a la asignatura
    String coments;
    Date initial_date;
    Date finish_date;


}
