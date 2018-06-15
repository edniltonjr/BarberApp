package com.example.juniorsantos.barberapp.core;

import com.example.juniorsantos.barberapp.Entidades.Usuarios;


public class DadosSingleton {

    private Usuarios mUser;

    private static DadosSingleton instance = null;

    public static DadosSingleton getInstance() {
        if (instance == null) {

            instance = new DadosSingleton();
        }
        return instance;
    }

    public Usuarios getUser() {


        return mUser;
    }



    public void setUser(Usuarios user) {
        if (this.mUser == null) {
            this.mUser = user;
        }
    }
}
