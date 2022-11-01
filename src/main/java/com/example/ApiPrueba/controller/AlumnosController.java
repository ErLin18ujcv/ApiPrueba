package com.example.ApiPrueba.controller;
import com.example.ApiPrueba.entity.Alumnos;
import com.example.ApiPrueba.exception.BusinessException;
import com.example.ApiPrueba.exception.NotFoundException;
import com.example.ApiPrueba.service.AlumnosService;
import com.example.ApiPrueba.utils.Constants;
import com.example.ApiPrueba.utils.RestApiError;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/alumnos")
public class AlumnosController {
    @Autowired
    private AlumnosService service;

    @PostMapping("addAlumno")
    public ResponseEntity<Object> addAlumnos(@RequestBody Alumnos alumnos){
        try {
            service.saveAlumnos(alumnos);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_ALUMNOS+alumnos.getId());
            return new ResponseEntity(alumnos,responseHeader, HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError= new  RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
            "La informacion enviada no es valida!..",e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addAlumnos")
    public ResponseEntity<Any> addAlumnos(@RequestBody List<Alumnos> alumnos){
        try {
            return new ResponseEntity(service.saveAlumnos(alumnos),HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError= new  RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "La informacion enviada no es valida!..",e.getMessage());
            return new ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Alumnos>> findAllAlumnos(){
        try {
            return new ResponseEntity(service.getAlumnos(),HttpStatus.OK);
        }catch (Exception e){
            RestApiError apiError= new  RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "La informacion enviada no es valida!..",e.getMessage());
            return new ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Alumnos> findAlumnosById(@PathVariable long id){
        try {
            return new ResponseEntity(service.getAlumnosById(id),HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Alumnos> findAlumnosByNombre(@PathVariable String nombre){
        try {
            return new ResponseEntity(service.getAlumnosByNombre(nombre),HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("")
    public ResponseEntity<Any> updateAlumnos(@RequestBody Alumnos alumnos){
        try {
            service.updateAlumnos(alumnos);
            return new ResponseEntity(alumnos,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteAlumnos(@PathVariable long id){
        try {
            service.deleteAlumnos(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }




}
