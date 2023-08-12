package com.example.proyectoclinicaveterinaria.Entidades;

public class Veterinario {
    private int codvet;
    private String nomvet;
    private String apevet;
    private String televet;
    private String espvet;
    private int imgvet;

    public Veterinario(){
    }

    public Veterinario(int codvet, String nomvet, String apevet, String televet, String espvet, int imgvet) {
        this.codvet = codvet;
        this.nomvet = nomvet;
        this.apevet = apevet;
        this.televet = televet;
        this.espvet = espvet;
        this.imgvet = imgvet;
    }

    public int getCodvet() {
        return codvet;
    }

    public void setCodvet(int codvet) {
        this.codvet = codvet;
    }

    public String getNomvet() {
        return nomvet;
    }

    public void setNomvet(String nomvet) {
        this.nomvet = nomvet;
    }

    public String getApevet() {
        return apevet;
    }

    public void setApevet(String apevet) {
        this.apevet = apevet;
    }

    public String getTelevet() {
        return televet;
    }

    public void setTelevet(String televet) {
        this.televet = televet;
    }

    public String getEspvet() {
        return espvet;
    }

    public void setEspvet(String espvet) {
        this.espvet = espvet;
    }

    public int getImgvet() {
        return imgvet;
    }

    public void setImgvet(int imgvet) {
        this.imgvet = imgvet;
    }
}
