package com.rifcode.tachesdesutilisateurs.API;

import com.rifcode.tachesdesutilisateurs.Models.Tache;
import com.rifcode.tachesdesutilisateurs.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetData {


    @GET("todos")
    Call<List<Tache>> getTachesByUserId(@Query("userId") Integer id_user);

    @GET("users")
    Call<List<User>> getUsers();
}
