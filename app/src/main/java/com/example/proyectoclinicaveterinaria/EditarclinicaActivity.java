package com.example.proyectoclinicaveterinaria;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbClinica;
import com.example.proyectoclinicaveterinaria.Entidades.Clinica;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class EditarclinicaActivity extends AppCompatActivity {
    TextInputEditText edteditarsedeclinica,edteditardireccionclinica,edteditartelefonoclinica;
    TextInputLayout tilSede, tilDireccion, tilTelefono;
    Button btnguardareditarclinica,btnregresarlista;
    boolean correcto=false;
    Clinica clinica;
    int id=0;
    FloatingActionButton fabeliminarclinica,fabregresalistaclinica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarclinica);
        enlazarcontroles();




        if(savedInstanceState==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                id= Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");
            }

        }else{
            id=(Integer) savedInstanceState.getSerializable("ID");
        }
        DbClinica dbClinica =new DbClinica(EditarclinicaActivity.this);
        clinica= dbClinica.verclinica(id);

        if(clinica!=null){
            edteditarsedeclinica.setText(clinica.getSede());
            edteditardireccionclinica.setText(clinica.getDireccion());
            edteditartelefonoclinica.setText(String.valueOf(clinica.getTelefono()));



        }

        btnguardareditarclinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ValidarDatos()==true)
                {

                    correcto= dbClinica.editarClinica(id,edteditarsedeclinica.getText().toString(),edteditardireccionclinica.getText().toString(),Integer.parseInt(edteditartelefonoclinica.getText().toString()));
                    if(correcto){
                        String mensaje = "CLINICA ACTUALIZADA CORRECTAMENTE";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }else{
                        String mensaje = "ERROR AL ACTUALIZAR CLINICA";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });
        btnregresarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarclinicaActivity.this , ListadoClinica.class);

                startActivity(a);
            }
        });

        fabregresalistaclinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarclinicaActivity.this , ListadoClinica.class);

                startActivity(a);
            }
        });

        fabeliminarclinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(EditarclinicaActivity.this);
                builder.setMessage("¿Desea eliminar esta clinica?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        ProgressDialog pd= ProgressDialog.show(
                                EditarclinicaActivity.this,
                                "Espere un momento",
                                "Eliminando clinica...",
                                true
                        );
                        Thread hilo=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(5000);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();

                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dbClinica.eliminarClinica(id);
                                        lista();

                                    }
                                });
                                pd.dismiss();
                            }
                        });
                        hilo.start();
                    }

                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }

    private void lista(){
        Intent intent=new Intent(this,ListadoClinica.class);
        startActivity(intent);
    }

    boolean ValidarDatos()
    {

        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_celular = Pattern.compile("^9[0-9]{8}$");

        String sede = edteditarsedeclinica.getText().toString();
        String direccion = edteditardireccionclinica.getText().toString();
        String telefono = edteditartelefonoclinica.getText().toString();


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

    void enlazarcontroles(){
        edteditarsedeclinica=findViewById(R.id.edtsedeeditarclinica);
        edteditardireccionclinica=findViewById(R.id.edtdireccioneditarclinica);
        edteditartelefonoclinica=findViewById(R.id.edteditartelefonoclinica);

        tilSede = findViewById(R.id.tilSedeCliUpdate);
        tilDireccion=findViewById(R.id.tilDireCliUpdate);
        tilTelefono=findViewById(R.id.tilTelCliUpdate);

        btnguardareditarclinica=findViewById(R.id.btnguardareditarclinica);
        btnregresarlista=findViewById(R.id.btnregresarallistado);
        fabeliminarclinica=findViewById(R.id.fabeliminarclinica);
        fabregresalistaclinica=findViewById(R.id.fabregresalistaclinica);
    }
}