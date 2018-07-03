package com.example.juniorsantos.barberapp.remote;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Helper.PreferenciasAndroid;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.TelaMenu;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth usuarioFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handle = new Handler();

        usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(usuarioFirebase != null &&
                        usuarioFirebase.getCurrentUser() != null &&
                        usuarioFirebase.getCurrentUser().getEmail() != null
                        && !usuarioFirebase.getCurrentUser().getEmail().isEmpty()){

                    ConfiguracaoFirebase.getDadosCliente( usuarioFirebase.getCurrentUser().getEmail() );

                    if(DadosSingleton.getInstance().getUser() != null){

                        startActivity(new Intent(SplashActivity.this, TelaMenu.class));
                    }else {
                        mostrarLogin();
                    }


                }else {
                    mostrarLogin();
                }

            }
        }, 2000);
    }

    private void mostrarLogin() {
        Intent intent = new Intent(SplashActivity.this,
                LoginFire.class);
        startActivity(intent);
        finish();
    }



}

