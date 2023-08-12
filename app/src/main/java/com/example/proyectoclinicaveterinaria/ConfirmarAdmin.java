package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmarAdmin extends AppCompatActivity {

    EditText edtAdminU, edtAdminP;
    Button tvIngreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_admin);
        Enlazar();

        tvIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user="admin2022";
                String passw="1111";
                if (edtAdminU.getText().toString().equals(user) && edtAdminP.getText().toString().equals(passw)) {
                    Intent x = new Intent(ConfirmarAdmin.this, MenuAdminActivity.class);
                    startActivity(x);
                }else{
                    Limpiar();
                }
            }
        });

    }

    void Limpiar(){
        edtAdminP.setText("");
        edtAdminU.setText("");

    }
    void Enlazar(){
        edtAdminP=findViewById(R.id.edtAdminPassw);
        edtAdminU=findViewById(R.id.edtAdminUser);
        tvIngreso=findViewById(R.id.btnIngresoAdmin);

    }
}