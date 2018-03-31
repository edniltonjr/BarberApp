package com.example.juniorsantos.barberapp.login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Junior Santos on 31/03/2018.
 */

public interface ApiInterface {



    @GET("login.php")
    Call<User> performUserLogin(@Query("username") String Username, @Query("password") String Password);


}
