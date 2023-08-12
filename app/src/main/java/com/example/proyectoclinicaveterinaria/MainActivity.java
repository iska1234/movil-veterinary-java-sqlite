package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectoclinicaveterinaria.DAO.VeterinarioDAO;
import com.example.proyectoclinicaveterinaria.Entidades.Veterinario;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegistrarse;
    VeterinarioDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnlazarControles();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MainActivity.this,LoginActivity.class
                );
                startActivity(intento);
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MainActivity.this,RegistrarUsuarioActivity.class
                );
                startActivity(intento);
            }
        });
    }

    void EnlazarControles() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
    }



}