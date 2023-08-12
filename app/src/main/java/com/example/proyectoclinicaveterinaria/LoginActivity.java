package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbHelper;
import com.example.proyectoclinicaveterinaria.db.DbUsuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    ImageView imgMascota;
    Button btnIngresar, btnRegresarLogin, btnOlvidarPassw;
    TextInputEditText edtContra, edtCorreo;
    TextInputLayout tilContra, tilCorreo;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EnlazarControles();

        imgMascota.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent i = new Intent(LoginActivity.this,ConfirmarAdmin.class);
                startActivity(i);
                return true;
            }
        });


        db = new DbHelper(this);

        btnRegresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        LoginActivity.this,MainActivity.class
                );
                startActivity(i);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo = edtCorreo.getText().toString();
                String contrasena = edtContra.getText().toString();

                if(ValidarDatos()==true){
                    if(TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(contrasena)){
                        Toast.makeText(LoginActivity.this, "Campos requeridos", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean checkcorreocontra = db.checkcorreocontra(correo,contrasena);
                        if(checkcorreocontra==true){
                            Toast.makeText(LoginActivity.this, "BIENVENIDO", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,MenuPrincipalActivity.class);
                            i.putExtra("EMAIL",correo);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnOlvidarPassw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(x);
            }
        });
    }

    void EnlazarControles() {
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegresarLogin = findViewById(R.id.btnRegresarLogin);
        btnOlvidarPassw=findViewById(R.id.btnOlvidarPassw);
        edtContra = findViewById(R.id.edtContra);
        edtCorreo = findViewById(R.id.edtCorreo);
        tilContra = findViewById(R.id.tilContra);
        tilCorreo = findViewById(R.id.tilCorreo);

        imgMascota=findViewById(R.id.imgLoginMascota);

    }

    boolean ValidarDatos()
    {

        String correo = edtCorreo.getText().toString();
        String contra = edtContra.getText().toString();

        // contra
        tilContra.setError("");
        tilContra.setErrorEnabled(false);
        if (contra.equals(""))
        {
            tilContra.setError("Campo vacio");
            return false;
        }

        // email
        tilCorreo.setError("");
        tilCorreo.setErrorEnabled(false);
        if (correo.equals(""))
        {
            tilCorreo.setError("El Correo es Obligatorio");
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(correo).matches()==false)
        {
            tilCorreo.setError("Error, No es un Correo valido");
            return false;
        }

        // si no hubieron errores
        return true;
    }
}