package com.blogdot.FinalProject.repositories;

import java.util.List;

import com.blogdot.FinalProject.models.UsuarioModel;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
	UsuarioModel findByEmail(String nombre);

	//@Query(value = "SELECT u FROM Usuario u WHERE u.ciudad LIKE %:filtro%")
	List<UsuarioModel> findByCiudad(String ciudad);

	
}