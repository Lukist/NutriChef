package com.example.nutrichef.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nutrichef.Modelos.ModeloReceta;
import com.example.nutrichef.R;

import java.util.List;

public class RecetaAdapter extends ArrayAdapter<ModeloReceta> {

    private List<ModeloReceta> lista_recetas;
    private Context mContext;
    private int resourceLayout;

    public RecetaAdapter(@NonNull Context context, int resource, @NonNull List<ModeloReceta> objects) {
        super(context, resource, objects);

        lista_recetas = objects;
        mContext = context;
        resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        ModeloReceta receta = lista_recetas.get(position);

        TextView titulo = view.findViewById(R.id.row_comidas__titulo);
        titulo.setText(receta.getTitulo());

        TextView descripcion = view.findViewById(R.id.row_comidas__descripcion);
        descripcion.setText(receta.getDescripcion());

        return view;
    }
}
