package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaMascotaAdapter;
import com.example.proyectoclinicaveterinaria.DAO.MascotaDAO;
import com.example.proyectoclinicaveterinaria.Entidades.Mascota;
import com.example.proyectoclinicaveterinaria.db.DbMascotas;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ListadoMascotasActivity extends AppCompatActivity {

    RecyclerView rvMascota;
    MascotaDAO dao = new MascotaDAO();
    ArrayList<Mascota> listaArrayMascota;

    FloatingActionButton fabRegistro, fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_mascotas);

        fabRegistro=findViewById(R.id.fabRegistroMascota);
        fabMenu=findViewById(R.id.fabMenuMascota);

        rvMascota=findViewById(R.id.rvMascota);

        rvMascota.setLayoutManager(new LinearLayoutManager(this));

        DbMascotas dbMascotas = new DbMascotas(ListadoMascotasActivity.this);

        listaArrayMascota = new ArrayList<>();

        ListaMascotaAdapter adapter = new ListaMascotaAdapter(dbMascotas.mostrarMascota(dao), dao);
        rvMascota.setAdapter(adapter);

        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoMascotasActivity.this , RegistrarMascotaActivity.class);

                startActivity(a);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoMascotasActivity.this , MenuPrincipalActivity.class);

                startActivity(a);
            }
        });


    }
}