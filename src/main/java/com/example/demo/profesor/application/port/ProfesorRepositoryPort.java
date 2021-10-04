package com.example.demo.profesor.application.port;

import com.example.demo.persona.domain.Persona;
import com.example.demo.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositoryPort extends JpaRepository<Profesor,Integer> {
    Profesor findByPersona(Persona p);
}
