package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbClinica;
import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Nuevaespecialidad extends AppCompatActivity {
    TextInputEditText edtnombreespecialidad,edtdescripcionespecialidad;
    TextInputLayout tilNomEsp, tilDescEsp;
    Button btnguardarnuevaespecialidad,btnlistaespecialidad;
    FloatingActionButton fabMenu, fabListado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevaespecialidad);
        enlazarcontroles();

        btnguardarnuevaespecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidarDatos()==true)
                {

                    DbEspecialidades dbEspecialidades = new DbEspecialidades(Nuevaespecialidad.this);
                    long id = dbEspecialidades.insertarespecialidad(edtnombreespecialidad.getText().toString(),edtdescripcionespecialidad.getText().toString());
                    if (id>0){
                        String mensaje = "ESPECIALIDAD REGISTRADA EXITOSAMENTE";
                        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                    else{
                        String mensaje = "ERROR EN GUARDAR LOS DATOS";
                        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });


        btnlistaespecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Nuevaespecialidad.this , ListadoEspecialidad.class);

                startActivity(a);
            }
        });

        fabListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Nuevaespecialidad.this , ListadoEspecialidad.class);

                startActivity(a);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Nuevaespecialidad.this , MenuAdminActivity.class);

                startActivity(a);
            }
        });
    }

    boolean ValidarDatos()
    {

        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");

        String nombre = edtnombreespecialidad.getText().toString();
        String descripcion = edtnombreespecialidad.getText().toString();


        // Nombre
        tilNomEsp.setError("");
        tilNomEsp.setErrorEnabled(false);
        if (nombre.equals(""))
        {
            tilNomEsp.setError("El nombre es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(nombre).matches()==false)
        {
            tilNomEsp.setError("Error: Solo se permiten letras");
            return false;
        }

        // Descripcion
        tilDescEsp.setError("");
        tilDescEsp.setErrorEnabled(false);
        if (descripcion.equals(""))
        {
            tilDescEsp.setError("La Descripcion es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(descripcion).matches()==false)
        {
            tilDescEsp.setError("Error: Solo se permiten letras");
            return false;
        }

        // si no hubieron errores
        return true;
    }

    void enlazarcontroles(){
        edtnombreespecialidad=findViewById(R.id.edtnombredeespecialidad);
        edtdescripcionespecialidad=findViewById(R.id.edtdescripcionespecialidad);
        tilNomEsp=findViewById(R.id.tilNomEsp);
        tilDescEsp=findViewById(R.id.tilDescEsp);
        btnguardarnuevaespecialidad=findViewById(R.id.btnregistrarnuevaespecialidad);
        btnlistaespecialidad=findViewById(R.id.btnlistaespecialidad);

        fabMenu=findViewById(R.id.fabMenuEsp);
        fabListado=findViewById(R.id.fablistEspecialidad);
    }


}