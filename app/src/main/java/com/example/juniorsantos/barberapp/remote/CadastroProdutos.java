package com.example.juniorsantos.barberapp.remote;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Produtos;
import com.example.juniorsantos.barberapp.R;
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

import java.util.Calendar;


public class CadastroProdutos extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Button btnGravar, btnVoltarTelaInicial;
    private EditText edtNome, edtServiço, edtHorario;
    private Produtos produtos;
    private DatabaseReference firebase;
    private int day,month,year,hour,minute;
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
        btnVoltarTelaInicial = (Button) findViewById(R.id.btnVoltarTelaInicial);

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





                salvarProduto(produtos);








            }
        });








        btnVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarTelaInicial();
            }
        });
    }


    private boolean salvarProduto(Produtos produtos) {
        try {
            firebase = ConfiguracaoFirebase.getFirebase().child("addprodutos");
            firebase.child(produtos.getNome()).setValue(produtos);

            Toast.makeText(CadastroProdutos.this, "Produto inserido com sucesso", Toast.LENGTH_LONG).show();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }



    private void voltarTelaInicial() {
        Intent intent = new Intent(CadastroProdutos.this, ActivityPrincipal.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
        edtHorario.setText(date);

    }


}
