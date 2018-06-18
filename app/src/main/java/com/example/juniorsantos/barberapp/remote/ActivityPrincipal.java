package com.example.juniorsantos.barberapp.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Usuarios;
import com.example.juniorsantos.barberapp.Helper.PreferenciasAndroid;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.example.juniorsantos.barberapp.remote.CadastroProdutos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class ActivityPrincipal extends AppCompatActivity {

    private FirebaseAuth usuarioFirebase;
    private Button btnAddProduto, btnVerProduto, btnLogout;
    private TextView teste;

    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        teste = (TextView) findViewById(R.id.teste);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnAddProduto = (Button) findViewById(R.id.btnAddProduto);
        btnVerProduto = (Button) findViewById(R.id.btnVerProdutos);


        btnAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarProdutos();
            }
        });



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deslogarUsuario();
            }
        });

        btnVerProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verProdutos();
            }
        });




    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal_activity, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            deslogarUsuario();

        }

        return super.onOptionsItemSelected(item);
    }

    private void cadastrarProdutos() {
        Intent intent = new Intent(ActivityPrincipal.this, CadastroProdutos.class);
        startActivity(intent);
        finish();
    }

    private void verProdutos() {
        Intent intent = new Intent(ActivityPrincipal.this, ProdutosActivity.class);
        startActivity(intent);

        finish();
    }

    private void deslogarUsuario() {
        DadosSingleton.getInstance().setUser(null);
        usuarioFirebase.signOut();
        Intent intent = new Intent(ActivityPrincipal.this, LoginFire.class);
        startActivity(intent);
        finish();
    }
}
