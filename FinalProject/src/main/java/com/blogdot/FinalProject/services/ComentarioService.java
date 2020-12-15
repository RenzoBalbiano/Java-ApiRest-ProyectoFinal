package com.blogdot.FinalProject.services;

import java.util.ArrayList;

import com.blogdot.FinalProject.models.ComentarioModel;
import com.blogdot.FinalProject.repositories.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository; 

    public ComentarioModel guardarComentario(ComentarioModel comentario){
        return comentarioRepository.save(comentario);
    }
    public ArrayList<ComentarioModel> obtenerTodosLosComentarios() {
        return (ArrayList<ComentarioModel>) comentarioRepository.findAll();
    }

    public boolean eliminarComentario(Long id){
        try{
            comentarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    
}
