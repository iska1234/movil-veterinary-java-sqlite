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

import com.example.proyectoclinicaveterinaria.db.DbMedicacion;
import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class EditarmedicacionActivity extends AppCompatActivity {
    TextInputEditText edteditarnombremedicacion,edteditarpreciomedicacion;
    TextInputLayout tilNomMedi, tilPrecioMedi;

    Button btneditarmedicacion,btnregresarlistamedicacion;
    boolean correcto=false;
    Medicacion medicacion;
    int id=0;
    FloatingActionButton fabeliminarmedicacion, fabregresalistaMedicamentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarmedicacion);
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
        DbMedicacion dbMedicacion =new DbMedicacion(EditarmedicacionActivity.this);
        medicacion= dbMedicacion.vermedicacion(id);

        if(medicacion!=null){
            edteditarnombremedicacion.setText(medicacion.getNombre());
            edteditarpreciomedicacion.setText(String.valueOf(medicacion.getPrecio()));

        }

        btneditarmedicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ValidarDatos()==true)
                {
                    correcto= dbMedicacion.editarmedicacion(id,edteditarnombremedicacion.getText().toString(),Integer.parseInt(edteditarpreciomedicacion.getText().toString()));
                    if(correcto){
                        String mensaje = "MEDICAMENTO ACTUALIZADO CORRECTAMENTE";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }else{
                        String mensaje = "ERROR AL ACTUALIZAR MEDICAMENTO";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });

        btnregresarlistamedicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarmedicacionActivity.this , ListadoMedicacion.class);
                startActivity(a);
            }
        });

        fabregresalistaMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarmedicacionActivity.this , ListadoMedicacion.class);
                startActivity(a);
            }
        });

        fabeliminarmedicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(EditarmedicacionActivity.this);
                builder.setMessage("¿Desea eliminar esta medicacion?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        ProgressDialog pd= ProgressDialog.show(
                                EditarmedicacionActivity.this,
                                "Espere un momento",
                                "Eliminando medicacion...",
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
                                        dbMedicacion.eliminarmedicacion(id);
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
        Intent intent=new Intent(this,ListadoMedicacion.class);
        startActivity(intent);
    }


    boolean ValidarDatos()
    {

        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_numeros = Pattern.compile("^[0-9]+$");

        String nombre = edteditarnombremedicacion.getText().toString();
        String precio = edteditarpreciomedicacion.getText().toString();



        // Nombre
        tilNomMedi.setError("");
        tilNomMedi.setErrorEnabled(false);
        if (nombre.equals(""))
        {
            tilNomMedi.setError("El nombre es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(nombre).matches()==false)
        {
            tilNomMedi.setError("Error: Solo se permiten letras");
            return false;
        }

        // Precio
        tilPrecioMedi.setError("");
        tilPrecioMedi.setErrorEnabled(false);
        if (precio.equals(""))
        {
            tilPrecioMedi.setError("El campo precio es Obligatorio");
            return false;
        }
        if (patron_numeros.matcher(precio).matches()==false)
        {
            tilPrecioMedi.setError("Error: Solo se permiten numeros");
            return false;
        }

        // si no hubieron errores
        return true;
    }


    void enlazarcontroles(){
        edteditarnombremedicacion=findViewById(R.id.edteditarnombremedicacion);
        edteditarpreciomedicacion=findViewById(R.id.edteditarpreciomedicacion);
        btneditarmedicacion=findViewById(R.id.btneditarmedicacion);
        btnregresarlistamedicacion=findViewById(R.id.btnregresarmedicacion);
        tilNomMedi=findViewById(R.id.tilNomMediUpdate);
        tilPrecioMedi=findViewById(R.id.tilPrecioMediUpdate);
        fabeliminarmedicacion=findViewById(R.id.fabeliminarmedicacion);
        fabregresalistaMedicamentos=findViewById(R.id.fabregresalistaMedicamentos);

    }
}