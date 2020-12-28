package com.blogdot.FinalProject.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.blogdot.FinalProject.models.ComentarioModel;
import com.blogdot.FinalProject.repositories.ComentarioRepository;
import com.blogdot.FinalProject.services.ComentarioService;
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
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    ComentarioRepository comentarioRepository;

    @PostMapping()
    public ComentarioModel guardarUsuario(@RequestBody ComentarioModel comentario) {
        comentario.setFechaDeCreacion(LocalDate.now());
        return this.comentarioService.guardarComentario(comentario);
    }  

    @GetMapping()
    public ArrayList<ComentarioModel> obtenerComentarios(){
        return comentarioService.obtenerTodosLosComentarios();
    }
    /*
    CONSULTA - OBTENER TODOS LOS COMENTARIOS DE UN POST, CON LA OPCIÓN DE PASAR EL LIMITE DE COMENTARIOS DESEADOS COMO MÁXIMO.
    IDEM A #2. SI NO LE PASO EL LIMITE TRAE TODOS. SI LE PASO 3, ME TRAERÁ LOS 3 COMENTARIOS MÁS NUEVO SOLAMENTE.
    */

    @GetMapping("/filtrarComentariosBlog")
    public ResponseEntity<?> buscarPorTituloDado(@RequestParam Long id, Integer limit){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(comentarioService.buscarComentariosBajoParametro(id,limit));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateComentario(@PathVariable Long id,@RequestBody ComentarioModel comentarioModificado){

        ComentarioModel comentarioExistente;
        
        Optional<ComentarioModel> comentarioONull = comentarioRepository.findById(id);
        
        if (comentarioONull.isPresent()) {
            comentarioExistente = comentarioONull.get();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String comentario = comentarioModificado.getComentario();
        
        if (comentario != null) {
            comentarioExistente.setComentario(comentario);
        }

        return new ResponseEntity<>(comentarioRepository.save(comentarioExistente), HttpStatus.OK);
    }    

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.comentarioService.eliminarComentario(id);
        if(ok){
            return "Se eliminó el comentario con id " + id;
        }else {
            return "No pudo eliminar el comentario con id " + id;
        }
    }
}
