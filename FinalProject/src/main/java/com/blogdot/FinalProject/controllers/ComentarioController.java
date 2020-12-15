package com.blogdot.FinalProject.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.blogdot.FinalProject.models.ComentarioModel;
import com.blogdot.FinalProject.services.ComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @PostMapping()
    public ComentarioModel guardarUsuario(@RequestBody ComentarioModel comentario) {
        comentario.setFechaDeCreacion(LocalDateTime.now());
        return this.comentarioService.guardarComentario(comentario);
    }
    @GetMapping()
    public ArrayList<ComentarioModel> obtenerPosteos(){
        return comentarioService.obtenerTodosLosComentarios();
    }
    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.comentarioService.eliminarComentario(id);
        if(ok){
            return "Se eliminó el usuario con id " + id;
        }else {
            return "No pudo eliminar el usuario con id " + id;
        }
    }
}
