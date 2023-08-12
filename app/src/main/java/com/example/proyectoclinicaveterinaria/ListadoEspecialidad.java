package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaEspecialidadAdapter;
import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.example.proyectoclinicaveterinaria.Entidades.Especialidades;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ListadoEspecialidad extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView listaEspecialidadrv;
    ArrayList<Especialidades> listaArrayEspecialidades;
    SearchView txtbuscarespecialidad;
    ListaEspecialidadAdapter adapter;

    FloatingActionButton fabRegistro, fabMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_especialidad);

        fabRegistro=findViewById(R.id.fabRegistroEsp);
        fabMenu=findViewById(R.id.fabMenuEspecialidad);

        txtbuscarespecialidad=findViewById(R.id.txtbuscarespecialidad);
        listaEspecialidadrv=findViewById(R.id.ListaEspecialidadesrv);


        listaEspecialidadrv.setLayoutManager(new LinearLayoutManager(this));

        DbEspecialidades dbEspecialidades=new DbEspecialidades(ListadoEspecialidad.this);
        listaArrayEspecialidades=new ArrayList<>();
        adapter=new ListaEspecialidadAdapter(dbEspecialidades.Mostrarespecialidad());
        listaEspecialidadrv.setAdapter(adapter);

        txtbuscarespecialidad.setOnQueryTextListener(this);


        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoEspecialidad.this , Nuevaespecialidad.class);

                startActivity(a);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoEspecialidad.this , MenuAdminActivity.class);

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