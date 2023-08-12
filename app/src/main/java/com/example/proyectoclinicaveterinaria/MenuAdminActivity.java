package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdminActivity extends AppCompatActivity {

    Button btnEspecialidad, btnClinica, btnMedicacion, btnHistorial, btnVeterinario, btnBuscarVetPorEsp, btnSalirLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        Enlazar();

        btnEspecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuAdminActivity.this,Nuevaespecialidad.class
                );
                startActivity(intento);
            }
        });

        btnClinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuAdminActivity.this,NuevaClinica.class
                );
                startActivity(intento);
            }
        });

        btnMedicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuAdminActivity.this,Nuevamedicacion.class
                );
                startActivity(intento);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuAdminActivity.this,Nuevohistorialmedico.class
                );
                startActivity(intento);
            }
        });

        btnVeterinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuAdminActivity.this,Nuevoveterinario.class
                );
                startActivity(intento);
            }
        });

        btnBuscarVetPorEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuAdminActivity.this,ListarVeterinarioporEspecialidad.class
                );
                startActivity(intento);
            }
        });

        btnSalirLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuAdminActivity.this,LoginActivity.class
                );
                startActivity(intento);
            }
        });

    }

    void Enlazar(){

        btnEspecialidad=findViewById(R.id.btnEspecialidad);
        btnClinica=findViewById(R.id.btnClinica);
        btnMedicacion=findViewById(R.id.btnMedicacion);
        btnHistorial=findViewById(R.id.btnHistorialMedico);
        btnVeterinario=findViewById(R.id.btnVeterinarios);
        btnBuscarVetPorEsp=findViewById(R.id.btnBuscarVetPorEsp);
        btnSalirLogin=findViewById(R.id.btnSalirLogin);

    }
}