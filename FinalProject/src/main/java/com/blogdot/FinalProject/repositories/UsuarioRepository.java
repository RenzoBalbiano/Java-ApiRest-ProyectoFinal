package com.blogdot.FinalProject.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.blogdot.FinalProject.models.UsuarioModel;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
	UsuarioModel findByEmail(String nombre);

	List<UsuarioModel> findByCiudad(String ciudad);

	List<UsuarioModel> findByFechaDeCreacion(LocalDateTime fechaDeCreacion);

}