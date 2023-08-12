package com.example.proyectoclinicaveterinaria.Entidades;

public class Especialidades {
    private int idespecialidades;
    private String nombredeespecialidad;
    private String descripcion;

    public int getIdespecialidades() {
        return idespecialidades;
    }

    public void setIdespecialidades(int idespecialidades) {
        this.idespecialidades = idespecialidades;
    }

    public String getNombredeespecialidad() {
        return nombredeespecialidad;
    }

    public void setNombredeespecialidad(String nombredeespecialidad) {
        this.nombredeespecialidad = nombredeespecialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString(){
        return nombredeespecialidad;
    }
}
