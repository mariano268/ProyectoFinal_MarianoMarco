package com.portfolio.MarianoMarco.Controller;

import com.portfolio.MarianoMarco.Dto.dtoProyecto;
import com.portfolio.MarianoMarco.Entity.Proyecto;
import com.portfolio.MarianoMarco.Interface.IProyectoService;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {
    @Autowired IProyectoService iproyectoService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = iproyectoService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id")int id){
        if(!iproyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.BAD_REQUEST);   
        }
        
        Proyecto proyecto = iproyectoService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproyecto) {
        if(dtoproyecto.getNombre().isBlank()) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(dtoproyecto.getDescripcion().isBlank()) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = new Proyecto(dtoproyecto.getNombre(), dtoproyecto.getDescripcion() , dtoproyecto.getImagen());
        iproyectoService.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto guardado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!iproyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        
        iproyectoService.delete(id);
        
        return new ResponseEntity(new Mensaje("Proyecto eliminado correctamente"), HttpStatus.OK);
    }
    
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id , @RequestBody dtoProyecto dtoproyecto){
        if(!iproyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        if(iproyectoService.existsByNombre(dtoproyecto.getNombre()) && iproyectoService.getByNombre(dtoproyecto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if((dtoproyecto.getNombre()).isBlank()) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = iproyectoService.getOne(id).get();
        proyecto.setNombre(dtoproyecto.getNombre());
        proyecto.setDescripcion(dtoproyecto.getDescripcion());
        proyecto.setImagen(dtoproyecto.getImagen());
        
        iproyectoService.save(proyecto);
        
        return new ResponseEntity(new Mensaje ("Proyecto actualizado"), HttpStatus.OK);
    }
}
