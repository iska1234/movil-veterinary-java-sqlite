package com.example.proyectoclinicaveterinaria.Entidades;

public class HistorialMedicacionyMascotas {

    private int idhistorial;
    private String enfermedadpadecida;
    private String tratamiento;
    private String fecha;
    private String  nombremedicacion;
    private String nombremascotas;


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

    public String getNombremedicacion() {
        return nombremedicacion;
    }

    public void setNombremedicacion(String nombremedicacion) {
        this.nombremedicacion = nombremedicacion;
    }

    public String getNombremascotas() {
        return nombremascotas;
    }

    public void setNombremascotas(String nombremascotas) {
        this.nombremascotas = nombremascotas;
    }

}
