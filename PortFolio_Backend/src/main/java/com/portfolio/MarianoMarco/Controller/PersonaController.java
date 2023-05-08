package com.portfolio.MarianoMarco.Controller;

import com.portfolio.MarianoMarco.Dto.dtoPersona;
import com.portfolio.MarianoMarco.Entity.Persona;
import com.portfolio.MarianoMarco.Interface.IPersonaService;
import com.portfolio.MarianoMarco.security.controller.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    //TRAE LA INFORMACION DEL USUARIO. SIEMPRE ESTARA CON ID = 1
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!ipersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"),HttpStatus.BAD_REQUEST);   
        }
        
        Persona persona = ipersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.save(persona);
        return "Creada con exito";
    }*/
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id , @RequestBody dtoPersona dtopersona){
        if(!ipersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        if(ipersonaService.existsByNombre(dtopersona.getNombre()) && ipersonaService.getByNombre(dtopersona.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if((dtopersona.getNombre()).isBlank()) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = ipersonaService.getOne(id).get();
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImagen(dtopersona.getImagen());
        
        ipersonaService.save(persona);
        
        return new ResponseEntity(new Mensaje ("Persona actualizada"), HttpStatus.OK);
    }
}
