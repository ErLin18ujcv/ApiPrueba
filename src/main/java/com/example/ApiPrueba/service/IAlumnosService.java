package com.example.ApiPrueba.service;

import com.example.ApiPrueba.entity.Alumnos;
import com.example.ApiPrueba.exception.BusinessException;
import com.example.ApiPrueba.exception.NotFoundException;
import java.util.List;

public interface IAlumnosService {
    Alumnos        saveAlumnos (Alumnos alumnos) throws BusinessException;
    List <Alumnos> saveAlumnos(List<Alumnos> alumnos) throws BusinessException,NotFoundException;
    List <Alumnos> getAlumnos() throws BusinessException;
    Alumnos        getAlumnosById (long id) throws BusinessException, NotFoundException;
    Alumnos        getAlumnosByNombre(String nombre) throws BusinessException,NotFoundException;
    void           deleteAlumnos(long id) throws BusinessException,NotFoundException;
    Alumnos        updateAlumnos (Alumnos alumnos) throws BusinessException,NotFoundException;






}
