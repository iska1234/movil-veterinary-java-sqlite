package com.example.proyectoclinicaveterinaria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoclinicaveterinaria.db.DbHelper;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MenuPrincipalActivity extends AppCompatActivity {

    ImageView ivAgregarMascota,ivMascota;
    Button btnCitas, btnMascotas, btnUbicacion;
    FloatingActionMenu fabMenu;
    FloatingActionButton fabSalir;
    TextView tvNombre;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        tvNombre = findViewById(R.id.tvNombreUsuario);
        ivAgregarMascota = findViewById(R.id.ivAgregarMascota);
        btnCitas = findViewById(R.id.btnCitas);
        btnUbicacion = findViewById(R.id.btnUbicacion);
        btnMascotas = findViewById(R.id.btnMascotas);
        ivMascota = findViewById(R.id.ivMascota);
        fabMenu = findViewById(R.id.fabMenu);
        fabSalir = findViewById(R.id.fabSalir);


        fabMenu.setClosedOnTouchOutside(true);

        //Mostar el correo electronico al ingreso del usuario:
        Intent i =getIntent();
        String email=i.getStringExtra("EMAIL");
        tvNombre.setText("Bienvenido " + email);


        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        MenuPrincipalActivity.this,UbicacionMapsActivity.class
                );
                startActivity(i);
            }
        });

        ivAgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuPrincipalActivity.this,RegistrarMascotaActivity.class
                );
                startActivity(intento);
            }
        });

        btnCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        MenuPrincipalActivity.this,ListadoCitasActivity.class
                );
                startActivity(i);
            }
        });

        btnMascotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        MenuPrincipalActivity.this,ListadoMascotasActivity.class
                );
                startActivity(i);
            }
        });

        ivMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuPrincipalActivity.this,DetalleMascotaActivity.class
                );
                startActivity(intento);
            }
        });

        fabSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(
                        MenuPrincipalActivity.this,LoginActivity.class
                );
                startActivity(intento);
            }
        });

    }


    public void Mensaje(View view){

        String numero = "947399909";
        String mensaje = "Deseo realizar una cita";

        Uri uri = Uri.parse("smsto:" + numero);

        Intent sms_mensaje = new Intent(
          Intent.ACTION_SENDTO,
          uri
        );

        sms_mensaje.putExtra("sms_body", mensaje);

        startActivity(sms_mensaje);
    }


    public void Llamar(View view){

        Uri uri = Uri.parse("tel:" + 947399909);

        Intent llamada = new Intent(
               Intent.ACTION_DIAL, // mostrar la pasarela de llamada
                uri // establece al nro telefonico a quien se va a llamar
        );

        startActivity(llamada);
    }

    public void Whatsapp(View view){

        String numero = "947399909";
        String mensaje = "Deseo realizar una cita";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String uri = "whatsapp://send?phone="+numero+"&text="+mensaje;
        sendIntent.setData(Uri.parse(uri));

        startActivity(sendIntent);
    }
}