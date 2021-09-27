package com.example.demo.estudiante.domain;


import com.example.demo.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;


/* SQL que genera la tabla
drop table estudiante
;
create table estudiante
(
  id varchar(30) primary key auto_increment,
  id_persona int,
  num_hours_week int   not null,
  coments varchar(30),

  branch varchar(30) not null,
FOREIGN KEY (id_persona)  REFERENCES persona(id)

);

* */


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiante")
@Service
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   //Long id;
    String id;

    //@OneToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "Persona", referencedColumnName = "id")
  //  Long id_persona;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;
    Long num_hours_week;
    String comments;
    String branch;
}
