package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbClinica;
import com.example.proyectoclinicaveterinaria.db.DbUsuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class NuevaClinica extends AppCompatActivity {

    TextInputEditText xsede , xdireccionclinica , xtelefonoclinica ;
    TextInputLayout tilSede, tilDireccion, tilTelefono;
    Button xbtnregistrarclinica,btnlistarclinicas ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_clinica);
        Enlazar();
        xbtnregistrarclinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidarDatos()==true)
                {

                    DbClinica dbclinica = new DbClinica(NuevaClinica.this);
                    long id = dbclinica.insertarClinica(xsede.getText().toString(),xdireccionclinica.getText().toString(),
                            Integer.parseInt(xtelefonoclinica.getText().toString()));
                    if (id>0){
                        String mensaje = "CLINICA REGISTRADA EXITOSAMENTE";
                        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();

                    }
                    else{
                        String mensaje = "ERROR AL REGISTRAR LA CITA";
                        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });

        btnlistarclinicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(NuevaClinica.this , ListadoClinica.class);

                startActivity(a);
            }
        });

    }

    boolean ValidarDatos()
    {

        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_celular = Pattern.compile("^9[0-9]{8}$");

        String sede = xsede.getText().toString();
        String direccion = xdireccionclinica.getText().toString();
        String telefono = xtelefonoclinica.getText().toString();


        // Sede
        tilSede.setError("");
        tilSede.setErrorEnabled(false);
        if (sede.equals(""))
        {
            tilSede.setError("La Sede es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(sede).matches()==false)
        {
            tilSede.setError("Error: Solo se permiten letras");
            return false;
        }

        // Direccion
        tilDireccion.setError("");
        tilDireccion.setErrorEnabled(false);
        if (direccion.equals(""))
        {
            tilDireccion.setError("El campo Dirección es Obligatorio");
            return false;
        }

        // telefono
        tilTelefono.setError("");
        tilTelefono.setErrorEnabled(false);
        if (telefono.equals(""))
        {
            tilTelefono.setError("El campo Telefono es Obligatorio");
            return false;
        }
        if (patron_celular.matcher(telefono).matches()==false)
        {
            tilTelefono.setError("Error: El telefono ingresado no es valido");
            return false;
        }

        // si no hubieron errores
        return true;
    }

    void Enlazar(){
        xsede = findViewById(R.id.edtsede);
        xdireccionclinica=findViewById(R.id.edtdireccionclinica);
        xtelefonoclinica=findViewById(R.id.edttelefonoclinica);
        tilSede = findViewById(R.id.tilSedeCli);
        tilDireccion=findViewById(R.id.tilDireCli);
        tilTelefono=findViewById(R.id.tilTelCli);
        xbtnregistrarclinica=findViewById(R.id.btnregistrarclinica);
        btnlistarclinicas=findViewById(R.id.btnlistarclinica);
    }
}