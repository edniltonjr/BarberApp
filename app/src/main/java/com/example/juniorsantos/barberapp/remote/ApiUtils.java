package com.example.juniorsantos.barberapp.remote;

import com.example.juniorsantos.barberapp.model.UserService;

/**
 * Created by Junior Santos on 01/04/2018.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://localhost/demo/login.php/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}