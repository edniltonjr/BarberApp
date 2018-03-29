package com.example.juniorsantos.barberapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.core.BaseActivity;
import com.example.juniorsantos.barberapp.model.DetaillActivity;
import com.example.juniorsantos.barberapp.model.MainActivity;


import java.io.IOException;

public class TelaLogin extends BaseActivity{

    EditText editUser, editSenha;
    Button btnEntrar, btnSair;
    Usuario usuario;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        usuario = new Usuario();

        editUser = (EditText)findViewById(R.id.editUser);
        editSenha = (EditText)findViewById(R.id.editSenha);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnSair = (Button)findViewById(R.id.btnSair);



        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), TelaHorario.class);
                startActivity(intent);
                //finish();

            }
        });


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){

                    String usuario = editUser.getText().toString();
                    String senha = editSenha.getText().toString();

                    if(usuario.isEmpty() || senha.isEmpty()){

                        Toast.makeText(getApplicationContext(), "Nenhum campo pode está vazio", Toast.LENGTH_SHORT).show();

                    } else {

                        url = "http://cafein.com.br/barbershop/logar.php";

                        parametros = "usuario=" + usuario + "&senha=" + senha;

                        new  SolicitaDados().execute(url);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexao foi detectada", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }




    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {

            if(resultado.contains("login_ok")){
                Toast.makeText(getApplicationContext(), editUser.getText() + " conectado com Sucesso!", Toast.LENGTH_SHORT).show();
                usuario.setNome(editUser.getText().toString());
                Intent abreInicio = new Intent(TelaLogin.this, TelaMenu.class);

                getSingleton().setUsuario(usuario);
                startActivity(abreInicio);
            } else {

                Toast.makeText(getApplicationContext(), "Usuario ou Senha estão incorretos!", Toast.LENGTH_SHORT).show();

            }

        }
    }



}
