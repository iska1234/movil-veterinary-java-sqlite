package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectoclinicaveterinaria.db.DbHelper;

public class BaseDeDatosActivity extends AppCompatActivity {

    Button btncrear, btnnuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_de_datos);

        btncrear = findViewById(R.id.btnbasededatos);

        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper dbHelper = new DbHelper(BaseDeDatosActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(BaseDeDatosActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BaseDeDatosActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}