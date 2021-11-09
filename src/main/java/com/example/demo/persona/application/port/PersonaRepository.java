package com.example.demo.persona.application.port;

import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface PersonaRepository extends JpaRepository<Persona, String> {
    public List<Persona> getData(HashMap<String, Object> conditions);
    public List<PersonaOutputDto> getDataOutputDto(HashMap<String, Object> conditions);
}
