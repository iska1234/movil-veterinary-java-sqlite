package com.example.proyectoclinicaveterinaria;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.Entidades.VeterinarioyEspecialidad;
import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.example.proyectoclinicaveterinaria.db.DbVeterinarios;
import com.example.proyectoclinicaveterinaria.Entidades.Especialidades;
import com.example.proyectoclinicaveterinaria.Entidades.Veterinarios;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EditarveterinarioActivity extends AppCompatActivity {
    TextInputEditText edteditarnombreveterinario,edteditarapellidoveterinario,edteditardniveterinario,edteditarcelularveterinario,edteditardireccionveterinario,edteditargeneroveterinario,edteditarcorreoveterinario;
    TextInputLayout tilNombre, tilApellido, tilCelular, tilDire, tilCorreo, tilDni;
    Spinner spinneriditaridespecialidades;
    Button btnguardareditarvet,btnregresarlistavet;
    RadioButton xrbmasculino , xrbfmenino;

    boolean correcto=false;
    VeterinarioyEspecialidad veterinarios;
    int id=0;

    FloatingActionButton fabeliminarediveterinario, fabregresalistaVeterinarios;
    Integer idEspe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarveterinario);
        enlazarcontroles();

        xrbmasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                xrbfmenino.setChecked(false);
            }
        });

        xrbfmenino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                xrbmasculino.setChecked(false);
            }
        });


        List<Especialidades> listaCategorias = llenarCategorias();
        ArrayAdapter<Especialidades> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCategorias);
        spinneriditaridespecialidades.setAdapter(arrayAdapter);


        spinneriditaridespecialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                idEspe =((Especialidades) parent.getSelectedItem()).getIdespecialidades();
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
        DbVeterinarios dbVeterinarios =new DbVeterinarios(EditarveterinarioActivity.this);
        veterinarios = dbVeterinarios.verveterinarios(id);

        if(veterinarios!=null){
            edteditarnombreveterinario.setText(veterinarios.getNombre());
            edteditarapellidoveterinario.setText(String.valueOf(veterinarios.getApellidos()));
            edteditardniveterinario.setText(String.valueOf(veterinarios.getDni()));
            edteditarcelularveterinario.setText(String.valueOf(veterinarios.getCelular()));
            edteditardireccionveterinario.setText(veterinarios.getDireccion());
            String sexo="";
            sexo= veterinarios.getGenero();
            if (sexo.equals("Masculino")) {
                xrbmasculino.setChecked(true);
                xrbfmenino.setChecked(false);
            }
            if (sexo.equals("Femenino")){
                xrbfmenino.setChecked(true);
                xrbmasculino.setChecked(false);
            }
            edteditarcorreoveterinario.setText(veterinarios.getCorreo());

            for (int i=0; i< listaCategorias.size(); i++)
            {
                if (listaCategorias.get(i).equals(veterinarios.getNombreespecialidad()))
                {
                    spinneriditaridespecialidades.setSelection(i);
                    break;
                }
            }

        }

        btnguardareditarvet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ValidarDatos()==true)
                {
                    String xgenero = "Masculino";
                    if (xrbfmenino.isChecked()==true){
                        xgenero="Femenino";
                    }

                    correcto= dbVeterinarios.editarVeterinarios(id,edteditarnombreveterinario.getText().toString(),
                            edteditarapellidoveterinario.getText().toString(),
                            Integer.parseInt(edteditardniveterinario.getText().toString()),
                            Integer.parseInt(edteditarcelularveterinario.getText().toString()),
                            edteditardireccionveterinario.getText().toString(),
                            xgenero,
                            edteditarcorreoveterinario.getText().toString(),idEspe);


                    if(correcto){
                        String mensaje = "VETERINARIO ACTUALIZADO CORRECTAMENTE";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }else{
                        String mensaje = "ERROR AL ACTUALIZAR VETERINARIO";
                        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(Color.CYAN)
                                .show();
                    }
                }
            }
        });

        btnregresarlistavet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarveterinarioActivity.this , ListadoVeterinario.class);
                startActivity(a);
            }
        });

        fabregresalistaVeterinarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(EditarveterinarioActivity.this , ListadoVeterinario.class);
                startActivity(a);
            }
        });

        fabeliminarediveterinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(EditarveterinarioActivity.this);
                builder.setMessage("¿Desea eliminar este veterinario?.-.").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        ProgressDialog pd= ProgressDialog.show(
                                EditarveterinarioActivity.this,
                                "Espere un momento",
                                "Eliminando veterinario...",
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
                                        dbVeterinarios.eliminarVeterinario(id);
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

    boolean ValidarDatos() {

        //Pattern patron_codigo = Pattern.compile("^E[0-9]{5}$");
        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_celular = Pattern.compile("^9[0-9]{8}$");
        Pattern patron_dni = Pattern.compile("^[0-9]{8}$");

        String nombre = edteditarnombreveterinario.getText().toString();
        String apellido = edteditarapellidoveterinario.getText().toString();
        String dni = edteditardniveterinario.getText().toString();
        String correo = edteditarcorreoveterinario.getText().toString();
        String celular = edteditarcelularveterinario.getText().toString();
        String dire = edteditardireccionveterinario.getText().toString();


        // nombre
        tilNombre.setError("");
        tilNombre.setErrorEnabled(false);
        if (nombre.equals("")) {
            tilNombre.setError("El Codigo es Obligatorio");
            return false;
        }
        if (patron_letras.matcher(nombre).matches() == false) {
            tilNombre.setError("Error: Solo se permiten letras");
            return false;
        }

        // apellido
        tilApellido.setError("");
        tilApellido.setErrorEnabled(false);
        if (apellido.equals("")) {
            tilApellido.setError("El Codigo es Obligatorio");
            return false;
        }
        if (patron_letras.matcher(apellido).matches() == false) {
            tilApellido.setError("Error: Solo se permiten letras");
            return false;
        }

        // Celular
        tilCelular.setError("");
        tilCelular.setErrorEnabled(false);
        if (celular.equals("")) {
            tilCelular.setError("El campo celular es Obligatorio");
            return false;
        }
        if (patron_celular.matcher(celular).matches() == false) {
            tilCelular.setError("Error: El celular ingresado no es valido");
            return false;
        }

        // Dni
        tilDni.setError("");
        tilDni.setErrorEnabled(false);
        if (dni.equals("")) {
            tilDni.setError("El campo dni es Obligatorio");
            return false;
        }
        if (patron_dni.matcher(dni).matches() == false) {
            tilDni.setError("Error: El dni ingresado no es valido");
            return false;
        }

        // Direccion
        tilDire.setError("");
        tilDire.setErrorEnabled(false);
        if (dire.equals("")) {
            tilDire.setError("El campo dirección es Obligatorio");
            return false;
        }

        // email
        tilCorreo.setError("");
        tilCorreo.setErrorEnabled(false);
        if (correo.equals("")) {
            tilCorreo.setError("El Correo es Obligatorio");
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(correo).matches() == false) {
            tilCorreo.setError("Error, No es un Correo valido");
            return false;
        }

        // si no hubieron errores
        return true;
    }


    void enlazarcontroles(){
        edteditarnombreveterinario=findViewById(R.id.edteditarnombreveterinario);
        edteditarapellidoveterinario=findViewById(R.id.edteditarapellidoveterinario);
        edteditardniveterinario=findViewById(R.id.edteditardniveterinario);
        edteditarcelularveterinario=findViewById(R.id.edteditarcelularveterinario);
        edteditardireccionveterinario=findViewById(R.id.edteditardireccionveterinario);
        edteditarcorreoveterinario=findViewById(R.id.edteditarcorreoveterinario);

        tilNombre = findViewById(R.id.tilNomVetUpdate);
        tilApellido = findViewById(R.id.tilApeVetUpdate);
        tilDni = findViewById(R.id.tilDniVetUpdate);
        tilCelular = findViewById(R.id.tilCelVetUpdate);
        tilDire= findViewById(R.id.tilDireVetUpdate);
        tilCorreo= findViewById(R.id.tilCorreoVetUpdate);

        xrbfmenino=findViewById(R.id.rbfemeninoVetUpdate);
        xrbmasculino=findViewById(R.id.rbmasculinoVetUpdate);

        spinneriditaridespecialidades=findViewById(R.id.spinnereditaridespecialidades);
        btnguardareditarvet=findViewById(R.id.btnguardareditarveterinario);
        btnregresarlistavet=findViewById(R.id.btnregresarlistaveterinarios);
        fabeliminarediveterinario=findViewById(R.id.fabeliminareditarveterinario);
        fabregresalistaVeterinarios=findViewById(R.id.fabregresalistaVeterinarios);
    }


    private List<Especialidades> llenarCategorias(){
        List<Especialidades> listaCat = new ArrayList<>();
        DbEspecialidades dbCategorias = new DbEspecialidades(EditarveterinarioActivity.this);
        Cursor cursor = dbCategorias.mostarEspecialidadenSpinner();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Especialidades cat = new Especialidades();
                    cat.setIdespecialidades(cursor.getInt(cursor.getColumnIndex("idespecialidades")));
                    cat.setNombredeespecialidad(cursor.getString(cursor.getColumnIndex("NOMBREDEESPECIALIDAD")));
                    listaCat.add(cat);
                } while (cursor.moveToNext());
            }
        }
        dbCategorias.close();

        return listaCat;
    }

}