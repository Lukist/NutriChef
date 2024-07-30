package com.example.nutrichef.Retrofit;

import com.example.nutrichef.Modelos.ModeloReceta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/recetas")
    Call<Void> sendReceta(@Body ModeloReceta receta);
}
