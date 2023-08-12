package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaMedicacionAdapter;
import com.example.proyectoclinicaveterinaria.db.DbMedicacion;
import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ListadoMedicacion extends AppCompatActivity implements SearchView.OnQueryTextListener{
    RecyclerView listaMedicacionrv;
    ArrayList<Medicacion> listaArrayMedicacion;
    SearchView txtbuscarmedicacion;
    ListaMedicacionAdapter adapter;

    FloatingActionButton fabRegistro, fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_medicacion);

        fabRegistro=findViewById(R.id.fabRegistroMedicamentos);
        fabMenu=findViewById(R.id.fabMenuMedicamentos);


        txtbuscarmedicacion=findViewById(R.id.txtbuscarmedicacion);
        listaMedicacionrv=findViewById(R.id.ListaMedicacionesrv);
        listaMedicacionrv.setLayoutManager(new LinearLayoutManager(this));

        DbMedicacion dbMedicacion=new DbMedicacion(ListadoMedicacion.this);
        listaArrayMedicacion=new ArrayList<>();
        adapter=new ListaMedicacionAdapter(dbMedicacion.Mostrarmedicacion());
        listaMedicacionrv.setAdapter(adapter);

        txtbuscarmedicacion.setOnQueryTextListener(this);

        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoMedicacion.this , Nuevamedicacion.class);

                startActivity(a);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoMedicacion.this , MenuAdminActivity.class);

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