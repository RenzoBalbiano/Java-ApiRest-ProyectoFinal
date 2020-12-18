package com.blogdot.FinalProject.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "Comentarios")
public class ComentarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;

    //@CreationTimestamp
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDeCreacion;

    @Column(name = "comentario", length=200)
    private String comentario;

    private String autor;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Post",referencedColumnName = "id")
    @JsonIgnore
    private PostModel FK_Post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public PostModel getFK_Post() {
        return FK_Post;
    }

    public void setFK_Post(PostModel fK_Post) {
        FK_Post = fK_Post;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
