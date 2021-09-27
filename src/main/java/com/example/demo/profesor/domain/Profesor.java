package com.example.demo.profesor.domain;


import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.persona.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Set;

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
    String id;

    @OneToOne(fetch=FetchType.EAGER , cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "id_persona")
    Persona persona;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<Estudiante> estudiantes;

    String coments;
    String branch;
}
