package com.example.juniorsantos.barberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.juniorsantos.barberapp.core.BaseActivity;

public class TelaAgendar extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_agendar);

        //usuario carregado no singleton
    }

    /**
     *
     * @param view
     */
    public void abrirAgendamentoBarba(View view){
        Intent intent = new Intent(this, TelaBarbeiro.class);
        intent.putExtra("servico","barba");

        startActivity(intent);
    }

    public void abrirAgendamentoCabelo(View view){
        Intent intent = new Intent(this, TelaBarbeiro.class);

        intent.putExtra("servico","cabelo");
        startActivity(intent);
    }





}

