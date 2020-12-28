package com.blogdot.FinalProject.repositories;

import java.util.List;
import com.blogdot.FinalProject.models.ComentarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

public interface ComentarioRepository extends CrudRepository<ComentarioModel,Long>{

    @Query(value = "SELECT * FROM Comentarios WHERE fk_post = ?1 LIMIT ?2", nativeQuery = true)
    List<ComentarioModel> getComentarioPorLimite(Long id, Integer limit);        
    //nativeQuery = true, value = "select * from Comentarios s WHERE fk_post = ?1 ORDER BY s.fecha_de_creacion desc LIMIT ?1")


}
