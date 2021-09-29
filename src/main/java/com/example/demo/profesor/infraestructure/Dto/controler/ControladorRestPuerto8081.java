package com.example.demo.profesor.infraestructure.Dto.controler;


import com.example.demo.exception.BeanNotFoundException;
import com.example.demo.persona.application.port.PersonaServicePort;
import com.example.demo.profesor.application.port.ProfesorServicePort;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDto;
import com.example.demo.profesor.infraestructure.Dto.ProfesorOutputDtoFull;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("profesor")
public class ControladorRestPuerto8081 {


    @GetMapping("/8081/{id}")
    public ProfesorOutputDto retorna8081(@PathVariable String id, @RequestParam(value = "outputType",defaultValue = "simple") String itemid){
        // ahora tenemos que hacer la llamada
        String url="http://localhost:8081/profesor/" + id + "?outputType=" + itemid;
        if(itemid.equals("simple")) {
            try {
                ResponseEntity<ProfesorOutputDto> responseEntity = new RestTemplate().getForEntity(url, ProfesorOutputDto.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK)
                    return responseEntity.getBody();
                throw new BeanNotFoundException("No encontrado");
            } catch (Exception e) {
                throw new BeanNotFoundException("No encontrado");
            }
        }
        else if(itemid.equals("full")) {
            try {
                ResponseEntity<ProfesorOutputDtoFull> responseEntity = new RestTemplate().getForEntity(url, ProfesorOutputDtoFull.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    return responseEntity.getBody();
                }
                throw new BeanNotFoundException("No encontrado");
            } catch (Exception e) {
                e.printStackTrace();
                throw new BeanNotFoundException("No encontrado");
            }
        }
        return null;
    }

//    @RestControler(“/profesor/{id})
//    GET getProfesor(@PathVariable int id)

}
