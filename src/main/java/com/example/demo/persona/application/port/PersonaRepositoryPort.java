package com.example.demo.persona.application.port;

import com.example.demo.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonaRepositoryPort extends JpaRepository<Persona,/*Long*/String> {
  //  @Query("select p from Persona p where p.name = ?1")
  //  public List<Persona> encontrarPorNombre(String nombre);
    // el query tiene que estar aqui

    List<Persona> findByName(String name);

    List<Persona> findByUsere(String user);
    // así es mas simple
}
