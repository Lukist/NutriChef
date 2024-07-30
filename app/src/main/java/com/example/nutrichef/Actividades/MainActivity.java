package com.example.nutrichef.Actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrichef.Adaptadores.RecetaAdapter;
import com.example.nutrichef.Modelos.ModeloReceta;
import com.example.nutrichef.R;
import com.example.nutrichef.database.DbHelper;
import com.example.nutrichef.utils.SharedPreferencesManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_add_receta;
    DbHelper db;

    ListView lv_recetas;
    RecetaAdapter recetaAdapter;
    Button btn_salir;
    private ImageButton searchButton, btn_addReceta;
    private EditText searchEditText;
    private ConstraintLayout headerLayout, rootLayout;
    TextView tv_titulo;
    List<ModeloReceta> listaRecetas, listaFiltrada;
    SharedPreferencesManager sharedPreferencesManager;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.searchButton);
        searchEditText = findViewById(R.id.searchEditText);
        headerLayout = findViewById(R.id.activity_main__cl_headerLayout);
        rootLayout = findViewById(R.id.rootLayout);
        tv_titulo = findViewById(R.id.activity_main__tv_titulo);
        lv_recetas = findViewById(R.id.activity_main__lv_listaRecetas);
        btn_addReceta = findViewById(R.id.activity_main__btn_add_receta);
        btn_salir = findViewById(R.id.activity_main__btn_salir);


        sharedPreferencesManager = new SharedPreferencesManager(MainActivity.this);

        db = new DbHelper(MainActivity.this);

        listaRecetas = db.buscarRecetas();
        listaFiltrada = new ArrayList<>(listaRecetas);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/GreatVibes-Regular.ttf");
        tv_titulo.setTypeface(typeface);

        recetaAdapter = new RecetaAdapter(MainActivity.this, R.layout.row_comidas, listaFiltrada);
        lv_recetas.setAdapter(recetaAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEditText.getVisibility() == View.GONE) {
                    searchEditText.setVisibility(View.VISIBLE);
                    searchEditText.requestFocus();
                    showKeyboard(searchEditText);
                    animateVisibility(searchEditText, true);
                } else {
                    searchEditText.setVisibility(View.GONE);
                    animateVisibility(searchEditText, false);
                }
            }
        });

        rootLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (searchEditText.getVisibility() == View.VISIBLE && searchEditText.getText().toString().isEmpty()) {
                        animateVisibility(searchEditText, false);
                    }
                }
                return false;
            }
        });

        btn_addReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarReceta.class);
                startActivity(intent);

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

        lv_recetas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                int index = position;
                ModeloReceta receta = (ModeloReceta) adapterView.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.WhiteDialog);

                builder.setTitle("¿Esta seguro que desea eleminar esta receta?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteReceta(receta.getId_receta());

                        if (position >= 0 && position < listaFiltrada.size()) {
                            listaFiltrada.remove(position);
                            recetaAdapter.notifyDataSetChanged();
                            listaRecetas = db.buscarRecetas();
                        } else {
                            Log.e("MainActivity", "Posición inválida: " + position);
                        }
                    }
                });

                builder.setNegativeButton("no", null);

                builder.show();

                return true;
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesManager.deslogueo();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

//        btn_add_receta = findViewById(R.id.activity_main__btn_add_receta);
//        lv_recetas = findViewById(R.id.activity_main__lv_lista_recetas);
//        db = new DbHelper(MainActivity.this);
//
//        recetaAdapter = new RecetaAdapter(MainActivity.this, R.layout.row_comidas, db.buscarRecetas());
//        lv_recetas.setAdapter(recetaAdapter);
//
//        btn_add_receta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.WhiteDialog);
//
//                View layoutInflater = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog__form_receta, null);
//                EditText titulo = layoutInflater.findViewById(R.id.activity_recetas_info__input_titulo);
//                EditText descripcion = layoutInflater.findViewById(R.id.activity_recetas_info__input_descripcion);
//                EditText categoria = layoutInflater.findViewById(R.id.activity_recetas_info__input_categoria);
//                EditText ingredientes = layoutInflater.findViewById(R.id.activity_recetas_info__input_ingredientes);
//                EditText pasos = layoutInflater.findViewById(R.id.activity_recetas_info__input_pasos);
//                EditText valor_nutrcional = layoutInflater.findViewById(R.id.activity_recetas_info__input_valor_energetico);
//
//                builder.setView(layoutInflater);
//
//                builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        ModeloReceta receta = new ModeloReceta(1, titulo.getText().toString(), descripcion.getText().toString(), categoria.getText().toString(), ingredientes.getText().toString(), pasos.getText().toString(),Double.parseDouble(valor_nutrcional.getText().toString().trim()), 1);
//
//                        db.insertReceta(receta);
//
//                        recetaAdapter = new RecetaAdapter(MainActivity.this, R.layout.row_comidas, db.buscarRecetas());
//                        lv_recetas.setAdapter(recetaAdapter);
//                    }
//                });
//
//                builder.show();
//
//            }
//        });
//
//        lv_recetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                ModeloReceta receta =  (ModeloReceta) adapterView.getItemAtPosition(i);
//                Intent intent = new Intent(MainActivity.this, RecetasInfo.class);
//                intent.putExtra("receta", (Serializable) receta);
//                startActivity(intent);
//            }
//        });
//    }
//
//
    }

    private void filterList(String query) {
         listaFiltrada.clear();

        if (query.isEmpty()) {
            listaFiltrada.addAll(listaRecetas);
        }else {
            String lowerCaseQuery = query.toLowerCase();

            for (ModeloReceta receta : db.buscarRecetas()) {
                if (receta.getTitulo().toLowerCase().contains(lowerCaseQuery) || receta.getCategoria().toLowerCase().contains(lowerCaseQuery)) {
                    listaFiltrada.add(receta);
                }
            }
        }
         recetaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the ListView with the latest data from the database
        recetaAdapter.clear();
        recetaAdapter.addAll(db.buscarRecetas());
        recetaAdapter.notifyDataSetChanged();
    }

    private void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void animateVisibility(final View view, boolean show) {
        ObjectAnimator animator;
        if (show) {
            view.setAlpha(0f);
            view.setVisibility(View.VISIBLE);
            animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        } else {
            animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                    hideKeyboard();
                }

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationRepeat(Animator animation) {}
            });
        }
        animator.setDuration(300);
        animator.start();
    }
}