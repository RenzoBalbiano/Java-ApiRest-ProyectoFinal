package com.blogdot.FinalProject.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "Comentarios")
public class ComentarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime fechaDeCreacion;

    @Column(name = "comentario", length=200)
    private String comentario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_comentario",referencedColumnName = "id")
    private UsuarioModel author_comentario;

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

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public UsuarioModel getAuthor_comentario() {
        return author_comentario;
    }

    public void setAuthor_comentario(UsuarioModel author_comentario) {
        this.author_comentario = author_comentario;
    }
}
