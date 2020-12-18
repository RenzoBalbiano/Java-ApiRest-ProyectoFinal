package com.blogdot.FinalProject.DTO;

import java.time.LocalDate;

public class PostDto {

    
    
    private Long id;

    private String titulo;
    private String descripcion;

    private String contenido;

    //@CreationTimestamp
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDeCreacion;

    private boolean publicado;

    public PostDto(Long id, String titulo, String descripcion, String contenido, LocalDate fechaDeCreacion,
            boolean publicado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.fechaDeCreacion = fechaDeCreacion;
        this.publicado = publicado;
    }

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

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public PostDto() {
    }

    
}
