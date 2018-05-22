package com.example.juniorsantos.barberapp.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.juniorsantos.barberapp.Adapter.ProdutosAdapter;
import com.example.juniorsantos.barberapp.Adapter.SpinnerAdapter;
import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Produtos;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.dataloader.ProdutoDataloader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProdutosActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<Produtos> adapter;
    private ArrayList<Produtos> produtos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerProdutos;
    private Button btnVoltarTelaInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        spinner = (Spinner) findViewById(R.id.spinnerview);

        List<SpinnerAdapter> spinnerAdapters = new ArrayList<SpinnerAdapter>();




        firebase = ConfiguracaoFirebase.getFirebase().child("addprodutos");
        valueEventListenerProdutos = new ProdutoDataloader(new ProdutoDataloader.ProdutoDataListener() {
            @Override
            public void onProdutoLoaded(List<Produtos> list) {

                spinner.setAdapter(new SpinnerAdapter(list));

            }






        });






        btnVoltarTelaInicial = (Button) findViewById(R.id.btnVoltarTelaInicial2);
        btnVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarTelaInicial();
            }
        });

    }


    private void voltarTelaInicial() {
        Intent intent = new Intent(ProdutosActivity.this, ActivityPrincipal.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerProdutos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerProdutos);
    }
}