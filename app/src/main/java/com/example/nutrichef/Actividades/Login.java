package com.example.nutrichef.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutrichef.R;

public class Login extends AppCompatActivity {


    EditText et_usuario, et_contrasena;
    Button btn_ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_usuario = findViewById(R.id.activity_login__input_usuario);
        et_contrasena = findViewById(R.id.activity_login__input_contrasena);

        btn_ingresar= findViewById(R.id.activity_login__btn_ingresar);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (et_usuario.getText().toString().trim().equals("pepito") && et_contrasena.getText().toString().trim().equals("pepito123")) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Usuario o contraseña erroneos", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    Toast.makeText(Login.this, "Porfavor ingrese datos validos", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}