package com.example.demo.estudiante.domain;


import com.example.demo.estudiante_asignatura.domain.Estudiante_Asignatura;
import com.example.demo.persona.domain.Persona;
import com.example.demo.profesor.domain.Profesor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;
import org.hibernate.annotations.Parameter;
import com.example.demo.StringPrefixedSequenceIdGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/* SQL que genera la tabla
drop table estudiante
;
create table estudiante
(
  id varchar(30) primary key auto_increment,
  id_persona varchar(30),
 id_profesor varchar(30),
  num_hours_week int   not null,
  comments varchar(30),

  branch varchar(30) not null,
FOREIGN KEY (id_persona)  REFERENCES persona(id),
FOREIGN KEY (id_profesor)  REFERENCES profesor(id)

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
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "estudiante_seq")
    @GenericGenerator(
            name="estudiante_seq",
            strategy = "com.example.demo.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            }
    )*/
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="id")
    String/*Integer*/ id_estudiante;

    // borra los dos registros a la vez
    @OneToOne(fetch=FetchType.EAGER , cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "id_persona")
    Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
            Profesor profesor;

    Long num_hours_week;
    String comments;
    String branch;

    @OneToMany(
            mappedBy = "estudiante",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    List<Estudiante_Asignatura> estudiantes_asignaturas=new ArrayList<>();//en el ejemplo lo inicializa


}
