package com.blogdot.FinalProject.controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import com.blogdot.FinalProject.DTO.ComentarioDto;
import com.blogdot.FinalProject.models.ComentarioModel;
import com.blogdot.FinalProject.models.PostModel;
import com.blogdot.FinalProject.models.UsuarioModel;
import com.blogdot.FinalProject.repositories.PostRepository;
import com.blogdot.FinalProject.repositories.UsuarioRepository;
import com.blogdot.FinalProject.services.ComentarioService;
import com.blogdot.FinalProject.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    PostRepository postRepository;

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping()
    public PostModel guardarNuevoPost(@RequestBody PostModel post) {
        post.setFechaDeCreacion(LocalDate.now());
        return this.postService.guardarPost(post);
    }
     //POST Crear un COMMENT
     @PostMapping("/{id_post}/comentario")
     public ResponseEntity<?> crearComentario(@PathVariable Long id_post, @RequestBody ComentarioDto comentarioDto) {
        
        PostModel post = postRepository.getOne(id_post);

        UsuarioModel usuario = usuarioRepository.findByEmail(comentarioDto.getAutor());
 
        ComentarioModel comentario = new ComentarioModel();
        comentario.setComentario(comentarioDto.getComentario());
        comentario.setAutor(usuario.getEmail());
        comentario.setFechaDeCreacion(LocalDate.now());
        
        post.agregarComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.guardarComentario(comentario));
    }

    @GetMapping()
    public ArrayList<PostModel> obtenerPosteos(){
        return postService.obtenerTodosLosPosteos();
    }

    /*
    CONSULTA - OBTENER TODOS LOS POST QUE CONTENGA UNA PALABRA DADA EN EL TÍTULO --LA PALABRA PUEDE COINCIDIR EN 
    CUALQUIER PARTE DEL TÍTULO

    TRAER TODOS LOS POST SIN PUBLICAR

    */

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
