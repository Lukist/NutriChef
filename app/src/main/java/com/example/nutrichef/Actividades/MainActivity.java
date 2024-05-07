package com.example.nutrichef.Actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.nutrichef.Adaptadores.RecetaAdapter;
import com.example.nutrichef.Modelos.ModeloReceta;
import com.example.nutrichef.R;
import com.example.nutrichef.database.DbHelper;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_add_receta;
    DbHelper db;

    ListView lv_recetas;
    RecetaAdapter recetaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add_receta = findViewById(R.id.activity_main__btn_add_receta);
        lv_recetas = findViewById(R.id.activity_main__lv_lista_recetas);
        db = new DbHelper(MainActivity.this);

        recetaAdapter = new RecetaAdapter(MainActivity.this, R.layout.row_comidas, db.buscarRecetas());
        lv_recetas.setAdapter(recetaAdapter);

        btn_add_receta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.WhiteDialog);

                View layoutInflater = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog__form_receta, null);
                EditText titulo = layoutInflater.findViewById(R.id.activity_recetas_info__input_titulo);
                EditText descripcion = layoutInflater.findViewById(R.id.activity_recetas_info__input_descripcion);
                EditText categoria = layoutInflater.findViewById(R.id.activity_recetas_info__input_categoria);
                EditText ingredientes = layoutInflater.findViewById(R.id.activity_recetas_info__input_ingredientes);
                EditText pasos = layoutInflater.findViewById(R.id.activity_recetas_info__input_pasos);
                EditText valor_nutrcional = layoutInflater.findViewById(R.id.activity_recetas_info__input_valor_energetico);

                builder.setView(layoutInflater);

                builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ModeloReceta receta = new ModeloReceta(1, titulo.getText().toString(), descripcion.getText().toString(), categoria.getText().toString(), ingredientes.getText().toString(), pasos.getText().toString(),Double.parseDouble(valor_nutrcional.getText().toString().trim()), 1);

                        db.insertReceta(receta);

                        recetaAdapter = new RecetaAdapter(MainActivity.this, R.layout.row_comidas, db.buscarRecetas());
                        lv_recetas.setAdapter(recetaAdapter);
                    }
                });

                builder.show();

            }
        });

        lv_recetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ModeloReceta receta =  (ModeloReceta) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, RecetasInfo.class);
                intent.putExtra("receta", (Serializable) receta);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the ListView with the latest data from the database
        recetaAdapter.clear();
        recetaAdapter.addAll(db.buscarRecetas());
        recetaAdapter.notifyDataSetChanged();
    }
}