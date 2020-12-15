package com.blogdot.FinalProject.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.blogdot.FinalProject.models.PostModel;
import com.blogdot.FinalProject.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")

public class PostController {

    @Autowired
    PostService postService;

    @PostMapping()
    public PostModel guardarUsuario(@RequestBody PostModel post) {
        post.setFechaDeCreacion(LocalDateTime.now());
        return this.postService.guardarPost(post);
    }
    @GetMapping()
    public ArrayList<PostModel> obtenerPosteos(){
        return postService.obtenerTodosLosPosteos();
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.postService.eliminarPost(id);
        if(ok){
            return "Se eliminó el usuario con id " + id;
        }else {
            return "No pudo eliminar el usuario con id " + id;
        }
    }
    
}
