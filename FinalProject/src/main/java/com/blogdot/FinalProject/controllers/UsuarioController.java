package com.blogdot.FinalProject.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import com.blogdot.FinalProject.models.ComentarioModel;
import com.blogdot.FinalProject.models.PostModel;
import com.blogdot.FinalProject.models.UsuarioModel;
import com.blogdot.FinalProject.repositories.OtroUsuarioRepository;
import com.blogdot.FinalProject.services.ComentarioService;
import com.blogdot.FinalProject.services.PostService;
import com.blogdot.FinalProject.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PostService postService;

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    OtroUsuarioRepository otroUsuarioRepository;

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        usuario.setFechaDeCreacion(LocalDateTime.now());
        return this.usuarioService.guardarUsuario(usuario);
    }
    @PostMapping("/{id_user}/post")
    public PostModel crearPostDadoUsuario(@PathVariable Long id_user,@RequestBody PostModel post) {
        UsuarioModel usuario = otroUsuarioRepository.getOne(id_user);
        usuario.agregarPost(post);
        post.setFechaDeCreacion(LocalDateTime.now());
        return this.postService.guardarPost(post);
    }
    @PostMapping("/{id_user}/comentario")
    public ComentarioModel crearComentarioDadoUsuario(@PathVariable Long id_user,@RequestBody ComentarioModel comentario) {
        UsuarioModel usuario = otroUsuarioRepository.getOne(id_user);
        usuario.agregarComentario(comentario);
        comentario.setFechaDeCreacion(LocalDateTime.now());
        return this.comentarioService.guardarComentario(comentario);
    }
    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerTodosUsuarios();
    }
    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }
    @GetMapping("porEmail/{email}")
    public UsuarioModel findUsuarioByEmail(@PathVariable String email){
        return usuarioService.getUsuarioByEmail(email);
    }
    @GetMapping("/residencia")
    public ResponseEntity<?> buscarCiudad(@RequestParam String ciudad){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscar(ciudad));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
    
    @GetMapping("/creacion/{date}")
    public ResponseEntity<?> buscarFechaPosterior(@RequestParam(value = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDateTime fecha){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarPorFecha(fecha));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable(value = "id") Long id,@RequestBody UsuarioModel usuario){

        UsuarioModel usuarioEditado = otroUsuarioRepository.getOne(id);
        usuarioEditado.setNombre(usuario.getNombre());
        usuarioEditado.setApellido(usuario.getApellido());
        usuarioEditado.setEmail(usuario.getEmail());
        usuarioEditado.setPassword(usuario.getPassword());
        usuarioEditado.setCiudad(usuario.getCiudad());
        usuarioEditado.setProvincia(usuario.getProvincia());
        usuarioEditado.setPais(usuario.getPais());
        return new ResponseEntity<>(otroUsuarioRepository.save(usuarioEditado), HttpStatus.OK);
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
