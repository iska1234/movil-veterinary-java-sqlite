package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbHelper;
import com.example.proyectoclinicaveterinaria.db.DbUsuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    Button btnRegistrar;
    TextInputEditText xnombre , xapellido , xdni , xcelular , xdireccion  , xcorreo , xcontrasena ;
    RadioButton xrbmasculino , xrbfmenino;
    ImageView imageView, btnRegresarRegistrarse;
    TextInputLayout tilNombre, tilApellido, tilCelular, tilDire, tilCorreo, tilContra, tilDni;

    final int REQUEST_CODE_GALLERY = 999;

    public static DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        EnlazarControles();

        db = new DbUsuario(this);


        btnRegresarRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidarDatos()==true)
                {
                    String xgenero = "Masculino";
                    if (xrbfmenino.isChecked()==true){
                        xgenero="Femenino";
                    }


                    DbUsuario dbUsuario = new DbUsuario(RegistrarUsuarioActivity.this);
                    long id = dbUsuario.insertarUsuario(xnombre.getText().toString(),xapellido.getText().toString(),
                            Integer.parseInt(xdni.getText().toString()),Integer.parseInt(xcelular.getText().toString()),
                            xdireccion.getText().toString(),xgenero,xcorreo.getText().toString(),
                            xcontrasena.getText().toString());
                    if (id>0){
                        LimpiarControles();
                        Toast.makeText(RegistrarUsuarioActivity.this, "USUARIO REGISTRADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegistrarUsuarioActivity.this, "ERROR EN GUARDAR LOS DATOS", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private byte[] imageViewToByte(ImageView image){

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    /*
    ActivityResultLauncher<Intent> galeriaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== RESULT_OK){
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        imageView.setImageBitmap(imgBitmap);
                    }
                }
            });


     */


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(this, "NO TIENE PERMISOS", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if(requestCode==REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /*
    private byte[] imagenViewToByte(ImageView image){

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

     */

    void EnlazarControles() {
        btnRegistrar = findViewById(R.id.btnAgendarCita);
        btnRegresarRegistrarse = findViewById(R.id.btnRegresarRegistrarse);
        xnombre = findViewById(R.id.edtombre);
        xapellido = findViewById(R.id.edtapellido);
        xdni = findViewById(R.id.edtdni);
        xcelular = findViewById(R.id.edtcelular);
        xdireccion= findViewById(R.id.edtdireccion);
        xrbmasculino= findViewById(R.id.rbmasculino);
        xrbfmenino = findViewById(R.id.rbfemenino);
        xcorreo= findViewById(R.id.edtcorreo);
        xcontrasena= findViewById(R.id.edtcontrasea);

        tilNombre = findViewById(R.id.tilNomUsu);
        tilApellido = findViewById(R.id.tilApeUsu);
        tilDni = findViewById(R.id.tilDniUsu);
        tilCelular = findViewById(R.id.tilCelUsu);
        tilDire= findViewById(R.id.tilDireUsu);
        tilCorreo= findViewById(R.id.tilCorreo);
        tilContra= findViewById(R.id.tilContra);
    }

    void LimpiarControles(){
        xnombre.setText(null);
        xapellido.setText(null);
        xdni.setText(null);
        xcelular.setText(null);
        xdireccion.setText(null);
        xrbfmenino.setChecked(false);
        xrbmasculino.setChecked(false);
        xcorreo.setText(null);
        xcontrasena.setText(null);
    }


    boolean ValidarDatos()
    {

        //Pattern patron_codigo = Pattern.compile("^E[0-9]{5}$");
        Pattern patron_letras = Pattern.compile("^[a-zA-Z ]+$");
        Pattern patron_celular = Pattern.compile("^9[0-9]{8}$");
        Pattern patron_dni = Pattern.compile("^[0-9]{8}$");

        String nombre = xnombre.getText().toString();
        String apellido = xapellido.getText().toString();
        String dni = xdni.getText().toString();
        String contra = xcontrasena.getText().toString();
        String correo = xcorreo.getText().toString();
        String celular = xcelular.getText().toString();
        String dire = xdireccion.getText().toString();


        // nombre
        tilNombre.setError("");
        tilNombre.setErrorEnabled(false);
        if (nombre.equals(""))
        {
            tilNombre.setError("El Codigo es Obligatorio");
            return false;
        }
        if (patron_letras.matcher(nombre).matches()==false)
        {
            tilNombre.setError("Error: Solo se permiten letras");
            return false;
        }

        // apellido
        tilApellido.setError("");
        tilApellido.setErrorEnabled(false);
        if (apellido.equals(""))
        {
            tilApellido.setError("El Codigo es Obligatorio");
            return false;
        }
        if (patron_letras.matcher(apellido).matches()==false)
        {
            tilApellido.setError("Error: Solo se permiten letras");
            return false;
        }

        // Celular
        tilCelular.setError("");
        tilCelular.setErrorEnabled(false);
        if (celular.equals(""))
        {
            tilCelular.setError("El campo celular es Obligatorio");
            return false;
        }
        if (patron_celular.matcher(celular).matches()==false)
        {
            tilCelular.setError("Error: El celular ingresado no es valido");
            return false;
        }

        // Dni
        tilDni.setError("");
        tilDni.setErrorEnabled(false);
        if (dni.equals(""))
        {
            tilDni.setError("El campo dni es Obligatorio");
            return false;
        }
        if (patron_dni.matcher(dni).matches()==false)
        {
            tilDni.setError("Error: El dni ingresado no es valido");
            return false;
        }

        // Direccion
        tilDire.setError("");
        tilDire.setErrorEnabled(false);
        if (dire.equals(""))
        {
            tilDire.setError("El campo dirección es Obligatorio");
            return false;
        }

        // contra
        tilContra.setError("");
        tilContra.setErrorEnabled(false);
        if (contra.equals(""))
        {
            tilContra.setError("Campo vacio");
            return false;
        }

        // email
        tilCorreo.setError("");
        tilCorreo.setErrorEnabled(false);
        if (correo.equals(""))
        {
            tilCorreo.setError("El Correo es Obligatorio");
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(correo).matches()==false)
        {
            tilCorreo.setError("Error, No es un Correo valido");
            return false;
        }

        // si no hubieron errores
        return true;
    }
}