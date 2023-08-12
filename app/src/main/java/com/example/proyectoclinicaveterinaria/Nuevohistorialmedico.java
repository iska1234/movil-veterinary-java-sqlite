package com.example.proyectoclinicaveterinaria;

import android.app.DatePickerDialog;
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

import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.example.proyectoclinicaveterinaria.db.DbHistorialmedico;
import com.example.proyectoclinicaveterinaria.db.DbMascotas;
import com.example.proyectoclinicaveterinaria.db.DbMedicacion;
import com.example.proyectoclinicaveterinaria.Entidades.Mascota;
import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class Nuevohistorialmedico extends AppCompatActivity {

    TextInputEditText edtenfermedades , edttratamiento , edtfecha ;
    TextInputLayout tilEnfermedad, tilTratamiento, tilFecha;
    Spinner xspinnermedicacion , xspinnermascota ;
    Button xbtnregistrarhistorial , xbtnlistarhistorial ;
    Integer idMedica , idMasco;

    Calendar cal;
    int dia, mes, anio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_historialmedico);
        EnlazarControles();

        List<Medicacion> listaCategorias = llenarMedicamentos();
        ArrayAdapter<Medicacion> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCategorias);
        xspinnermedicacion.setAdapter(arrayAdapter);

        List<Mascota> listaMascotas = llenarMascota();
        ArrayAdapter<Mascota> arrayAdapters = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaMascotas);
        xspinnermascota.setAdapter(arrayAdapters);


        xspinnermedicacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idMedica =((Medicacion) parent.getSelectedItem()).getIdmedicacion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        xspinnermascota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idMasco = ((Mascota) parent.getSelectedItem()).getCodmas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cal = Calendar.getInstance();
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONDAY);
        anio = cal.get(Calendar.YEAR);

        edtfecha.setText(dia+"/"+mes+"/"+anio);

        edtfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(Nuevohistorialmedico.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String date=dayOfMonth+"/"+month+"/"+year;

                        edtfecha.setText(date);
                    }
                },anio,mes,dia);

                dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                dialog.show();
            }
        });

        xbtnregistrarhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidarDatos()==true)
                {

                    DbHistorialmedico dbHistorialmedico = new DbHistorialmedico(Nuevohistorialmedico.this);
                    long id = dbHistorialmedico.insertarhistorialmedico(edtenfermedades.getText().toString(),edttratamiento.getText().toString(),edtfecha.getText().toString(),idMedica,idMasco);
                    if (id>0){
                        String mensaje = "HISTORIAL REGISTRADO EXITOSAMENTE";
                        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();

                    }
                    else{
                        String mensaje = "ERROR EN GUARDAR LOS DATOS";
                        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });


        xbtnlistarhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Nuevohistorialmedico.this , ListadoHistorialMedico.class);
                startActivity(a);
            }
        });


    }

    boolean ValidarDatos()
    {

        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");

        String enfermedad = edtenfermedades.getText().toString();
        String tratamiento = edttratamiento.getText().toString();
        String fecha = edtfecha.getText().toString();

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


    void EnlazarControles (){
        edtenfermedades= findViewById(R.id.edtenfermedadhistorrial);
        edttratamiento = findViewById(R.id.edttratamientohistorial);
        edtfecha = findViewById(R.id.edtfechahistorial);

        tilEnfermedad= findViewById(R.id.tilEnfermedad);
        tilTratamiento = findViewById(R.id.tilTratamiento);
        tilFecha = findViewById(R.id.tilFecha);

        xspinnermascota= findViewById(R.id.spinnermascotas);
        xspinnermedicacion = findViewById(R.id.spinnermedicacion);
        xbtnregistrarhistorial = findViewById(R.id.btnregistrarhistorial);
        xbtnlistarhistorial = findViewById(R.id.btnlistado);

    }

    private List<Medicacion> llenarMedicamentos(){
        List<Medicacion> listaCat = new ArrayList<>();
        DbMedicacion dbCategorias = new DbMedicacion(Nuevohistorialmedico.this);
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
        DbMascotas dbMascota = new DbMascotas(Nuevohistorialmedico.this);
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