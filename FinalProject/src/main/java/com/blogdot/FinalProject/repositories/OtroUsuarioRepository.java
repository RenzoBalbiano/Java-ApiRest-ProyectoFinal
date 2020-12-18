package com.blogdot.FinalProject.repositories;

import com.blogdot.FinalProject.models.UsuarioModel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtroUsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    List<UsuarioModel> findByFechaDeCreacionAfter(LocalDate fechaDeCreacion);
}
