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

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable("id") Long id,@RequestBody UsuarioModel usuarioModificado){

        UsuarioModel usuarioExistente;
        
        Optional<UsuarioModel> usuarioONull = otroUsuarioRepository.findById(id);

        if (usuarioONull.isPresent()) {
            usuarioExistente = usuarioONull.get();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String nombre = usuarioModificado.getNombre();
        String apellido = usuarioModificado.getApellido();
        String email = usuarioModificado.getEmail();
        String ciudad = usuarioModificado.getCiudad();
        String provincia = usuarioModificado.getProvincia();
        String pais = usuarioModificado.getPais();

        if (nombre != null) {
            usuarioExistente.setNombre(nombre);
        }

        if (apellido != null) {
            usuarioExistente.setApellido(apellido);
        }

        if (email != null) {
            usuarioExistente.setEmail(email);
        }

        if (ciudad != null) {
            usuarioExistente.setCiudad(ciudad);
        }
        if (provincia != null) {
            usuarioExistente.setProvincia(provincia);
        }
        if (pais != null) {
            usuarioExistente.setPais(pais);
        }
        
        return new ResponseEntity<>(otroUsuarioRepository.save(usuarioExistente), HttpStatus.OK);
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
