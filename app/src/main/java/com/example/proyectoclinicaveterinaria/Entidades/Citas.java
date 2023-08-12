package com.example.proyectoclinicaveterinaria.Entidades;

public class Citas {
    private int nrocitas;
    private String TIPODECITA;
    private String NOMBREMASCOTA;
    private String VETERINARIO;
    private String CLINICA;
    private String FECHACITA;
    private String HORACITA;
    private String DESCRIPCION;


    public int getNrocitas() {
        return nrocitas;
    }

    public void setNrocitas(int nrocitas) {
        this.nrocitas = nrocitas;
    }

    public String getTIPODECITA() {
        return TIPODECITA;
    }

    public void setTIPODECITA(String TIPODECITA) {
        this.TIPODECITA = TIPODECITA;
    }

    public String getNOMBREMASCOTA() {
        return NOMBREMASCOTA;
    }

    public void setNOMBREMASCOTA(String NOMBREMASCOTA) {
        this.NOMBREMASCOTA = NOMBREMASCOTA;
    }

    public String getVETERINARIO() {
        return VETERINARIO;
    }

    public void setVETERINARIO(String VETERINARIO) {
        this.VETERINARIO = VETERINARIO;
    }

    public String getCLINICA() {
        return CLINICA;
    }

    public void setCLINICA(String CLINICA) {
        this.CLINICA = CLINICA;
    }

    public String getFECHACITA() {
        return FECHACITA;
    }

    public void setFECHACITA(String FECHACITA) {
        this.FECHACITA = FECHACITA;
    }

    public String getHORACITA() {
        return HORACITA;
    }

    public void setHORACITA(String HORACITA) {
        this.HORACITA = HORACITA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
}
