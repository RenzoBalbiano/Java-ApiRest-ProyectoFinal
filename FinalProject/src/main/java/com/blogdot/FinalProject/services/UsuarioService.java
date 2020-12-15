package com.blogdot.FinalProject.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.blogdot.FinalProject.models.UsuarioModel;
import com.blogdot.FinalProject.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository; 
    
    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
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

    public List <UsuarioModel> buscarPorFecha(LocalDateTime fecha) throws Exception{
        try{
            List <UsuarioModel> usuarios = usuarioRepository.findByFechaDeCreacionAfter(fecha);
            return usuarios;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario){
        UsuarioModel usuarioExistente = usuarioRepository.findById(usuario.getId()).orElse(null);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setFechaDeCreacion(usuario.getFechaDeCreacion());
        usuarioExistente.setCiudad(usuario.getCiudad());
        usuarioExistente.setProvincia(usuario.getProvincia());
        usuarioExistente.setPais(usuario.getPais());
        return usuarioRepository.save(usuarioExistente);
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}
