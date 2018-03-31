package com.example.juniorsantos.barberapp.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Junior Santos on 31/03/2018.
 */

public class User {

    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }
}
