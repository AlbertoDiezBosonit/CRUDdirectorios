package com.example.demo.persona.infraestructure.controller;


import com.example.demo.persona.application.PersonaRepositoryImpl;
import com.example.demo.persona.application.port.PersonaRepository;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("persona")
public class ControladorBusquedaAvanzada {
    @Autowired
    PersonaServicePort personaService;

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping("avanzada")
    public List<PersonaOutputDto> retorna(@RequestParam(value = "user",defaultValue = "") String user,
                                          @RequestParam(value = "name",defaultValue = "") String name,
                                          @RequestParam(value = "surname",defaultValue = "") String surname,
                                          @RequestParam(value = "fechaCreacionSuperior",defaultValue = "") String fechaCreacionSuperior,
                                          @RequestParam(value = "fechaCreacionInferior",defaultValue = "") String fechaCreacionInferior,
                                          @RequestParam(value = "ordenar",defaultValue = "") String ordenar,
                                          @RequestParam(value = "pagina",defaultValue = "") String pagina,
                                          @RequestParam(value = "porPagina",defaultValue = "") String porPagina
                                                                            ) {
        HashMap<String,Object> parametros=new HashMap<>();
        if(!user.equals(""))
            parametros.put("usere",user);
        if(!name.equals(""))
            parametros.put("name",name);
        if(!surname.equals(""))
            parametros.put("surname",surname);
        if(!fechaCreacionSuperior.equals(""))
            parametros.put("fechaCreacionSuperior",fechaCreacionSuperior);
        if(!fechaCreacionInferior.equals(""))
            parametros.put("fechaCreacionInferior",fechaCreacionInferior);
        if(!ordenar.equals(""))
            parametros.put("ordenar",ordenar);
        if(!pagina.equals(""))
            parametros.put("pagina",pagina);
        if(!porPagina.equals(""))
            parametros.put("porPagina",porPagina);
        return personaRepository.getDataOutputDto(parametros);
    }
}
