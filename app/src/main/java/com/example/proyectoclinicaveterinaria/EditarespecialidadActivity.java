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

import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.example.proyectoclinicaveterinaria.Entidades.Especialidades;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class EditarespecialidadActivity extends AppCompatActivity {
    TextInputEditText edteditarnombreespecialidad,edteditardescripcionespecialidad;
    TextInputLayout tilNomEsp, tilDescEsp;
    Button btneditarespecialidad,btnregresarlistaespecialidad;
    boolean correcto=false;
    Especialidades especialidades;
    int id=0;
    FloatingActionButton fabeliminarespecialidad, fabregresalistaEspecialidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarespecialidad);

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
        DbEspecialidades dbEspecialidades =new DbEspecialidades(EditarespecialidadActivity.this);
        especialidades= dbEspecialidades.verespecialidades(id);

        if(especialidades!=null){
            edteditarnombreespecialidad.setText(especialidades.getNombredeespecialidad());
            edteditardescripcionespecialidad.setText(especialidades.getDescripcion());
        }

        btneditarespecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ValidarDatos()==true)
                {

                    correcto= dbEspecialidades.editarespecialidad(id,edteditarnombreespecialidad.getText().toString(),edteditardescripcionespecialidad.getText().toString());
                    if(correcto){
                        String mensaje = "ESPECIALIDAD ACTUALIZADA CORRECTAMENTE";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }else{
                        String mensaje = "ERROR AL ACTUALIZAR ESPECIALIDAD";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });

        btnregresarlistaespecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarespecialidadActivity.this , ListadoEspecialidad.class);
                startActivity(a);
            }
        });

        fabregresalistaEspecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarespecialidadActivity.this , ListadoEspecialidad.class);
                startActivity(a);
            }
        });

        fabeliminarespecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(EditarespecialidadActivity.this);
                builder.setMessage("¿Desea eliminar esta especialidad?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        ProgressDialog pd= ProgressDialog.show(
                                EditarespecialidadActivity.this,
                                "Espere un momento",
                                "Eliminando especialidad...",
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
                                        dbEspecialidades.eliminarespecialidad(id);
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
        Intent intent=new Intent(this,ListadoEspecialidad.class);
        startActivity(intent);
    }

    boolean ValidarDatos()
    {
        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");

        String nombre = edteditarnombreespecialidad.getText().toString();
        String descripcion = edteditardescripcionespecialidad.getText().toString();

        // Nombre
        tilNomEsp.setError("");
        tilNomEsp.setErrorEnabled(false);
        if (nombre.equals(""))
        {
            tilNomEsp.setError("El nombre es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(nombre).matches()==false)
        {
            tilNomEsp.setError("Error: Solo se permiten letras");
            return false;
        }

        // Descripcion
        tilDescEsp.setError("");
        tilDescEsp.setErrorEnabled(false);
        if (descripcion.equals(""))
        {
            tilDescEsp.setError("La Descripcion es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(descripcion).matches()==false)
        {
            tilDescEsp.setError("Error: Solo se permiten letras");
            return false;
        }

        // si no hubieron errores
        return true;
    }

    void enlazarcontroles(){
        edteditarnombreespecialidad=findViewById(R.id.edteditarnombreespecialidad);
        edteditardescripcionespecialidad=findViewById(R.id.edteditardescripcionespecialidad);

        tilNomEsp=findViewById(R.id.tilNomEspUpdate);
        tilDescEsp=findViewById(R.id.tilDescEspUpdate);

        btneditarespecialidad=findViewById(R.id.btneditarespecialidad);
        btnregresarlistaespecialidad=findViewById(R.id.btnregresarespecialidades);
        fabeliminarespecialidad=findViewById(R.id.fabeliminarespecialidad);
        fabregresalistaEspecialidad=findViewById(R.id.fabregresalistaEspecialidad);
    }
}