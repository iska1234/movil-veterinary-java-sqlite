package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaClinicaAdapter;
import com.example.proyectoclinicaveterinaria.db.DbClinica;
import com.example.proyectoclinicaveterinaria.Entidades.Clinica;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ListadoClinica extends AppCompatActivity implements  SearchView.OnQueryTextListener{
    RecyclerView listaClinicarv;
    ArrayList<Clinica> listaArrayClinica;
    SearchView txtbuscarclinica;
    ListaClinicaAdapter adapter;

    FloatingActionButton fabRegistro, fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_clinica);

        fabRegistro=findViewById(R.id.fabRegistroClinica);
        fabMenu=findViewById(R.id.fabMenuClinica);

        txtbuscarclinica=findViewById(R.id.txtbuscarclinica);
        listaClinicarv=findViewById(R.id.ListaClinicasrv);
        listaClinicarv.setLayoutManager(new LinearLayoutManager(this));

        DbClinica dbClinica=new DbClinica(ListadoClinica.this);
        listaArrayClinica=new ArrayList<>();
        adapter=new ListaClinicaAdapter(dbClinica.Mostrarclinica());
        listaClinicarv.setAdapter(adapter);

        txtbuscarclinica.setOnQueryTextListener(this);

        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoClinica.this , NuevaClinica.class);

                startActivity(a);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoClinica.this , MenuAdminActivity.class);

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