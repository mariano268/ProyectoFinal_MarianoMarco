package com.portfolio.MarianoMarco.Controller;

import com.portfolio.MarianoMarco.Entity.Persona;
import com.portfolio.MarianoMarco.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    //TRAE LA INFORMACION DEL USUARIO. SIEMPRE ESTARA CON ID = 1
    @GetMapping("/persona/traer")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)1);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "Creada con exito";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "Borrada con exito";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persona/editar")
    public String editPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "Editada con exito";
    }
}
