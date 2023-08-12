package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.example.proyectoclinicaveterinaria.db.DbMedicacion;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Nuevamedicacion extends AppCompatActivity {
    TextInputEditText edtnuevonombremedicacion,edtnuevopreciomedicacion;
    TextInputLayout tilNomMedi, tilPrecioMedi;
    Button btnnuevamedicacacion,btnlistarmedicaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevamedicacion);
        enlazarcontroles();



        btnnuevamedicacacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidarDatos()==true)
                {

                    DbMedicacion dbMedicacion = new DbMedicacion(Nuevamedicacion.this);
                    long id = dbMedicacion.insertarmedicacion(edtnuevonombremedicacion.getText().toString(),
                            Integer.parseInt(edtnuevopreciomedicacion.getText().toString()));
                    if (id>0){
                        String mensaje = "MEDICAMENTO REGISTRADO EXITOSAMENTE";
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


        btnlistarmedicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Nuevamedicacion.this , ListadoMedicacion.class);




                startActivity(a);
            }
        });
    }

    boolean ValidarDatos()
    {

        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_numeros = Pattern.compile("^[0-9]+$");


        String nombre = edtnuevonombremedicacion.getText().toString();
        String precio = edtnuevopreciomedicacion.getText().toString();



        // Nombre
        tilNomMedi.setError("");
        tilNomMedi.setErrorEnabled(false);
        if (nombre.equals(""))
        {
            tilNomMedi.setError("El nombre es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(nombre).matches()==false)
        {
            tilNomMedi.setError("Error: Solo se permiten letras");
            return false;
        }

        // Precio
        tilPrecioMedi.setError("");
        tilPrecioMedi.setErrorEnabled(false);
        if (precio.equals(""))
        {
            tilPrecioMedi.setError("El campo precio es Obligatorio");
            return false;
        }
        if (patron_numeros.matcher(precio).matches()==false)
        {
            tilPrecioMedi.setError("Error: Solo se permiten numeros");
            return false;
        }

        // si no hubieron errores
        return true;
    }

    void enlazarcontroles(){
        edtnuevonombremedicacion=findViewById(R.id.edtnuevonombremedicacion);
        edtnuevopreciomedicacion=findViewById(R.id.edtnuevopreciomedicacion);
        tilNomMedi=findViewById(R.id.tilNomMedi);
        tilPrecioMedi=findViewById(R.id.tilPrecioMedi);
        btnnuevamedicacacion=findViewById(R.id.btnguardarnuevamedicacion);
        btnlistarmedicaciones=findViewById(R.id.btnlistahistorial);
    }
}