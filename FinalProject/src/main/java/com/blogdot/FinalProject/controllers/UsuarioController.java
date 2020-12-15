package com.blogdot.FinalProject.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Optional;

import com.blogdot.FinalProject.models.UsuarioModel;
import com.blogdot.FinalProject.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        usuario.setFechaDeCreacion(LocalDateTime.now());
        return this.usuarioService.guardarUsuario(usuario);
    }
    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }
    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }
    @GetMapping("porEmail/{email}")
    public UsuarioModel findUsuarioByEmail(@PathVariable String email){
        return usuarioService.getUsuarioByEmail(email);
    }
    @GetMapping("/usuario")
    public ResponseEntity<?> buscarCiudad(@RequestParam String ciudad){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscar(ciudad));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/usuario")
    public UsuarioModel updateUsuario(@RequestBody UsuarioModel usuario){
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if(ok){
            return "Se eliminó el usuario con id " + id;
        }else {
            return "No pudo eliminar el usuario con id " + id;
        }
    }
}
