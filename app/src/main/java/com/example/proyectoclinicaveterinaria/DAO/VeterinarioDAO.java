package com.example.proyectoclinicaveterinaria.DAO;

import com.example.proyectoclinicaveterinaria.Entidades.Veterinario;

import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    private static List<Veterinario> lista = new ArrayList<>();

    public List<Veterinario> Listar_Veterinario()
    {
        return lista;
    }

    public void Grabar_Veterinario(Veterinario obj){
        lista.add(obj);
    }

    public List<String> VeterinarioEsp(String espc)
    {
        List<String> miLista = new ArrayList<String>();
        //
        for (Veterinario vet: lista) {
            if (vet.getEspvet().equals(espc)) {
                miLista.add("Nombre: " + vet.getNomvet() + "\n" +
                        "Apellidos: " + vet.getApevet() + "\n" +
                        "Celular: " + vet.getTelevet());
            }
        }
        //
        return miLista;
    }
}
