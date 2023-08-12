package com.example.proyectoclinicaveterinaria;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.Entidades.Citas;
import com.example.proyectoclinicaveterinaria.Utilitarios.Variables;
import com.example.proyectoclinicaveterinaria.db.DbCitas;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class EditarCitasActivity extends AppCompatActivity {

    TextView edttipodecitaedt,edtnombremascotacitaedt,edtveterinarioedt;    EditText edtfechacitaedt,edthoracitaedt,edtdescripcionedt;
    Spinner spinnersedescedt;
    Button btneditarcita;
    boolean correcto=false;
    Citas cita;
    int id=0;
    int dia , mes , año ;
    Calendar cal ;
    FloatingActionButton fabeliminar, fablistar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cita);
        Enlazarcontroles();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Variables.xsedes
        );
        spinnersedescedt.setAdapter(adapter);





        cal = Calendar.getInstance();
        dia=cal.get(Calendar.DAY_OF_MONTH);
        mes=cal.get(Calendar.MONTH)+1;
        año=cal.get(Calendar.YEAR);

        edtfechacitaedt.setText(dia+"/"+mes+"/"+año);
        Calendar c=Calendar.getInstance();
        int hora=c.get(Calendar.HOUR_OF_DAY);
        int min=c.get(Calendar.MINUTE);



        edthoracitaedt.setText(hora+":"+min);

        edtfechacitaedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(
                        EditarCitasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        edtfechacitaedt.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, dia, mes, año
                );
                dp.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
                dp.show();
            }
        });

        edthoracitaedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog tmd=new TimePickerDialog(EditarCitasActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edthoracitaedt.setText(hourOfDay+":"+minute);
                    }
                },hora,min,true);
                tmd.show();

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
        DbCitas dbCitas=new DbCitas(EditarCitasActivity.this);
        cita=dbCitas.vercitas(id);

        if(cita!=null){
            edttipodecitaedt.setText(cita.getTIPODECITA());
            edtnombremascotacitaedt.setText(cita.getNOMBREMASCOTA());
            edtveterinarioedt.setText(cita.getVETERINARIO());
            edtfechacitaedt.setText(cita.getFECHACITA());
            edthoracitaedt.setText(cita.getHORACITA());
            edtdescripcionedt.setText(cita.getDESCRIPCION());

            for (int i = 0; i< Variables.xsedes.length; i++)
            {

                if (Variables.xsedes[i].equals(cita.getCLINICA()))
                {
                    spinnersedescedt.setSelection(i);

                    break;
                }
            }
        }

        btneditarcita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correcto=dbCitas.editarCitas(id,edttipodecitaedt.getText().toString(),edtnombremascotacitaedt.getText().toString(),edtveterinarioedt.getText().toString(),spinnersedescedt.getSelectedItem().toString(),edtfechacitaedt.getText().toString(),edthoracitaedt.getText().toString(),edtdescripcionedt.getText().toString());
                if(correcto){
                    String mensaje = "CITA ACTUALIZADA CORRECTAMENTE";
                    Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                            .setTextColor(Color.CYAN)
                            .show();
                }else{
                    String mensaje = "ERROR AL ACTUALIZAR CITA";
                    Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                            .setTextColor(Color.CYAN)
                            .show();
                }
            }
        });

        fablistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarCitasActivity.this , ListadoCitasActivity.class);
                startActivity(a);
            }
        });

        fabeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(EditarCitasActivity.this);
                builder.setMessage("¿Desea eliminar esta cita?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        ProgressDialog pd= ProgressDialog.show(
                                EditarCitasActivity.this,
                                "Espere un momento",
                                "Eliminando cita...",
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
                                        dbCitas.eliminarCitas(id);
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
        Intent intent=new Intent(this,ListadoCitasActivity.class);
        startActivity(intent);
    }

    void Enlazarcontroles(){
        edttipodecitaedt=findViewById(R.id.edttipodecitaedt);
        edtnombremascotacitaedt=findViewById(R.id.edtnombremascotacitaedt);
        edtveterinarioedt=findViewById(R.id.edtveterinarioedt);
        edtfechacitaedt=findViewById(R.id.edtfechacitaedt);
        edthoracitaedt=findViewById(R.id.edthoracitaedt);
        edtdescripcionedt=findViewById(R.id.edtdescripcionedt);
        spinnersedescedt=findViewById(R.id.spinnersedescedt);
        btneditarcita=findViewById(R.id.btneditarcita);
        fablistar=findViewById(R.id.fablistar);
        fabeliminar=findViewById(R.id.fabeliminar);
    }

}