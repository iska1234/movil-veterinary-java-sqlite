package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiReniec extends AppCompatActivity {

    EditText dniApi;
    TextView SalidadniApi,tvSalidaNombre,tvSalidaApellidoM,tvSalidaApellidoP,tvSalidaDNi;
    Button ConsultarApi, btnRegresar,btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_reniec);
        EnlazoApi();

        ConsultarApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultarReniec();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ApiReniec.this,Nuevoveterinario.class);
                i.putExtra("nombre",tvSalidaNombre.getText());
                i.putExtra("apellidoP",tvSalidaApellidoP.getText());
                i.putExtra("apellidoM",tvSalidaApellidoM.getText());
                i.putExtra("dni",tvSalidaDNi.getText());

                startActivity(i);
            }
        });
    }

    private void ConsultarReniec(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            int dniA = Integer.parseInt(dniApi.getText().toString());
            // Crea una nueva conexión HTTP usando la URL de la API
            URL url = new URL("https://dniruc.apisperu.com/api/v1/dni/"+dniA+"?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImxtdGltYW5hZ0BnbWFpbC5jb20ifQ.udFejq_ZQw4kqP6wfRGX1RaKaksh-lFwcqlM7p9Y1dU");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Establece el método de la solicitud como GET
            conn.setRequestMethod("GET");

            // Procesa la respuesta de la API
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                // Si la respuesta es exitosa, lee los datos de la respuesta
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder response = new StringBuilder();

                String line;
                while ((line = in.readLine()) != null) {

                    response.append(line);
                }
                in.close();

                // Parsea la cadena en un objeto JSON
                JSONObject responseJson = new JSONObject(response.toString());

                // Obtiene los valores que deseas asignar a tus TextViews
                String dni = responseJson.getString("dni");
                String nombres = responseJson.getString("nombres");
                String apellido_paterno = responseJson.getString("apellidoPaterno");
                String apellido_materno = responseJson.getString("apellidoMaterno");
                tvSalidaDNi.setText(dni);
                tvSalidaApellidoM.setText(apellido_materno);
                tvSalidaApellidoP.setText(apellido_paterno);
                tvSalidaNombre.setText(nombres);

                // Muestra el resultado en el TextView
                //SalidadniApi.setText(response.toString());
            } else {
                // Si la respuesta no fue exitosa, maneja el error
                SalidadniApi.setText("Error: " + responseCode);
            }
        }catch (MalformedURLException ex){
            SalidadniApi.setText("Error al consultar la API: " + ex.getMessage());
        }catch (IOException ex) {
            // Si se produce un error al abrir la conexión, maneja el error aquí
            SalidadniApi.setText("Error al abrir la conexión: " + ex.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }




    void EnlazoApi() {
        dniApi = findViewById(R.id.edtapidni);
        SalidadniApi = findViewById(R.id.edtsalidaapidni);
        ConsultarApi = findViewById(R.id.btnconsultardniapi);
        tvSalidaApellidoM=findViewById(R.id.tvSalidaApellidoM);
        tvSalidaApellidoP=findViewById(R.id.tvSalidaApellidoP);
        tvSalidaDNi=findViewById(R.id.tvSalidaDNi);
        tvSalidaNombre=findViewById(R.id.tvSalidaNombre);
        btnRegresar= findViewById(R.id.btnRegresarApi);
        btnGuardar=findViewById(R.id.btnGuardarDataApi);
    }

}