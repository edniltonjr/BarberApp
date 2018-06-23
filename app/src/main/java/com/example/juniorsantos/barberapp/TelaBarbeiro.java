package com.example.juniorsantos.barberapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.core.BaseActivity;



public class TelaBarbeiro extends BaseActivity {





    Button barb3, barb2, barb1, aleatorio;
    ProgressDialog progressBar;
    String url = "";
    String parametros = "";
    Usuario usuario;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        usuario = new Usuario();






        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_barbeiro);
        progressBar = new ProgressDialog(this);
        progressBar.setIndeterminate(true);
        progressBar.setTitle("Carregando!");

        barb3 = (Button) findViewById(R.id.barb3);
        barb2 = (Button) findViewById(R.id.barb2);
        barb1 = (Button) findViewById(R.id.barb1);
        aleatorio = (Button) findViewById(R.id.aleatorio);


    }

    /**
     * Confirmação
     *
     * @param barbeiro
     */
    void doAgendamento(final String barbeiro) {

        if (isOnline()) { //VERIFICA A CONEXAO


            AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog);
            alert.setTitle("Confirmar");
            final String serv = getIntent().getStringExtra("servico");
            alert.setMessage("\n\rServiço: " + serv + "\n\rBarbeiro: " + barbeiro + "\n \rUsuario: " );
            alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            }).setPositiveButton("Sim, Agendar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    processAgendamento(serv, barbeiro);
                }
            });
            alert.show();


        }

    }

    /**
     * processar agendamento
     *
     * @param serv
     * @param barbeiro
     */
    private void processAgendamento(String serv, String barbeiro) {

        url = "http://cafein.com.br/barbershop/registraragenda.php";

        parametros = "serv=" + serv + "&barbeiro=" + barbeiro;
//                        url = url.concat("/"+parametros);
        new SolicitaDados().execute(url);
    }


    //CRIEI OS METODOS DOS BARBEIROS

    public void barbeiro3(View view) {

        doAgendamento("barbeiro3");

    }


    public void barbeiro2(View view) {

        doAgendamento("barbeiro2");

    }

    public void barbeiro1(View view) {

        doAgendamento("barbeiro1");

    }

    public void aleatorio(View view) {

        doAgendamento("qualquer");

    }


    private class SolicitaDados extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {
            progressBar.dismiss();

            if (resultado != null) {

                if (resultado.contains("agendamento_ok")) {
                    Toast.makeText(getApplicationContext(), " agendamento com Sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(getBaseContext(), "Erro na requisição", Toast.LENGTH_SHORT).show();



        }
    }


}
