package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaCitasAdapter;
import com.example.proyectoclinicaveterinaria.Entidades.Cita;
import com.example.proyectoclinicaveterinaria.Entidades.Citas;
import com.example.proyectoclinicaveterinaria.db.DbCitas;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ListadoCitasActivity extends AppCompatActivity {
    RecyclerView listacitasrv;
    ArrayList<Citas> listaArrayCitas;

    FloatingActionButton fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_citas);

        fabMenu=findViewById(R.id.fabMenuClitas);


        listacitasrv=findViewById(R.id.ListaCitasrv);
        listacitasrv.setLayoutManager(new LinearLayoutManager(this));
        DbCitas dbCitas=new DbCitas(ListadoCitasActivity.this);
        listaArrayCitas=new ArrayList<>();
        ListaCitasAdapter adapter=new ListaCitasAdapter(dbCitas.Mostrarcitas());
        listacitasrv.setAdapter(adapter);

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListadoCitasActivity.this , MenuPrincipalActivity.class);

                startActivity(a);
            }
        });

    }


}