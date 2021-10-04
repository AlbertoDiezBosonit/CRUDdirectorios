package com.example.demo.estudiante_asignatura.application.port;

import com.example.demo.estudiante_asignatura.domain.Estudiante_Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface Estudiante_asignaturaRepositoryPort extends JpaRepository<Estudiante_Asignatura,/*String*/Integer> {


}
