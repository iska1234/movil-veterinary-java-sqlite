package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbMascotas;
import com.example.proyectoclinicaveterinaria.db.DbUsuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class RegistrarMascotaActivity extends AppCompatActivity {

    TextInputEditText xnombremas , xpesomas , xraza , xespecie , xedad ;
    TextInputLayout tilNombre, tilPeso, tilRaza, tilEspecie, tilEdad;
    Button xbtnregistrarmas, btnRegresarMascota ;
    RadioButton xrbmacho , xrrbhembra;
    Spinner spinnersedescedt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);

        EnlazarControles();

        xrbmacho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                xrrbhembra.setChecked(false);
            }
        });

        xrrbhembra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                xrbmacho.setChecked(false);
            }
        });


        xbtnregistrarmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidarDatos()==true)
                {
                    String xgenero=SexoMacota();
                    DbMascotas dbMascotas = new DbMascotas(RegistrarMascotaActivity.this);
                    if (!xnombremas.getText().toString().isEmpty() && !xpesomas.getText().toString().isEmpty() && !xraza.getText().toString().isEmpty() && !xespecie.getText().toString().isEmpty() && !xedad.getText().toString().isEmpty()){
                        long id = dbMascotas.insertarMascota(xnombremas.getText().toString(),Double.parseDouble(xpesomas.getText().toString()),xraza.getText().toString(),
                                xespecie.getText().toString(),xgenero,xedad.getText().toString());
                        //Log.d("ID: ","RETORNA DE REGISTRAR: .."+id);
                        if (id>0){
                            LimpiarControles();
                            String mensaje = "MASCOTA REGISTRADO EXITOSAMENTE";
                            Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                    .setTextColor(Color.CYAN)
                                    .show();

                            Intent i = new Intent(RegistrarMascotaActivity.this,ListadoMascotasActivity.class);
                            startActivity(i);
                        }
                        else{
                            String mensaje = "ERROR!!..NO SE PUEDO GUARDAR";
                            Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                    .setTextColor(Color.CYAN)
                                    .show();
                        }
                    }

                    else Toast.makeText(RegistrarMascotaActivity.this, "FALTAN INGRESAR DATOS DE LA MASCOTA", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnRegresarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    String SexoMacota(){
        String selecionado="";
        if (xrrbhembra.isChecked()) selecionado="Hembra";
        if (xrbmacho.isChecked()) selecionado="Macho";
        return selecionado;
    }

    boolean ValidarDatos()
    {

        //Pattern patron_codigo = Pattern.compile("^E[0-9]{5}$");
        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_numeros = Pattern.compile("^[0-9]+$");

        String nombre = xnombremas.getText().toString();
        String especie = xespecie.getText().toString();
        String edad = xedad.getText().toString();
        String peso = xpesomas.getText().toString();
        String raza = xraza.getText().toString();


        // nombre
        tilNombre.setError("");
        tilNombre.setErrorEnabled(false);
        if (nombre.equals(""))
        {
            tilNombre.setError("El Nombre es Obligatorio");
            return false;
        }
        if (patron_letras.matcher(nombre).matches()==false)
        {
            tilNombre.setError("Error: Solo se permiten letras");
            return false;
        }

        // especie
        tilEspecie.setError("");
        tilEspecie.setErrorEnabled(false);
        if (especie.equals(""))
        {
            tilEspecie.setError("La Especie es Obligatoria");
            return false;
        }
        if (patron_letras.matcher(especie).matches()==false)
        {
            tilEspecie.setError("Error: Solo se permiten letras");
            return false;
        }

        // raza
        tilRaza.setError("");
        tilRaza.setErrorEnabled(false);
        if (raza.equals(""))
        {
            tilRaza.setError("La Raza es Obligatoria");
            return false;
        }
        if (patron_letras.matcher(raza).matches()==false)
        {
            tilRaza.setError("Error: Solo se permiten letras");
            return false;
        }

        // Edad
        tilEdad.setError("");
        tilEdad.setErrorEnabled(false);
        if (edad.equals(""))
        {
            tilEdad.setError("El campo Edad es Obligatorio");
            return false;
        }
        if (patron_numeros.matcher(edad).matches()==false)
        {
            tilEdad.setError("Error: Solo se permiten numeros");
            return false;
        }

        // Peso
        tilPeso.setError("");
        tilPeso.setErrorEnabled(false);
        if (peso.equals(""))
        {
            tilPeso.setError("El campo Peso es Obligatorio");
            return false;
        }
        if (patron_numeros.matcher(peso).matches()==false)
        {
            tilPeso.setError("Error: Solo se permiten numeros");
            return false;
        }

        // si no hubieron errores
        return true;
    }


    void EnlazarControles(){
        xnombremas = findViewById(R.id.edtnombremascota);
        xpesomas = findViewById(R.id.edtpesomascota);
        xraza = findViewById(R.id.edtrazamascota);
        xespecie = findViewById(R.id.edtespeciemascota);
        xedad= findViewById(R.id.edtedadmascota);

        tilNombre = findViewById(R.id.tilNomMas);
        tilPeso = findViewById(R.id.tilPesoMas);
        tilRaza = findViewById(R.id.tilRazaMas);
        tilEspecie = findViewById(R.id.tilEspecieMas);
        tilEdad= findViewById(R.id.tilEdadMas);

        xrbmacho= findViewById(R.id.rbmacho);
        xrrbhembra = findViewById(R.id.rbhembra);
        xbtnregistrarmas = findViewById(R.id.btnRegistrarMascota);
        btnRegresarMascota = findViewById(R.id.btnRegresarMascota);
        spinnersedescedt = findViewById(R.id.spinnersedescedt);
    }

    void LimpiarControles(){
        xnombremas.setText(null);
        xpesomas.setText(null);
        xraza.setText(null);
        xespecie.setText(null);
        xrbmacho.setChecked(true);
        xrrbhembra.setChecked(false);
        xedad.setText(null);
        //foto.setText(null);

    }
}