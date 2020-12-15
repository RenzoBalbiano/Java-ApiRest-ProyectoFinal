package com.blogdot.FinalProject.repositories;

import com.blogdot.FinalProject.models.UsuarioModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtroUsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    //List<UsuarioModel> findByStartDateAfter(LocalDateTime fechaDeCreacion);   
}
