package com.example.demo.estudiante.application.port;

import com.example.demo.estudiante.domain.Estudiante;
import com.example.demo.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstudianteRepositoryPort extends JpaRepository<Estudiante,Long> {
    //List<Persona> findByUser(String user);
}
