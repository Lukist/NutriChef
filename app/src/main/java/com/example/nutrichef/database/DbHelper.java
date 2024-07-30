package com.example.nutrichef.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nutrichef.Modelos.ModeloReceta;
import com.example.nutrichef.Modelos.ModeloUsuario;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "nutrichef_db.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        ///////////////////////////////////////////// CONSTRUCTOR /////////////////////////////////////////////////////////////
        SQLiteDatabase db = getWritableDatabase();
        if (!checkDatabaseExistence(db)) {
            // The database does not exist, copy it from the assets folder
            try {
                copyDatabase(context);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean checkDatabaseExistence(SQLiteDatabase db) {
        String path = db.getPath();
        File file = new File(path);
        return file.exists();
    }

    private void copyDatabase(Context context) throws IOException {
        // Close the existing database
        close();

        // Open the empty database as the output stream
        OutputStream databaseOutputStream = new FileOutputStream(getDatabasePath(context));

        // Open the pre-populated database as the input stream
        InputStream databaseInputStream = context.getAssets().open("databases/" + DATABASE_NAME);

        // Transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer, 0, length);
        }

        // Close the streams
        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }

    private String getDatabasePath(Context context) {
        return context.getDatabasePath(DATABASE_NAME).getPath();
    }

    public void insertReceta(ModeloReceta receta) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("titulo", receta.getTitulo());
        cv.put("descripcion", receta.getDescripcion());
        cv.put("categoria", receta.getCategoria());
        cv.put("ingredientes", receta.getIngredientes());
        cv.put("pasos", receta.getPasos());
        cv.put("valor_nutricional", receta.getValor_nutricional());
        cv.put("id_usuario", receta.getId_usuario());

        db.insert("Receta", null, cv);

        db.close();

    }

    public List<ModeloReceta> buscarRecetas() {
        List<ModeloReceta> returnList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT DISTINCT * FROM Receta";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id_receta = cursor.getInt(0);
                String titulo = cursor.getString(1);
                String descripcion = cursor.getString(2);
                String categoria = cursor.getString(3);
                String ingredientes = cursor.getString(4);
                String pasos = cursor.getString(5);
                double valorEnergetico = cursor.getDouble(6);
                int id_usuario = cursor.getInt(7);

                ModeloReceta receta = new ModeloReceta(id_receta, titulo, descripcion, categoria, ingredientes, pasos, valorEnergetico, id_usuario);
                returnList.add(receta);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public void updateReceta(ModeloReceta receta) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("titulo", receta.getTitulo());
        cv.put("descripcion", receta.getDescripcion());
        cv.put("categoria", receta.getCategoria());
        cv.put("ingredientes", receta.getCategoria());
        cv.put("pasos", receta.getPasos());
        cv.put("valor_nutricional", receta.getValor_nutricional());
        cv.put("id_usuario", receta.getId_usuario());

        String whereClause = "id_receta = ?";
        String[] whereArgs = { String.valueOf(receta.getId_receta()) };

        db.update("Receta", cv, whereClause, whereArgs);

        db.close();
    }

    public ModeloUsuario comprobarLogin(String nombreUsuario, String clave) {
        SQLiteDatabase db = getReadableDatabase();

        ModeloUsuario usuario = new ModeloUsuario();

        usuario.setUsuarioId(-1);

        String query = "SELECT * FROM Usuario WHERE usuario = ? AND contrasena = ?";

        Cursor cursor = db.rawQuery(query, new String[]{nombreUsuario, clave});

        if (cursor != null && cursor.moveToFirst()) {
            int usuarioId = cursor.getInt(0);
            String usuarioNombre = cursor.getString(1);
            String contrasena = cursor.getString(2);
            String nombre = cursor.getString(3);
            String apellido = cursor.getString(4);

            usuario.setUsuarioId(usuarioId);
            usuario.setUsuario(usuarioNombre);
            usuario.setContrasena(contrasena);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
        }

        return usuario;

    }

    public void deleteReceta(int idReceta) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("Receta", "id_receta = ?", new String[]{String.valueOf(idReceta)});
        db.close();
    }
}
