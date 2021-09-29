package com.example.demo.profesor.domain;


import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/*
   create table profesor
(
  id varchar(20) primary key auto_increment,
id_persona varchar(20),
coments varchar(20),
branch varchar(20),
foreign key (id_persona) references persona(id)

);
* */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesor")
@Service
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    String id_profesor;

    @OneToOne(fetch=FetchType.EAGER , cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "id_persona")
    Persona persona;

    @OneToMany(
            mappedBy = "profesor",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    List<Estudiante> estudiantes=new ArrayList<>();//en el ejemplo lo inicializa

    String coments;
    String branch;
}
