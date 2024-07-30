package com.example.nutrichef.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrichef.Modelos.ModeloReceta;
import com.example.nutrichef.R;
import com.example.nutrichef.Retrofit.ApiService;
import com.example.nutrichef.Retrofit.RetrofitClient;
import com.example.nutrichef.database.DbHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarReceta extends AppCompatActivity {

    EditText inputTitulo, inputDescripcion, inputCategoria, inputIngredientes, inputPasos, inputValorNutricional;
    Button btn_salir, btn_continuar;
    DbHelper dbHelper;
    private static final String BASE_URL = "http://192.168.0.102:1880/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_receta);

        inputTitulo = findViewById(R.id.activity_agregar_receta__et_inputTitulo);
        inputDescripcion = findViewById(R.id.activity_agregar_receta__et_inputDescripcion);
        inputCategoria = findViewById(R.id.activity_agregar_receta__et_inputCategoria);
        inputIngredientes = findViewById(R.id.activity_agregar_receta__et_inputIngredientes);
        inputPasos = findViewById(R.id.activity_agregar_receta__et_inputPasos);
        inputValorNutricional = findViewById(R.id.activity_agregar_receta__et_inputValorNutricional);
        btn_continuar = findViewById(R.id.activity_agregar_receta__btn_continuar);
        btn_salir = findViewById(R.id.activity_agregar_receta__btn_salir);

        dbHelper = new DbHelper(AgregarReceta.this);


        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = inputTitulo.getText().toString().trim();
                String descripcion = inputDescripcion.getText().toString().trim();
                String categoria = inputCategoria.getText().toString().trim();
                String ingredientes = inputIngredientes.getText().toString().trim();
                String pasos = inputPasos.getText().toString().trim();
                String valorNutricionalStr = inputValorNutricional.getText().toString().trim();

                // Verificar si alguno de los campos está vacío
                if (titulo.isEmpty() || descripcion.isEmpty() || categoria.isEmpty() ||
                        ingredientes.isEmpty() || pasos.isEmpty() || valorNutricionalStr.isEmpty()) {
                    Toast.makeText(AgregarReceta.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    double valorNutricional;
                    try {
                        valorNutricional = Double.parseDouble(valorNutricionalStr);

                        ModeloReceta receta = new ModeloReceta(1, titulo, descripcion, categoria, ingredientes, pasos, valorNutricional, 1);
                        dbHelper.insertReceta(receta);

                        inputTitulo.setText("");
                        inputDescripcion.setText("");
                        inputCategoria.setText("");
                        inputIngredientes.setText("");
                        inputPasos.setText("");
                        inputValorNutricional.setText("");

                        ApiService apiService = RetrofitClient.getClient(BASE_URL).create(ApiService.class);
                        Call<Void> call = apiService.sendReceta(receta);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(AgregarReceta.this, "Envio exitoso", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(AgregarReceta.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.d("envio de receta", "hubi un error");
                                Log.d("envio de receta", String.valueOf(t));
                            }
                        });

                    } catch (NumberFormatException e) {
                        Toast.makeText(AgregarReceta.this, "Valor nutricional inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                // Crear el objeto ModeloReceta

            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarReceta.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}