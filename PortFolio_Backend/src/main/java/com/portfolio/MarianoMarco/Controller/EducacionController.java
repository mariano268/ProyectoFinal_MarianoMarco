package com.portfolio.MarianoMarco.Controller;

import com.portfolio.MarianoMarco.Dto.dtoEducacion;
import com.portfolio.MarianoMarco.Entity.Educacion;
import com.portfolio.MarianoMarco.Interface.IEducacionService;
import com.portfolio.MarianoMarco.security.controller.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {
    @Autowired IEducacionService ieducacionService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = ieducacionService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id")int id){
        if(!ieducacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.BAD_REQUEST);   
        }
        
        Educacion experiencia = ieducacionService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion) {
        if(dtoeducacion.getNombre().isBlank()) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(dtoeducacion.getDescripcion().isBlank()) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion experiencia = new Educacion(dtoeducacion.getNombre(), dtoeducacion.getDescripcion() , dtoeducacion.getImagen());
        ieducacionService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Educacion guardada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!ieducacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        
        ieducacionService.delete(id);
        
        return new ResponseEntity(new Mensaje("Educacion eliminada correctamente"), HttpStatus.OK);
    }
    
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id , @RequestBody dtoEducacion dtoeducacion){
        if(!ieducacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        if(ieducacionService.existsByNombre(dtoeducacion.getNombre()) && ieducacionService.getByNombre(dtoeducacion.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if((dtoeducacion.getNombre()).isBlank()) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = ieducacionService.getOne(id).get();
        educacion.setNombre(dtoeducacion.getNombre());
        educacion.setDescripcion(dtoeducacion.getDescripcion());
        educacion.setImagen(dtoeducacion.getImagen());
        
        ieducacionService.save(educacion);
        
        return new ResponseEntity(new Mensaje ("Educacion actualizada"), HttpStatus.OK);
    }
}
