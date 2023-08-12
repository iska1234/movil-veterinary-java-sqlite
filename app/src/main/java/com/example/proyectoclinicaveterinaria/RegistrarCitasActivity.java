package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegistrarCitasActivity extends AppCompatActivity {

    Button btnTratamiento,btnOperacion,btnVacunas,
            btnConsultaMedica,btnEstetica,btnExamenMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_citas);
        //
        EnlazarControles();
        Intent i =getIntent();
        String nommas=i.getStringExtra("NOMMAS");
        Toast.makeText(this, nommas, Toast.LENGTH_SHORT).show();

        btnTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        RegistrarCitasActivity.this,RegistrarCitas2Activity.class
                );
                String espc="Tratamiento";
                String nombreV="Rocio Sanchez";
                String telef="(01) 2714058";
                intento.putExtra("espc",espc);
                intento.putExtra("nombreV",nombreV);
                intento.putExtra("telef",telef);
                intento.putExtra("nommas",nommas);
                startActivity(intento);
            }
        });

        btnOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        RegistrarCitasActivity.this,RegistrarCitas2Activity.class
                );
                String espc="Operacion";
                String nombreV="Raul Montenegro";
                String telef="(01) 7155454";
                intento.putExtra("espc",espc);
                intento.putExtra("nombreV",nombreV);
                intento.putExtra("telef",telef);
                intento.putExtra("nommas",nommas);
                startActivity(intento);
            }
        });

        btnVacunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        RegistrarCitasActivity.this,RegistrarCitas2Activity.class
                );
                String espc="Vacunas";
                String nombreV="Luisa Fernandez";
                String telef="982 300 207";
                intento.putExtra("espc",espc);
                intento.putExtra("nombreV",nombreV);
                intento.putExtra("telef",telef);
                intento.putExtra("nommas",nommas);
                startActivity(intento);
            }
        });

        btnConsultaMedica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        RegistrarCitasActivity.this,RegistrarCitas2Activity.class
                );
                String espc="ConsultaMedica";
                String nombreV="Ricardo Estelaz";
                String telef="(01) 2684596";
                intento.putExtra("espc",espc);
                intento.putExtra("nombreV",nombreV);
                intento.putExtra("telef",telef);
                intento.putExtra("nommas",nommas);
                startActivity(intento);
            }
        });

        btnEstetica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        RegistrarCitasActivity.this,RegistrarCitas2Activity.class
                );
                String espc="Estetica";
                String nombreV="Melany Fuentes";
                String telef="(01) 2084900";
                intento.putExtra("espc",espc);
                intento.putExtra("nombreV",nombreV);
                intento.putExtra("telef",telef);
                intento.putExtra("nommas",nommas);
                startActivity(intento);
            }
        });

        btnExamenMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        RegistrarCitasActivity.this,RegistrarCitas2Activity.class
                );
                String espc="ExamenMedico";
                String nombreV="Jefferson Paulino";
                String telef="(01) 2714437";
                intento.putExtra("espc",espc);
                intento.putExtra("nombreV",nombreV);
                intento.putExtra("telef",telef);
                intento.putExtra("nommas",nommas);
                startActivity(intento);
            }
        });
    }

    void EnlazarControles(){

        btnTratamiento = findViewById(R.id.btnTratamiento);
        btnOperacion=findViewById(R.id.btnOperaciones);
        btnVacunas=findViewById(R.id.btnVacunas);
        btnConsultaMedica=findViewById(R.id.btnConsultaMedica);
        btnEstetica=findViewById(R.id.btnEstetica);
        btnExamenMedico=findViewById(R.id.btnExamenMedico);

    }
}