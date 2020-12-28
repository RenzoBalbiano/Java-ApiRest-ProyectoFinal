package com.blogdot.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.blogdot.FinalProject.DTO.UsuarioDto;
import com.blogdot.FinalProject.models.UsuarioModel;
import com.blogdot.FinalProject.repositories.OtroUsuarioRepository;
import com.blogdot.FinalProject.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository; 

    OtroUsuarioRepository otroUsuarioRepository;
    
    public UsuarioDto guardarUsuario(UsuarioModel usuario) {
        
        return new UsuarioDto(usuarioRepository.save(usuario));
    }
    
    public ArrayList<UsuarioModel> obtenerTodosUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }
    
    public UsuarioModel getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    public List <UsuarioModel> buscar(String ciudad) throws Exception{
        try{
            List <UsuarioModel> usuarios = usuarioRepository.findByCiudad(ciudad);
            return usuarios;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

	public Optional<UsuarioModel> getUsuario(Long id) {
        return usuarioRepository.findById(id);
	}

	public UsuarioModel getUno(Long usuarioId) {
        
        return otroUsuarioRepository.getOne(usuarioId);
	}
}
