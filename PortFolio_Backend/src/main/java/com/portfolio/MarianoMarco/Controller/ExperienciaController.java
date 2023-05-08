package com.portfolio.MarianoMarco.Controller;

import com.portfolio.MarianoMarco.Dto.dtoExperiencia;
import com.portfolio.MarianoMarco.Entity.Experiencia;
import com.portfolio.MarianoMarco.Interface.IExperienciaService;
import com.portfolio.MarianoMarco.security.controller.Mensaje;
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
@RequestMapping("/experiencia")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {
    @Autowired IExperienciaService iexperienciaService;
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id")int id){
        if(!iexperienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.BAD_REQUEST);   
        }
        
        Experiencia experiencia = iexperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexperiencia) {
        if(dtoexperiencia.getNombre().isBlank()) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(dtoexperiencia.getDescripcion().isBlank()) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Experiencia experiencia = new Experiencia(dtoexperiencia.getNombre(), dtoexperiencia.getDescripcion() , dtoexperiencia.getImagen());
        iexperienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia guardada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!iexperienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        
        iexperienciaService.delete(id);
        
        return new ResponseEntity(new Mensaje("Experiencia eliminada correctamente"), HttpStatus.OK);
    }
    
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id , @RequestBody dtoExperiencia dtoexperiencia){
        if(!iexperienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        if(iexperienciaService.existsByNombre(dtoexperiencia.getNombre()) && iexperienciaService.getByNombre(dtoexperiencia.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if((dtoexperiencia.getNombre()).isBlank()) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Experiencia experiencia = iexperienciaService.getOne(id).get();
        experiencia.setNombre(dtoexperiencia.getNombre());
        experiencia.setDescripcion(dtoexperiencia.getDescripcion());
        experiencia.setImagen(dtoexperiencia.getImagen());
        
        iexperienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje ("Persona actualizada"), HttpStatus.OK);
    }
}
