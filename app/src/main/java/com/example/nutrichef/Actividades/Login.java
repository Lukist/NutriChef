package com.example.nutrichef.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrichef.Modelos.ModeloUsuario;
import com.example.nutrichef.R;
import com.example.nutrichef.database.DbHelper;
import com.example.nutrichef.utils.SharedPreferencesManager;

public class Login extends AppCompatActivity {


    EditText et_usuario, et_contrasena;
    Button btn_ingresar;
    TextView tv_titulo;
    DbHelper db;
    SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tv_titulo = findViewById(R.id.activity_login__tv_titulo);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/GreatVibes-Regular.ttf");
        tv_titulo.setTypeface(typeface);
        et_usuario = findViewById(R.id.activity_login__input_usuario);
        et_contrasena = findViewById(R.id.activity_login__input_contrasena);

        btn_ingresar= findViewById(R.id.activity_login__btn_ingresar);

        sharedPreferencesManager = new SharedPreferencesManager(this);

        db = new DbHelper(Login.this);

        if (sharedPreferencesManager.isLoggedIn()) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ModeloUsuario usuario = db.comprobarLogin(et_usuario.getText().toString().trim(), et_contrasena.getText().toString().trim());

                if (usuario.getUsuarioId() != -1) {
                    sharedPreferencesManager.logueo();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Ha introducido un valor de forma incorrecta", Toast.LENGTH_SHORT).show();
                }




            }

        });

    }
}