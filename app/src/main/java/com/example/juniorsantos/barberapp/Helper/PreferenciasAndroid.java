package com.example.juniorsantos.barberapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasAndroid {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "projetoFirebase.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;


    private final String CHAVE_IDENTIFICADOR = "identificarUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";

    public PreferenciasAndroid(Context context) {


        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        editor = preferences.edit();
    }

    public void salvarUsuarioPreferencias(String identificadorUsuario, String nomeUsuario){
        editor.putString(CHAVE_IDENTIFICADOR, identificadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }

    public String getIdentificador(){

        return preferences.getString(CHAVE_IDENTIFICADOR, null);

    }

    public  String getNome(){

        return preferences.getString(CHAVE_NOME, null);

    }

}
