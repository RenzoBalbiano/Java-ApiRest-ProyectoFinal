package com.blogdot.FinalProject.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.CreationTimestamp;
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Posteos")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;

    private String titulo;
    private String descripcion;

    @Lob
    @Column(updatable = true, name="contenido", nullable = false)
    private String contenido;

    //@CreationTimestamp
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDeCreacion;

    private boolean publicado;

    //(mappedBy = "Posteos")
    @OneToMany
    private List<ComentarioModel> comentarios = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author",referencedColumnName = "id")
    private UsuarioModel author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    //@JsonIgnore
    public UsuarioModel getAuthor() {
        return author;
    }

    public void setAuthor(UsuarioModel author) {
        this.author = author;
    }

    public void agregarComentario(ComentarioModel comentario){
        this.comentarios.add(comentario);
        comentario.setFK_Post(this);
    }   
}