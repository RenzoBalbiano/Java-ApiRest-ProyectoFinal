package com.blogdot.FinalProject.repositories;

import com.blogdot.FinalProject.models.UsuarioModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
	UsuarioModel findByEmail(String nombre);
}