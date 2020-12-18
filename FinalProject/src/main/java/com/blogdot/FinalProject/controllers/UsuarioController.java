package com.blogdot.FinalProject.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.blogdot.FinalProject.models.PostModel;
import com.blogdot.FinalProject.models.UsuarioModel;
import com.blogdot.FinalProject.repositories.OtroUsuarioRepository;
import com.blogdot.FinalProject.repositories.PostRepository;
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

    @Autowired
    PostRepository postRepository;

    @PostMapping()
    public ResponseEntity<?> guardarUsuario(@RequestBody UsuarioModel usuario) {
        
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario),HttpStatus.CREATED); 
    }

    @PostMapping("/{id_user}/post")
    public PostModel crearPostDadoUsuario(@PathVariable Long id_user,@RequestBody PostModel post) {
        UsuarioModel usuario = otroUsuarioRepository.getOne(id_user);
        usuario.agregarPost(post);
        post.setFechaDeCreacion(LocalDate.now());
        return this.postService.guardarPost(post);
    }
   
    @GetMapping()//
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
    
    @GetMapping("/creacion")
    public ResponseEntity<?> buscarFechaPosterior(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
  
        try{
      
            List <UsuarioModel> usuarios = otroUsuarioRepository.findByFechaDeCreacionAfter(fecha);
            System.out.println("Entró al método e hizo la lista");
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping(path = "/{usuarioId}")
    public ResponseEntity<?> updateUsuario(@PathVariable("usuarioId") Long usuarioId,@RequestBody UsuarioModel usuario){

        System.out.println("Entró al método");

        if(usuario != null){ 
            System.out.println(usuario.toString());
            System.out.println(usuarioId);
        }else{System.out.println("null");
        }

        UsuarioModel usuarioExistente = otroUsuarioRepository.findById(usuarioId).get();;
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setCiudad(usuario.getCiudad());
        usuarioExistente.setProvincia(usuario.getProvincia());
        usuarioExistente.setPais(usuario.getPais());
        
        return new ResponseEntity<>(otroUsuarioRepository.save(usuario), HttpStatus.OK);
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
