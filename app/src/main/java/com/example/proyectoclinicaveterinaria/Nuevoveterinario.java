package com.example.proyectoclinicaveterinaria;

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

import com.example.proyectoclinicaveterinaria.db.DbEspecialidades;
import com.example.proyectoclinicaveterinaria.db.DbUsuario;
import com.example.proyectoclinicaveterinaria.db.DbVeterinarios;
import com.example.proyectoclinicaveterinaria.Entidades.Especialidades;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Nuevoveterinario extends AppCompatActivity {
    TextInputEditText edtnuevonombrevet,edtnuevoapellidovet,edtnuevodnivet,edtnuevocelularvet,edtnuevadireccionvet,edtnuevocorreovet,edtnuevafotovet;
    TextInputLayout tilNombre, tilApellido, tilCelular, tilDire, tilCorreo, tilDni;

    Spinner Spinneridespecialidades;
    RadioButton xrbmasculino , xrbfmenino;
    Button btnguardarnuevovet,btnlistaveterinarios, btnbuscarDNi;
    Integer idEspe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevoveterinario);
        enlazarcontroles();

        Intent i =getIntent();
        String nombres=i.getStringExtra("nombre");
        String apellidoM=i.getStringExtra("apellidoM");
        String apellidoP=i.getStringExtra("apellidoP");
        String dni=i.getStringExtra("dni");

        edtnuevonombrevet.setText(nombres);
        edtnuevoapellidovet.setText(apellidoP+" "+apellidoM);
        edtnuevodnivet.setText(dni);


        List<Especialidades> listaCategorias = llenarCategorias();
        ArrayAdapter<Especialidades> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCategorias);
        Spinneridespecialidades.setAdapter(arrayAdapter);


        Spinneridespecialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                idEspe =((Especialidades) parent.getSelectedItem()).getIdespecialidades();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        btnguardarnuevovet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidarDatos()==true)
                {
                    String xgenero = "Masculino";
                    if (xrbfmenino.isChecked()==true){
                        xgenero="Femenino";
                    }

                    DbVeterinarios dbVeterinarios = new DbVeterinarios(Nuevoveterinario.this);
                    long id = dbVeterinarios.insertarveterinarios(edtnuevonombrevet.getText().toString(),
                            edtnuevoapellidovet.getText().toString(),Integer.parseInt(edtnuevodnivet.getText().toString()),
                            Integer.parseInt(edtnuevocelularvet.getText().toString()),edtnuevadireccionvet.getText().toString(),
                            xgenero,edtnuevocorreovet.getText().toString(),idEspe);
                    if (id>0){
                        String mensaje = "VETERINARIO REGISTRADO EXITOSAMENTE";
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


        btnlistaveterinarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Nuevoveterinario.this , ListadoVeterinario.class);




                startActivity(a);
            }
        });

        btnbuscarDNi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Nuevoveterinario.this,ApiReniec.class);
                startActivity(i);
            }
        });
    }

    boolean ValidarDatos() {

        //Pattern patron_codigo = Pattern.compile("^E[0-9]{5}$");
        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_celular = Pattern.compile("^9[0-9]{8}$");
        Pattern patron_dni = Pattern.compile("^[0-9]{8}$");

        String nombre = edtnuevonombrevet.getText().toString();
        String apellido = edtnuevoapellidovet.getText().toString();
        String dni = edtnuevodnivet.getText().toString();
        String correo = edtnuevocorreovet.getText().toString();
        String celular = edtnuevocelularvet.getText().toString();
        String dire = edtnuevadireccionvet.getText().toString();


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
        edtnuevonombrevet=findViewById(R.id.edtnuevonombreveterinario);
        edtnuevoapellidovet=findViewById(R.id.edtnuevoapellidoveterinario);
        edtnuevodnivet=findViewById(R.id.edtnuevodniveterinario);
        edtnuevocelularvet=findViewById(R.id.edtnuevocelularveterinario);
        edtnuevadireccionvet=findViewById(R.id.edtnuevadireccionveterinario);
        edtnuevocorreovet=findViewById(R.id.edtnuevocorreoveterinario);
        xrbmasculino= findViewById(R.id.rbmasculinoVet);
        xrbfmenino = findViewById(R.id.rbfemeninoVet);
        Spinneridespecialidades=findViewById(R.id.spinneridespecialidades);
        btnguardarnuevovet=findViewById(R.id.btnregistrarnuevovet);
        btnlistaveterinarios=findViewById(R.id.btnlistaveterinarios);
        btnbuscarDNi=findViewById(R.id.btnAgregarDataApi);

        tilNombre = findViewById(R.id.tilNomVet);
        tilApellido = findViewById(R.id.tilApeVet);
        tilDni = findViewById(R.id.tilDniVet);
        tilCelular = findViewById(R.id.tilCelVet);
        tilDire= findViewById(R.id.tilDireVet);
        tilCorreo= findViewById(R.id.tilCorreoVet);

    }


    private List<Especialidades> llenarCategorias(){
        List<Especialidades> listaCat = new ArrayList<>();
        DbEspecialidades dbCategorias = new DbEspecialidades(Nuevoveterinario.this);
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