package com.example.proyectoclinicaveterinaria.Entidades;

public class Clinica {
    private int idclinica;
    private String sede;
    private String direccion;
    private int telefono;

    public int getIdclinica() {
        return idclinica;
    }

    public void setIdclinica(int idclinica) {
        this.idclinica = idclinica;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
