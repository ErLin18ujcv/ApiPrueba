package com.example.ApiPrueba.service;

import com.example.ApiPrueba.entity.Alumnos;
import com.example.ApiPrueba.exception.BusinessException;
import com.example.ApiPrueba.exception.NotFoundException;
import com.example.ApiPrueba.repository.AlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnosService implements IAlumnosService{
    @Autowired
    private AlumnosRepository repository;
    @Override
    public Alumnos saveAlumnos(Alumnos alumnos) throws BusinessException {
        /* Validaciones */
        try {
            if (alumnos.getNombre().isEmpty()){
                throw new BusinessException("El producto esta vacio");
            }if (alumnos.getNombre().length()<10){
                throw new BusinessException("El nombre no debe contener menos de 10 caracteres");
            }if (alumnos.getNombre().matches("")){//Validando Nombre!......
                throw new BusinessException("El nombre no debe contener espacios");
            }if (alumnos.getEmail().matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                throw new BusinessException("El correo no es valido");//Validando Correo!....
            }
            return repository.save(alumnos);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Override
    public List<Alumnos> saveAlumnos(List<Alumnos> alumnos) throws BusinessException, NotFoundException {
        try {
            for ( Alumnos alumno: alumnos) {
                if (alumno.getEmail().matches("")){
                    throw new BusinessException("La direccion de correo no debe estar vacia!!...");
                }
            }
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        return repository.saveAll(alumnos);
    }

    @Override
    public List<Alumnos> getAlumnos() throws BusinessException {
        try {
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Alumnos getAlumnosById(long id) throws BusinessException, NotFoundException {
        Optional<Alumnos> opt =null;
        try {
            opt=repository.findById(id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("No se encontro el prducto!!"+ id);
        }
        return opt.get();
    }

    @Override
    public Alumnos getAlumnosByNombre(String nombre) throws BusinessException, NotFoundException {
        Optional<Alumnos> opt =null;
        try {
            opt=repository.findByNombre(nombre);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("No se encontro el nombre!!"+nombre);
        }
        return opt.get();
    }

    @Override
    public void deleteAlumnos(long id) throws BusinessException, NotFoundException {
        Optional<Alumnos> opt =null;
        try {
            opt=repository.findById(id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("No se encontro el prducto!!"+id);
        }
        else {
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }

    @Override
    public Alumnos updateAlumnos(Alumnos alumnos) throws BusinessException, NotFoundException {
        Optional<Alumnos> opt =null;
        try {
            opt=repository.findById(alumnos.getId());
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()){
            throw new NotFoundException("No se encontro el prducto!!"+ alumnos.getId());
        }
        else {
            try {
                if (alumnos.getTelefono().matches("^[2398]\\d*$")){
                    throw new  BusinessException("Ese no es un numero de telefono valido.");
                }if (alumnos.getNombre().length()<10){
                    throw new BusinessException("El nombre no debe contener menos de 10 caracteres");
                }
                //if (alumnos.getNombre().matches("")){//Validando Nombre!......
                    //throw new BusinessException("El nombre no debe contener espacios");
                //}
                if (alumnos.getEmail().matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                    throw new BusinessException("El correo no es valido");//Validando Correo!....
                }
                //reglas del negocio
                Alumnos existingAlumnos=new Alumnos();
                existingAlumnos.setId       (alumnos .getId());
                existingAlumnos.setNombre   (alumnos .getNombre());
                existingAlumnos.setApellido (alumnos .getApellido());
                existingAlumnos.setTelefono (alumnos .getTelefono());
                existingAlumnos.setEmail    (alumnos .getEmail());
                existingAlumnos.setDireccion(alumnos .getDireccion());
                existingAlumnos.setEdad     (alumnos .getEdad());
                return repository.save(existingAlumnos);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
