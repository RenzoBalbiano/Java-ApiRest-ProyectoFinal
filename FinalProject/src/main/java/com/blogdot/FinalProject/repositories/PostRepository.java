package com.blogdot.FinalProject.repositories;

import java.util.List;
import com.blogdot.FinalProject.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {

    List<PostModel> findByTituloContaining(String titulo);

    List<PostModel> findByPublicado(Boolean publicado);

}
