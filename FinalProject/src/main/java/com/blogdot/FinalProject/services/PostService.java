package com.blogdot.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import com.blogdot.FinalProject.models.PostModel;
import com.blogdot.FinalProject.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    PostRepository postRepository; 
    
    public PostModel guardarPost(PostModel post){
        return postRepository.save(post);
    }

    public ArrayList<PostModel> obtenerTodosLosPosteos() {
        return (ArrayList<PostModel>) postRepository.findAll();
    }

    public List <PostModel> buscarPorTitulo(String titulo) throws Exception{
        try{
            List <PostModel> posteos = postRepository.findByTituloContaining(titulo);
            return posteos;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List <PostModel> buscarPorPublicado(Boolean estado) throws Exception{
        try{
            List <PostModel> posteos = postRepository.findByPublicado(estado);
            return posteos;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean eliminarPost(Long id){
        try{
            postRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
