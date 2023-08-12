package com.example.proyectoclinicaveterinaria.DAO;

import com.example.proyectoclinicaveterinaria.Entidades.Mascota;

import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
private static List<Mascota> lista = new ArrayList<>();

    public List<Mascota> MascotaListar()
    {
        return lista;
    }
    public int tamanio(){
        return lista.size();
    }

    public int BuscarPosicionMascota(int codigo){
        for (int pos = 0; pos < lista.size();pos++){
            if (codigo == (lista.get(pos).getCodmas())){
                return pos;
            }
        }
        return -1;
    }

    public void ActualizarEMascota(int posi,Mascota mascota){
        lista.get(posi).setNommas(mascota.getNommas());
        lista.get(posi).setEdad(mascota.getEdad());
        lista.get(posi).setEspecie(mascota.getEspecie());
        lista.get(posi).setPesomas(mascota.getPesomas());
        lista.get(posi).setRazamas(mascota.getRazamas());
        lista.get(posi).setSexomas(mascota.getSexomas());
    }
    public void EliminarMascota(int posi){
        lista.remove(posi);
    }
    public Mascota MascotaEliminar(int codigo){
        for (Mascota masc: lista) {
            if (masc.getCodmas() == codigo)
                return masc;
        }
        //
        return null;
    }
    public Mascota MascotaBuscarPorPosicion(int posicion){//buscar x posicion en la lista
        for(int po=0;po<lista.size();po++){
            if(po==posicion){
                return lista.get(po);
            }
        }
        //
        return null;
    }
    public Mascota MascotaBuscar(int codigo){//buscar x codigo
        for (Mascota masc: lista) {
            if (masc.getCodmas() == codigo)
                return masc;
        }
        //
        return null;
    }

    public List<String> MascotaListado()
    {
        List<String> miLista = new ArrayList<String>();
        //
        for (Mascota alu: lista) {
            miLista.add("Nombre: " + alu.getNommas() + "\n" +
                    "Peso: " + alu.getPesomas() + "\n" +
                    "Raza: " + alu.getRazamas() + "\n" +
                    "Especie: " + alu.getEspecie() + "\n" +
                    "Edad: " + alu.getEdad() + "\n" +
                    "Sexo: " + alu.getSexomas());
        }
        //
        return miLista;
    }

    public String MascotaGuardar(Mascota obj)
    {
        if (MascotaBuscar(obj.getCodmas())==null){
            lista.add(obj);
            return "Nueva Mascota registrada";
        }
        //
        return "Mascota duplicada";
    }
}

