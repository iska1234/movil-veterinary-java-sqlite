package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaHistorialmedicoAdapter;
import com.example.proyectoclinicaveterinaria.db.DbHistorialmedico;
import com.example.proyectoclinicaveterinaria.Entidades.Historialmedico;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ListadoHistorialMedico extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView buscadortratamiento ;
    ArrayList<Historialmedico> listaArrayHistorialmedico;
    RecyclerView listaHistorialmedico;
    ListaHistorialmedicoAdapter adapter;

    FloatingActionButton fabRegistro, fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_historial_medico);

        fabRegistro=findViewById(R.id.fabRegistroHistorial);
        fabMenu=findViewById(R.id.fabMenuHistorial);


        buscadortratamiento = findViewById(R.id.txtbuscartramiento);
        listaHistorialmedico = findViewById(R.id.ListaHistorialmedicorv);

        listaHistorialmedico.setLayoutManager(new LinearLayoutManager(this));

        DbHistorialmedico dbHistorialmedico =new DbHistorialmedico(ListadoHistorialMedico.this);
        listaArrayHistorialmedico=new ArrayList<>();
        adapter=new ListaHistorialmedicoAdapter(dbHistorialmedico.Mostrarhistorialmedico());
        listaHistorialmedico.setAdapter(adapter);

        buscadortratamiento.setOnQueryTextListener(this);

        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoHistorialMedico.this , Nuevohistorialmedico.class);

                startActivity(a);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoHistorialMedico.this , MenuAdminActivity.class);

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