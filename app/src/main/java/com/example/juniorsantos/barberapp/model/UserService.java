package com.example.juniorsantos.barberapp.model;

/**
 * Created by Junior Santos on 01/04/2018.
 */

import com.example.juniorsantos.barberapp.model.ResObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("login/{username}/{password}")
    Call login(@Path("username") String username, @Path("password") String password);
}
