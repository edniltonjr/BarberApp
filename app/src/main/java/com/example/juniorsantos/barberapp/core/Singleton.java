package com.example.juniorsantos.barberapp.core;

import android.app.Application;

import com.example.juniorsantos.barberapp.Usuario;

/**
 * Created by Junior Santos on 21/08/2017.
 */

public class Singleton extends Application {

    private Usuario usuario;

    //criar atributo aqui e adicionar getter e setter

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
