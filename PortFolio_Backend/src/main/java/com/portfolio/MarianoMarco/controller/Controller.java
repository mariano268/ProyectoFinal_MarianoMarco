package com.portfolio.MarianoMarco.controller;

import com.portfolio.MarianoMarco.model.Usuario;
import com.portfolio.MarianoMarco.service.IUsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private IUsuarioService userServ;
    
    @PostMapping ("/new/usuario")
    public void agregarUsuario (@RequestBody Usuario user) {
        userServ.crearUsuario(user);
    }
    
    @GetMapping ("/ver/usuarios")
    public List<Usuario> verUsuarios() {
        return userServ.verUsuarios();
    }
}
