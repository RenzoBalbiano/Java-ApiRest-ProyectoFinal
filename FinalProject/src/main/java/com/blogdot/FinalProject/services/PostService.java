package com.blogdot.FinalProject.services;

import java.util.ArrayList;

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

    public boolean eliminarPost(Long id){
        try{
            postRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
