package com.example.juniorsantos.barberapp.core;

import android.app.Application;

import com.example.juniorsantos.barberapp.Entidades.Usuarios;
import com.example.juniorsantos.barberapp.Usuario;

/**
 * Created by Junior Santos on 21/08/2017.
 */

public class Singleton extends Application {

   private String id;
   private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    //criar atributo aqui e adicionar getter e setter



}
