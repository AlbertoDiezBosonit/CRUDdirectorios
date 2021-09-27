package com.example.demo.persona.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonaRepository extends JpaRepository<Persona,/*Long*/String> {
  //  @Query("select p from Persona p where p.name = ?1")
  //  public List<Persona> encontrarPorNombre(String nombre);
    // el query tiene que estar aqui

    List<Persona> findByName(String name);

    List<Persona> findByUser(String user);
    // así es mas simple
}
