package com.example.demo.persona.application;

import com.example.demo.persona.domain.Persona;
import com.example.demo.persona.infraestructure.dto.PersonaOutputDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaRepositoryImpl {
        @PersistenceContext
        private EntityManager entityManager;

    public List<PersonaOutputDto> getDataOutputDto(HashMap<String, Object> conditions){
        return getData(conditions).stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
    }

        public List<Persona> getData(HashMap<String, Object> conditions)
        {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Persona> query= cb.createQuery(Persona.class);
            Root<Persona> root = query.from(Persona.class);

            List<Predicate> predicates = new ArrayList<>();
            conditions.forEach((field,value) ->
            {
                switch (field)
                {
                    case "id":
                        predicates.add(cb.equal (root.get(field), (Integer)value));
                        break;
                    case "name":
                        predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                        break;
                    case "usere":
                        predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                        break;
                    case "surname":
                        predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                        break;
                    case "address":
                        predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                        break;
                    case "fechaCreacionInferior":
                        predicates.add(cb.lessThan(root.<Date>get("termination_date"),java.sql.Date.valueOf((String) value)));
                        break;
                    case "fechaCreacionSuperior":
                        predicates.add(cb.greaterThan(root.<Date>get("termination_date"),java.sql.Date.valueOf((String) value)));
                        break;

                }
            });
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            List<Persona> retorno=entityManager.createQuery(query).getResultList();
            if(conditions.get("ordenar")!=null) {
                if (conditions.get("ordenar").equals("usere"))
                    retorno.sort(Comparator.comparing(Persona::getUsere));
                else if (conditions.get("ordenar").equals("name"))
                    retorno.sort(Comparator.comparing(Persona::getName));
            }

            // ahora toca la paginación:
            if(conditions.get("pagina")!=null) {
                Integer numItems=10,numPagina=0;
                try{
                    numPagina=Integer.parseInt(conditions.get("pagina").toString());
                }catch (Exception e){}
                if(conditions.get("porPagina")!=null){
                    try {
                        numItems = Integer.parseInt(conditions.get("porPagina").toString());
                    }catch (Exception e){}
                }
                Integer inicio=numPagina*numItems;
                Integer fin=inicio+numItems;
                if(inicio>=retorno.size()) {
                    retorno.clear();
                    inicio=0;
                    fin=0;
                }
                if(fin>= retorno.size()){
                    fin= retorno.size();
                }
                retorno = retorno.subList(inicio, fin);
            }

            return retorno;

        }
    }

