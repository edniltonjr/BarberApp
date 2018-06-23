package com.example.juniorsantos.barberapp.core;

import com.example.juniorsantos.barberapp.Entidades.Agendamento;
import com.example.juniorsantos.barberapp.Entidades.Usuarios;


public class DadosSingleton {

    private Usuarios mUser;
    private Agendamento mAgen;

    private static DadosSingleton instance = null;

    public static DadosSingleton getInstance() {
        if (instance == null) {

            instance = new DadosSingleton();
        }
        return instance;
    }



    public Agendamento getAgen(){
        return  mAgen;
    }

    public void setAgendamento (Agendamento agendamento){

        if (this.mAgen == null) {
            this.mAgen = agendamento;
        }

    }

    public Usuarios getUser() {
        return mUser;
    }



    public void setUser(Usuarios user) {
        if (this.mUser == null) {
            this.mUser = user;
        }
    }

    public void clearSingleton(){
        this.mUser = null;
    }

}
