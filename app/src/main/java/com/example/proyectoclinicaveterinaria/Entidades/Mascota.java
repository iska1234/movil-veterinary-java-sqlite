package com.example.proyectoclinicaveterinaria.Entidades;

public class Mascota {
    private int codmas;
    private String nommas;
    private String pesomas;
    private String razamas;
    private String especie;
    private String edad;
    private String sexomas;
    private String fotomas;


    public Mascota() {
    }

    public Mascota(int codmas, String nommas, String pesomas, String razamas, String especie, String edad, String sexomas, String fotomas) {
        this.codmas = codmas;
        this.nommas = nommas;
        this.pesomas = pesomas;
        this.razamas = razamas;
        this.especie = especie;
        this.edad = edad;
        this.sexomas = sexomas;
        this.fotomas = fotomas;
    }

    public int getCodmas() {
        return codmas;
    }

    public void setCodmas(int codmas) {
        this.codmas = codmas;
    }

    public String getNommas() {
        return nommas;
    }

    public void setNommas(String nommas) {
        this.nommas = nommas;
    }

    public String getPesomas() {
        return pesomas;
    }

    public void setPesomas(String pesomas) {
        this.pesomas = pesomas;
    }

    public String getRazamas() {
        return razamas;
    }

    public void setRazamas(String razamas) {
        this.razamas = razamas;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexomas() {
        return sexomas;
    }

    public void setSexomas(String sexomas) {
        this.sexomas = sexomas;
    }

    public String getFotomas() {
        return fotomas;
    }

    public void setFotomas(String fotomas) {
        this.fotomas = fotomas;
    }


    public String toString(){
        return nommas;
    }

}
