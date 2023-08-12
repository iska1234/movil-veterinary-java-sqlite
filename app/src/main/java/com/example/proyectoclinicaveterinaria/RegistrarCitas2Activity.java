package com.example.proyectoclinicaveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.proyectoclinicaveterinaria.Adaptador.AdaptadorVeterinario;
import com.example.proyectoclinicaveterinaria.DAO.VeterinarioDAO;
import com.example.proyectoclinicaveterinaria.Entidades.Veterinario;
import com.example.proyectoclinicaveterinaria.Utilitarios.Variables;
import com.example.proyectoclinicaveterinaria.db.DbCitas;
import com.google.android.material.snackbar.Snackbar;

import java.lang.annotation.Repeatable;
import java.util.Calendar;
import java.util.List;

public class RegistrarCitas2Activity extends AppCompatActivity {

    TextView tvDatosVet;
    EditText edtFecha,edtHora,edtDescripcion;

    VeterinarioDAO dao;
    Button btnRegresar,btnRegistrar,btnListar;

    Spinner spinnerSede;
    Calendar cal;
    int dia, mes, anio;
    int hora,minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_citas2);
        EnlazarControles();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                RegistrarCitas2Activity.this,
                android.R.layout.simple_dropdown_item_1line,
                Variables.xsedes
        );
        spinnerSede.setAdapter(adapter);

        //
        Intent i =getIntent();
        String especialidad=i.getStringExtra("espc");
        String nombreVeterinario=i.getStringExtra("nombreV");
        String telef=i.getStringExtra("telef");
        String nommas=i.getStringExtra("nommas");
        //
        Toast.makeText(this, "Especialidad: "+especialidad+nombreVeterinario+telef, Toast.LENGTH_SHORT).show();

        tvDatosVet.setText("Veterinario: "+nombreVeterinario+"\n"+
                            "Telefono: "+telef);

        //
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cal = Calendar.getInstance();
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONDAY);
        anio = cal.get(Calendar.YEAR);
        //
        hora=cal.get(Calendar.HOUR);
        minutos=cal.get(Calendar.MINUTE);
        //
        edtHora.setText(hora+":"+minutos);
        edtFecha.setText(dia+"/"+mes+"/"+anio);
        edtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(RegistrarCitas2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String date=dayOfMonth+"/"+month+"/"+year;

                        edtFecha.setText(date);
                    }
                },anio,mes,dia);

                dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                dialog.show();
            }
        });

        edtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tmd=new TimePickerDialog(RegistrarCitas2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtHora.setText(hourOfDay+":"+minute);
                    }
                },hora,minutos,true);
                tmd.show();
            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbCitas dbcitas = new DbCitas(RegistrarCitas2Activity.this);
                long id = dbcitas.insertarCitas(
                        especialidad.toString(),nommas,nombreVeterinario.toString()
                        ,spinnerSede.getSelectedItem().toString(),edtFecha.getText().toString(), edtHora.getText().toString(),
                        edtDescripcion.getText().toString());
                if (id>0){
                    String mensaje = "CITA REGISTRADA EXITOSAMENTE";
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
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(RegistrarCitas2Activity.this , ListadoCitasActivity.class);


                startActivity(a);
            }
        });

    }

    void EnlazarControles(){

        btnRegresar=findViewById(R.id.btnRegresarCita);
        tvDatosVet=findViewById(R.id.tvDatosVeterinario);
        spinnerSede=findViewById(R.id.spinnersedesc);

        edtFecha=findViewById(R.id.edtFecha);
        edtHora=findViewById(R.id.edtHora);
        edtDescripcion=findViewById(R.id.edtdescripcion);

        btnRegistrar=findViewById(R.id.btnAgendarCita);
        btnListar=findViewById(R.id.btnListar);


    }/*
    void TraerVeterinarioEsp(String esp) {
        dao = new VeterinarioDAO();

        List<String> listado = dao.VeterinarioEsp(esp);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(
            getApplicationContext(),
            android.R.layout.simple_list_item_1,
                listado
        );
        lvVeterinario.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvVeterinario.setAdapter(adapter);

        dao = null;

        Toast.makeText(this, "Cant"+listado.size(), Toast.LENGTH_SHORT).show();
    }

    void TraerVeterinario(){

        dao = new VeterinarioDAO();
        AdaptadorVeterinario adapter = new AdaptadorVeterinario(
            RegistrarCitas2Activity.this,
                R.layout.item_veterinario,
                dao.Listar_Veterinario()
        );
        lvVeterinario.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvVeterinario.setAdapter(adapter);
    }*/





}