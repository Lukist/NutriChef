package com.example.nutrichef.Modelos;

import java.io.Serializable;

public class ModeloReceta implements Serializable {
    private int id_receta;
    private String titulo;
    private String descripcion;
    private String categoria;
    private String ingredientes;
    private String pasos;
    private double valor_nutricional;
    private int id_usuario;

    public ModeloReceta() {
    }

    public ModeloReceta(int id_receta, String titulo, String descripcion, String categoria, String ingredientes, String pasos, double valor_nutricional, int id_usuario) {
        this.id_receta = id_receta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.valor_nutricional = valor_nutricional;
        this.id_usuario = id_usuario;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public double getValor_nutricional() {
        return valor_nutricional;
    }

    public void setValor_nutricional(double valor_nutricional) {
        this.valor_nutricional = valor_nutricional;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
