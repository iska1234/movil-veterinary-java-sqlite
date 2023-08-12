package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.proyectoclinicaveterinaria.Adaptador.ListaVeterinarioAdapter;
import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;
import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.example.proyectoclinicaveterinaria.Entidades.Especialidades;
import com.example.proyectoclinicaveterinaria.db.DbVeterinarioyEspecialidad;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListarVeterinarioporEspecialidad extends AppCompatActivity {

    Spinner xspinnerespecialidad ;
    Button btnbuscarespecialidad;
    RecyclerView lbVeterinariosconsultax;
    Integer idesp ;
    ArrayList<Medicacion> listaArrayVeterinario;
    ListaVeterinarioAdapter adapter;

    FloatingActionButton fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_veterinariopor_especialidad);
        EnlazarControles();

        fabMenu=findViewById(R.id.fabMenusito);

        List<Especialidades> listaCategorias = llenarCategorias();
        ArrayAdapter<Especialidades> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCategorias);
        xspinnerespecialidad.setAdapter(arrayAdapter);





        xspinnerespecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idesp =((Especialidades) parent.getSelectedItem()).getIdespecialidades();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lbVeterinariosconsultax.setLayoutManager(new LinearLayoutManager(this));

        DbVeterinarioyEspecialidad dbVeterinarios=new DbVeterinarioyEspecialidad(ListarVeterinarioporEspecialidad.this);
        listaArrayVeterinario=new ArrayList<>();
        adapter=new ListaVeterinarioAdapter(dbVeterinarios.MostrarveterinariosA());
        lbVeterinariosconsultax.setAdapter(adapter);


        btnbuscarespecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbVeterinarioyEspecialidad dbVeterinarios=new DbVeterinarioyEspecialidad(ListarVeterinarioporEspecialidad.this);
                listaArrayVeterinario=new ArrayList<>();
                adapter=new ListaVeterinarioAdapter(dbVeterinarios.MostrarveterinariosporEspecialidad(idesp));
                lbVeterinariosconsultax.setAdapter(adapter);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ListarVeterinarioporEspecialidad.this , MenuAdminActivity.class);

                startActivity(a);
            }
        });

    }


    void EnlazarControles(){

        lbVeterinariosconsultax = findViewById(R.id.lblistadoveterinariosconsulta);
        btnbuscarespecialidad = findViewById(R.id.btnBuscarEspecialidadx);
        xspinnerespecialidad = findViewById(R.id.spinnerespecialidadconsulta);
    }



    private List<Especialidades> llenarCategorias(){
        List<Especialidades> listaCat = new ArrayList<>();
        DbEspecialidades dbCategorias = new DbEspecialidades(ListarVeterinarioporEspecialidad.this);
        Cursor cursor = dbCategorias.mostarEspecialidadenSpinner();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Especialidades cat = new Especialidades();
                    cat.setIdespecialidades(cursor.getInt(cursor.getColumnIndex("idespecialidades")));
                    cat.setNombredeespecialidad(cursor.getString(cursor.getColumnIndex("NOMBREDEESPECIALIDAD")));
                    listaCat.add(cat);
                } while (cursor.moveToNext());
            }
        }
        dbCategorias.close();

        return listaCat;
    }
}