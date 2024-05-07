package com.example.nutrichef.Modelos;

import java.io.Serializable;

public class ModeloReceta implements Serializable {
    int idReceta;
    String titulo;
    String descripcion;
    String categoria;
    String ingrediente;
    String pasos;
    double valorNutricional;
    long usuarioId;

    public ModeloReceta() {
    }

    public ModeloReceta(int idReceta, String titulo, String descripcion, String categoria, String ingrediente, String pasos, double valorNutricional, long usuarioId) {
        this.idReceta = idReceta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ingrediente = ingrediente;
        this.pasos = pasos;
        this.valorNutricional = valorNutricional;
        this.usuarioId = usuarioId;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
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

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public double getValorNutricional() {
        return valorNutricional;
    }

    public void setValorNutricional(double valorNutricional) {
        this.valorNutricional = valorNutricional;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
