package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaVeterinarioAdapter;
import com.example.proyectoclinicaveterinaria.db.DbVeterinarios;
import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;
import com.example.proyectoclinicaveterinaria.db.DbVeterinarioyEspecialidad;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ListadoVeterinario extends AppCompatActivity implements SearchView.OnQueryTextListener{
    RecyclerView listaVeterinariorv;
    ArrayList<Medicacion> listaArrayVeterinario;
    SearchView txtbuscarveterinario;
    ListaVeterinarioAdapter adapter;

    FloatingActionButton fabRegistro, fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_veterinario);

        fabRegistro=findViewById(R.id.fabRegistroVeterinario);
        fabMenu=findViewById(R.id.fabMenuVeterinario);

        txtbuscarveterinario=findViewById(R.id.txtbuscarveterinarios);
        listaVeterinariorv=findViewById(R.id.ListaVeterinariosrv);
        listaVeterinariorv.setLayoutManager(new LinearLayoutManager(this));

        DbVeterinarioyEspecialidad dbVeterinarios=new DbVeterinarioyEspecialidad(ListadoVeterinario.this);
        listaArrayVeterinario=new ArrayList<>();
        adapter=new ListaVeterinarioAdapter(dbVeterinarios.MostrarveterinariosA());
        listaVeterinariorv.setAdapter(adapter);

        txtbuscarveterinario.setOnQueryTextListener(this);

        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoVeterinario.this , Nuevoveterinario.class);

                startActivity(a);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoVeterinario.this , MenuAdminActivity.class);

                startActivity(a);
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}