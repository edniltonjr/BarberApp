package com.example.juniorsantos.barberapp.model;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by Junior Santos on 27/04/2018.
 */

public interface AgendServicw {



    @GET ("/agendamento")
    void getCliente(Callback<List<Agendamento>> callback);
}
