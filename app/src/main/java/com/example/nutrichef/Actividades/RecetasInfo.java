package com.example.nutrichef.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutrichef.Modelos.ModeloReceta;
import com.example.nutrichef.R;
import com.example.nutrichef.database.DbHelper;

import java.io.Serializable;

public class RecetasInfo extends AppCompatActivity {

    EditText et_titulo, et_descripcion, et_categoria, et_ingredientes, et_pasos, et_valor_nutricional;

    Button btn_cambiar, btn_salir;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas_info);

        et_titulo = findViewById(R.id.activity_recetas_info__input_titulo);
        et_descripcion = findViewById(R.id.activity_recetas_info__input_descripcion);
        et_categoria = findViewById(R.id.activity_recetas_info__input_categoria);
        et_ingredientes = findViewById(R.id.activity_recetas_info__input_ingredientes);
        et_pasos = findViewById(R.id.activity_recetas_info__input_pasos);
        et_valor_nutricional = findViewById(R.id.activity_recetas_info__input_valor_energetico);
        btn_salir = findViewById(R.id.activity_recetas_info__btn_salir);

        btn_cambiar = findViewById(R.id.activity_recetas_info__btn_cambiar);

        dbHelper = new DbHelper(RecetasInfo.this);

        ModeloReceta receta = (ModeloReceta) getIntent().getSerializableExtra("receta");

        et_titulo.setText(receta.getTitulo());
        et_descripcion.setText(receta.getDescripcion());
        et_categoria.setText(receta.getCategoria());
        et_ingredientes.setText(receta.getIngredientes());
        et_pasos.setText(receta.getPasos());
        et_valor_nutricional.setText(String.valueOf(receta.getValor_nutricional()));

        btn_cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receta.setTitulo(et_titulo.getText().toString());
                receta.setDescripcion(et_descripcion.getText().toString());
                receta.setCategoria(et_categoria.getText().toString());
                receta.setIngredientes(et_ingredientes.getText().toString());
                receta.setPasos(et_pasos.getText().toString());
                receta.setValor_nutricional(Double.parseDouble(et_valor_nutricional.getText().toString()));

                dbHelper.updateReceta(receta);

                Toast.makeText(RecetasInfo.this, "La receta se a actualizado con exito!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecetasInfo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}