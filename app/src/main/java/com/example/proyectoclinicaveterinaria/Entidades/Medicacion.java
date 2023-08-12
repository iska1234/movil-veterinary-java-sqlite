package com.example.proyectoclinicaveterinaria.Entidades;

public class Medicacion {
    private int idmedicacion;
    private String nombre;
    private int precio;
    private int foto;

    public int getIdmedicacion() {
        return idmedicacion;
    }

    public void setIdmedicacion(int idmedicacion) {
        this.idmedicacion = idmedicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String toString(){
        return nombre;
    }
}
