package com.example.proyectoclinicaveterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoclinicaveterinaria.db.DbHelper;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText edtEmailForget,edtPasswForget,edtPasswConfForget;
    Button btnConfirmPassw, btnRegresarPassw;
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Enlazar();
        db = new DbHelper(this);
        btnConfirmPassw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass,repass;
                try{

                    email=edtEmailForget.getText().toString();
                    pass=edtPasswForget.getText().toString();
                    repass=edtPasswConfForget.getText().toString();
                    if(email.equals("")||pass.equals("")||repass.equals(""))
                    {
                        Toast.makeText(ForgetPasswordActivity.this, "No se pueden utilizar campos vacios", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        if (pass.equals(repass)){
                            int updatepass=db.updatePass(email,pass);
                            if(updatepass==1){
                                edtEmailForget.setText("");
                                edtPasswForget.setText("");
                                edtPasswConfForget.setText("");
                                Toast.makeText(ForgetPasswordActivity.this, "Cambio de contraseña exitoso", Toast.LENGTH_SHORT).show();
                                Intent x = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                                startActivity(x);
                            }else{

                                Toast.makeText(ForgetPasswordActivity.this, "Email invalido", Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(ForgetPasswordActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(ForgetPasswordActivity.this, "Erorr"+e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    void Enlazar(){

        edtEmailForget=findViewById(R.id.edtOlvidarCorreo);
        edtPasswForget=findViewById(R.id.edtOlvidarpassw);
        edtPasswConfForget=findViewById(R.id.edtOlvidarconfpassw);
        btnConfirmPassw=findViewById(R.id.btnForgetPassw);
        btnRegresarPassw=findViewById(R.id.btnRegresarForget);

    }
}