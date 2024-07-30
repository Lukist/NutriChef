package com.example.nutrichef.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String PREF_NAME = "YourAppPrefs";
    private static final String ESTA_LOGEADO = "estalogueado";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void logueo() {
        editor.putBoolean(ESTA_LOGEADO, true);
        editor.apply();
    }

    public void deslogueo() {
        editor.remove(ESTA_LOGEADO);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(ESTA_LOGEADO, false);
    }

}
