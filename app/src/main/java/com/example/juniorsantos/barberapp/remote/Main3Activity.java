package com.example.juniorsantos.barberapp.remote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.*;

import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.core.Singleton;
import com.example.juniorsantos.barberapp.model.AgendServicw;
import com.example.juniorsantos.barberapp.model.Agendamento;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    TextView resultadoTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        resultadoTextView = (TextView)findViewById(R.id.textviewresultado);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/customer/add")
                .build();




        AgendServicw servicw = retrofit.create(AgendServicw.class);

        servicw.getCliente(new Callback<List<Agendamento>>() {
            @Override
            public void onResponse(Call<List<Agendamento>> call, Response<List<Agendamento>> response) {

                resultadoTextView.setText(call.toString());

            }

            @Override
            public void onFailure(Call<List<Agendamento>> call, Throwable t) {

                resultadoTextView.setText("ERRO");

            }
        });


    }
}
