package com.example.proyectoclinicaveterinaria.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.Veterinario;
import com.example.proyectoclinicaveterinaria.R;

import java.util.List;

public class AdaptadorVeterinario extends ArrayAdapter<Veterinario> {
    Context mi_contexto;
    int mi_layout;
    List<Veterinario> mi_lista;
    public AdaptadorVeterinario(@NonNull Context context,
                           int resource,
                           @NonNull List<Veterinario> objects) {
        super(context, resource, objects);
        //
        mi_contexto = context;
        mi_layout = resource;
        mi_lista = objects;
    }

    //getview
    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View mi_vista = LayoutInflater.from(mi_contexto).inflate(mi_layout, null);

        TextView tvnombre = mi_vista.findViewById(R.id.tvNombreVeterinario);
        TextView tvapellido = mi_vista.findViewById(R.id.tvApellidoVeterinario);
        TextView tvtelefono = mi_vista.findViewById(R.id.tvTelefonoVeterinario);
        ImageView imagen = mi_vista.findViewById(R.id.imagenVeterinario);

        Veterinario obj = mi_lista.get(position);

        tvnombre.setText(obj.getNomvet());
        tvapellido.setText(obj.getApevet());
        tvtelefono.setText(obj.getTelevet());
        imagen.setImageResource(obj.getImgvet());

        return mi_vista;
    }
}
