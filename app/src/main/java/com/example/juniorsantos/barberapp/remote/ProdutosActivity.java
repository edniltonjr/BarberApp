package com.example.juniorsantos.barberapp.remote;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.Adapter.SpinnerAdapter;
import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Produtos;
import com.example.juniorsantos.barberapp.Entidades.Usuarios;
import com.example.juniorsantos.barberapp.Helper.PreferenciasAndroid;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.dataloader.ProdutoDataloader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ProdutosActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{

    Calendar myCalendar = Calendar.getInstance();
    private Spinner spinner;
    private TextView textView;
    private ArrayAdapter<Produtos> adapter;
    private ArrayList<Produtos> produtos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerProdutos;
    private Button btnVoltarTelaInicial;
    private DatePickerDialog datePickerDialog;
    private int day,month,year,hour,minute;
    private int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
    private FirebaseAuth usuarioFirebase;

    String userId;
    String firebaseUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();

    DatabaseReference databaseUsuario;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        usuarioFirebase = ConfiguracaoFirebase.getFirebaseAutenticacao();

        spinner = (Spinner) findViewById(R.id.spinnerview);
        textView = (TextView) findViewById(R.id.txthorario);










        firebase = ConfiguracaoFirebase.getFirebase().child("barbeiros");








        valueEventListenerProdutos = new ProdutoDataloader(new ProdutoDataloader.ProdutoDataListener() {
            @Override
            public void onProdutoLoaded(List<Produtos> list) {
                spinner.setAdapter(new SpinnerAdapter(list));

            }



        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ProdutosActivity.this , ProdutosActivity.this , year,month,day);
                datePickerDialog.show();
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

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    String data = String.valueOf(dayOfMonth) + " /"
                            + String.valueOf(monthOfYear+1) + " /" + String.valueOf(year);
                    Toast.makeText(ProdutosActivity.this,
                            "DATA = " + data, Toast.LENGTH_SHORT)
                            .show();
                }
            };

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        textView.setText(dayOfMonth +"/"+month+ "/" +year);


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


    private void deslogarUsuario() {
        usuarioFirebase.signOut();
        Intent intent = new Intent(ProdutosActivity.this, LoginFire.class);
        startActivity(intent);
        finish();
    }



}