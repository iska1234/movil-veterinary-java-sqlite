package com.example.proyectoclinicaveterinaria;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.Entidades.HistorialMedicacionyMascotas;
import com.example.proyectoclinicaveterinaria.db.DbHistorialmedico;
import com.example.proyectoclinicaveterinaria.db.DbMascotas;
import com.example.proyectoclinicaveterinaria.db.DbMedicacion;
import com.example.proyectoclinicaveterinaria.Entidades.Historialmedico;
import com.example.proyectoclinicaveterinaria.Entidades.Mascota;
import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class Editarhistorialmedico extends AppCompatActivity {
    TextInputEditText edteditarenfermedadpadecida,edteditartratamiento,edteditarfechahistorial;
    TextInputLayout tilEnfermedad, tilTratamiento, tilFecha;
    Spinner spinnereditaridmedicacion,spinnereditaridmascotas;
    Button btnguardareditarhistorial,btnregresarhistorial;

    boolean correcto = false;
    HistorialMedicacionyMascotas historialmedico;
    int id = 0;
    FloatingActionButton fabeliminarhistorial,fabregresalistahistorial;

    int dia , mes , año ;
    Calendar cal ;

    Integer idMedica , idMasco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarhistorialmedico);
        enlazarcontroles();

        List<Medicacion> listaCategorias = llenarMedicamentos();
        ArrayAdapter<Medicacion> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCategorias);
        spinnereditaridmedicacion.setAdapter(arrayAdapter);

        List<Mascota> listaMascotas = llenarMascota();
        ArrayAdapter<Mascota> arrayAdapters = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaMascotas);
        spinnereditaridmascotas.setAdapter(arrayAdapters);


        spinnereditaridmedicacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idMedica =((Medicacion) parent.getSelectedItem()).getIdmedicacion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnereditaridmascotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMasco = ((Mascota) parent.getSelectedItem()).getCodmas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
        DbHistorialmedico dbHistorialmedico =new DbHistorialmedico(Editarhistorialmedico.this);
        historialmedico= dbHistorialmedico.verhistorialmedico(id);

        if(historialmedico!=null){
            edteditarenfermedadpadecida.setText(historialmedico.getEnfermedadpadecida());
            edteditartratamiento.setText(historialmedico.getTratamiento());
            edteditarfechahistorial.setText(historialmedico.getFecha());
        }

        cal = Calendar.getInstance();
        dia=cal.get(Calendar.DAY_OF_MONTH);
        mes=cal.get(Calendar.MONTH)+1;
        año=cal.get(Calendar.YEAR);

        Calendar c=Calendar.getInstance();

        edteditarfechahistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(
                        Editarhistorialmedico.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        edteditarfechahistorial.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, dia, mes, año
                );
                dp.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
                dp.show();
            }
        });

        btnguardareditarhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ValidarDatos()==true)
                {
                    correcto= dbHistorialmedico.editarhistorialmedico(id,edteditarenfermedadpadecida.getText().toString(),edteditartratamiento.getText().toString(),edteditarfechahistorial.getText().toString(),idMedica,idMasco);
                    if(correcto){
                        String mensaje = "HISTORIAL MEDICO ACTUALIZADO CORRECTAMENTE";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }else{
                        String mensaje = "ERROR AL ACTUALIZAR HISTORIAL MEDICO";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });

        btnregresarhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Editarhistorialmedico.this , ListadoHistorialMedico.class);
                startActivity(a);
            }
        });

        fabregresalistahistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Editarhistorialmedico.this , ListadoHistorialMedico.class);
                startActivity(a);
            }
        });

        fabeliminarhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Editarhistorialmedico.this);
                builder.setMessage("Â¿Desea eliminar este historial 0_o?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                ProgressDialog pd= ProgressDialog.show(
                                        Editarhistorialmedico.this,
                                        "Espere un momento",
                                        "Eliminando historial medico...",
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
                                                dbHistorialmedico.eliminarhistorialmedico(id);
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
        Intent intent=new Intent(this,ListadoHistorialMedico.class);
        startActivity(intent);
    }

    boolean ValidarDatos()
    {

        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");

        String enfermedad = edteditarenfermedadpadecida.getText().toString();
        String tratamiento = edteditartratamiento.getText().toString();
        String fecha = edteditarfechahistorial.getText().toString();

        // Nombre
        tilEnfermedad.setError("");
        tilEnfermedad.setErrorEnabled(false);
        if (enfermedad.equals(""))
        {
            tilEnfermedad.setError("La enfermedad tratada es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(enfermedad).matches()==false)
        {
            tilEnfermedad.setError("Error: Solo se permiten letras");
            return false;
        }

        // Tratamiento
        tilTratamiento.setError("");
        tilTratamiento.setErrorEnabled(false);
        if (tratamiento.equals(""))
        {
            tilTratamiento.setError("El tratamiento es un campo Obligatorio");
            return false;
        }
        if (patron_letras.matcher(tratamiento).matches()==false)
        {
            tilTratamiento.setError("Error: Solo se permiten letras");
            return false;
        }

        // Fecha
        tilFecha.setError("");
        tilFecha.setErrorEnabled(false);
        if (fecha.equals(""))
        {
            tilFecha.setError("La fecha es un campo Obligatorio");
            return false;
        }

        // si no hubieron errores
        return true;
    }


    void enlazarcontroles(){
        edteditarenfermedadpadecida=findViewById(R.id.edteditarenfermedadpadecida);
        edteditartratamiento=findViewById(R.id.edteditartratamiento);
        edteditarfechahistorial=findViewById(R.id.edteditarfechahistorial);

        tilTratamiento=findViewById(R.id.tilTratamientoUpdate);
        tilEnfermedad=findViewById(R.id.tilEnfermedadUpdate);
        tilFecha=findViewById(R.id.tilFechaUpdate);

        spinnereditaridmedicacion=findViewById(R.id.spinnereditaridmedicacion);
        spinnereditaridmascotas=findViewById(R.id.spinnereditaridmascotas);
        btnguardareditarhistorial=findViewById(R.id.btnguardareditarhistorial);
        btnregresarhistorial=findViewById(R.id.btnregresarhistorial);
        fabeliminarhistorial=findViewById(R.id.fabeliminarhistorialmedi);
        fabregresalistahistorial=findViewById(R.id.fabregresalistahistorial);

    }

    private List<Medicacion> llenarMedicamentos(){
        List<Medicacion> listaCat = new ArrayList<>();
        DbMedicacion dbCategorias = new DbMedicacion(Editarhistorialmedico.this);
        Cursor cursor = dbCategorias.mostarMedicamentoenSpinner();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Medicacion cat = new Medicacion();
                    cat.setIdmedicacion(cursor.getInt(cursor.getColumnIndex("idmedicacion")));
                    cat.setNombre(cursor.getString(cursor.getColumnIndex("NOMBRE")));
                    listaCat.add(cat);
                } while (cursor.moveToNext());
            }
        }
        dbCategorias.close();

        return listaCat;
    }

    private List<Mascota> llenarMascota(){
        List<Mascota> listaMas= new ArrayList<>();
        DbMascotas dbMascota = new DbMascotas(Editarhistorialmedico.this);
        Cursor cursor = dbMascota.mostarMascptaenSpinner();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Mascota mas = new Mascota();
                    mas.setCodmas(cursor.getInt(cursor.getColumnIndex("idmascotas")));
                    mas.setNommas(cursor.getString(cursor.getColumnIndex("NOMBRE")));
                    listaMas.add(mas);
                } while (cursor.moveToNext());
            }
        }
        dbMascota.close();

        return listaMas;
    }


}