package com.example.proyectoclinicaveterinaria.Entidades;

public class Historialmedico {
    private int idhistorial;
    private String enfermedadpadecida;
    private String tratamiento;
    private String fecha;
    private int idmedicacion;
    private int idmascotas;

    public int getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(int idhistorial) {
        this.idhistorial = idhistorial;
    }

    public String getEnfermedadpadecida() {
        return enfermedadpadecida;
    }

    public void setEnfermedadpadecida(String enfermedadpadecida) {
        this.enfermedadpadecida = enfermedadpadecida;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdmedicacion() {
        return idmedicacion;
    }

    public void setIdmedicacion(int idmedicacion) {
        this.idmedicacion = idmedicacion;
    }

    public int getIdmascotas() {
        return idmascotas;
    }

    public void setIdmascotas(int idmascotas) {
        this.idmascotas = idmascotas;
    }


}
