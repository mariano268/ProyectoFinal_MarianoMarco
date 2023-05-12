package com.portfolio.MarianoMarco.Controller;

import com.portfolio.MarianoMarco.Dto.dtoHabilidad;
import com.portfolio.MarianoMarco.Entity.Habilidad;
import com.portfolio.MarianoMarco.Interface.IHabilidadService;
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
@RequestMapping("/habilidad")
@CrossOrigin(origins = "http://localhost:4200")
public class HabilidadController {
    @Autowired IHabilidadService ihabilidadService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list() {
        List<Habilidad> list = ihabilidadService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id")int id){
        if(!ihabilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.BAD_REQUEST);   
        }
        
        Habilidad habilidad = ihabilidadService.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody dtoHabilidad dtohabilidad) {
        if(dtohabilidad.getNombre().isBlank()) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Habilidad habilidad = new Habilidad(dtohabilidad.getNombre(), dtohabilidad.getPorcentaje());
        ihabilidadService.save(habilidad);
        
        return new ResponseEntity(new Mensaje("Habilidad guardada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!ihabilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        
        ihabilidadService.delete(id);
        
        return new ResponseEntity(new Mensaje("Habilidad eliminada correctamente"), HttpStatus.OK);
    }
    
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id , @RequestBody dtoHabilidad dtohabilidad){
        if(!ihabilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        if(ihabilidadService.existsByNombre(dtohabilidad.getNombre()) && ihabilidadService.getByNombre(dtohabilidad.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if((dtohabilidad.getNombre()).isBlank()) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Habilidad habilidad = ihabilidadService.getOne(id).get();
        habilidad.setNombre(dtohabilidad.getNombre());
        habilidad.setPorcentaje(dtohabilidad.getPorcentaje());
        
        ihabilidadService.save(habilidad);
        
        return new ResponseEntity(new Mensaje ("Habilidad actualizada"), HttpStatus.OK);
    }
}
