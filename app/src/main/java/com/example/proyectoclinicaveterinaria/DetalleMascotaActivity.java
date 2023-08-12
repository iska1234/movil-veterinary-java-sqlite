package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetalleMascotaActivity extends AppCompatActivity {

    Button btnAgendarCita, btnRegresarDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        btnAgendarCita = findViewById(R.id.btnAgendarCita);
        btnRegresarDetalle = findViewById(R.id.btnRegresarDetalle);

        btnRegresarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAgendarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        DetalleMascotaActivity.this,RegistrarCitasActivity.class
                );
                startActivity(intento);
            }
        });
    }
}