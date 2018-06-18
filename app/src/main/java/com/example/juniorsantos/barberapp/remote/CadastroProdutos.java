package com.example.juniorsantos.barberapp.remote;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.Adapter.SpinnerAdapter;
import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Produtos;
import com.example.juniorsantos.barberapp.Entidades.Usuarios;
import com.example.juniorsantos.barberapp.Helper.Base64Custom;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.dataloader.ProdutoDataloader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;


public class CadastroProdutos extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private SpinnerAdapter spinnerAdapter;
    private Spinner spinnerc;
    private ValueEventListener valueEventListenerProdutos;
    private Button btnGravar, btnVoltarTelaInicial;
    private EditText edtNome, edtServiço, edtHorario;
    private Produtos produtos;
    private Usuarios usuarios;
    private DatabaseReference firebase;
    private int day,month,year,hour,minute;
    String nomeRecurso;
    private int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
    CalendarView calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        edtHorario = (EditText) findViewById(R.id.edtHorario);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtServiço = (EditText) findViewById(R.id.edtValor);
        btnGravar = (Button) findViewById(R.id.btnGravar);
        spinnerc = (Spinner) findViewById(R.id.spinnerview2);
        btnVoltarTelaInicial = (Button) findViewById(R.id.btnVoltarTelaInicial);


        firebase = ConfiguracaoFirebase.getFirebase().child("barbeiros");



        valueEventListenerProdutos = new ProdutoDataloader(new ProdutoDataloader.ProdutoDataListener() {
            @Override
            public void onProdutoLoaded(List<Produtos> list) {
                spinnerc.setAdapter(new SpinnerAdapter(list));

            }




        });












        edtHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(CadastroProdutos.this , CadastroProdutos.this , year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }



        });





        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                produtos = new Produtos();


                produtos.setNome(edtNome.getText().toString());
                produtos.setServiço(edtServiço.getText().toString());
                produtos.setDate(edtHorario.getText().toString());
                String nomeBarbeiro = ((Produtos) spinnerc.getSelectedItem()).getNome();
                produtos.setBarbeiro(nomeBarbeiro);
                produtos.setCliente(produtos.getCliente());
                String idenficadorUsuario = Base64Custom.codificarBase64(produtos.getNome());
                produtos.setId(idenficadorUsuario);
                if (produtos.salvar(produtos) == true){

                    Toast.makeText(CadastroProdutos.this, "Produto inserido com sucesso", Toast.LENGTH_LONG).show();

                }

                else{
                    Toast.makeText(CadastroProdutos.this, "Erro ao inserir produto", Toast.LENGTH_LONG).show();
                }








            }
        });








        btnVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarTelaInicial();
            }
        });
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



    private void voltarTelaInicial() {
        Intent intent = new Intent(CadastroProdutos.this, ActivityPrincipal.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        if (month > 9){
            String date1 = dayOfMonth + "/" + (month + 1) + "/" + year;
            edtHorario.setText(date1);
        }

        else{

            String date = dayOfMonth + "/" + "0"+(month + 1) + "/" + year;
            edtHorario.setText(date);

        }


    }


}
