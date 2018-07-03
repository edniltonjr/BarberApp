package com.example.juniorsantos.barberapp.remote;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Usuarios;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.TelaMenu;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class LoginFire extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private TextView edtAbreCadastro;
    private Button btnLogar;
    private Button btnSair;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    DatabaseReference databaseUsuario;
    private FirebaseAuth usuarioFirebase;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtAbreCadastro = (TextView) findViewById(R.id.edtAbreCadastro);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnSair = (Button) findViewById(R.id.btnSair);

        CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.circularProgressbar);













        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {

                    usuarios = new Usuarios();
                    usuarios.setEmail(edtEmail.getText().toString());
                    usuarios.setSenha(edtSenha.getText().toString());


                    validarLogin();
                } else {
                    Toast.makeText(LoginFire.this, "Preencha os campos de e-mail e senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edtAbreCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreCadastroUsuario();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void validarLogin() {




        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()){


                    if (task.isSuccessful()){



                        ConfiguracaoFirebase.getDadosCliente(usuarios.getEmail().toString() );


                        if(DadosSingleton.getInstance().getUser() != null){

                            CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.circularProgressbar);




                           showProgressDiadlog();

                            Toast.makeText(LoginFire.this, "Seja Bem vindo " + DadosSingleton.getInstance().getUser().getNome().toString() , Toast.LENGTH_SHORT).show();


                            showProgressDiadlog();

                            abrirTela();
                        }
                    }

                    else {
                        Toast.makeText(LoginFire.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }

                }

                else {
                    Toast.makeText(LoginFire.this, "Não há conexão estabelecida!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    private void abrirTela(){
        Intent intentAbrirTela = new Intent(LoginFire.this, TelaMenu.class);
        startActivity(intentAbrirTela);





    }

    public void  showProgressDiadlog(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Carregando...");
        pDialog.setCancelable(false);
        pDialog.show();
        finish();

    }

    public void abreCadastroUsuario(){
        Intent intent = new Intent(LoginFire.this, CadastroActivity.class);
        startActivity(intent);
        finish();

    }


    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
        Usuarios usuarios = dataSnapshot.getValue(Usuarios.class);
        System.out.println(dataSnapshot.getKey() + " was " + usuarios.getNome() + " meters tall.");
    }



}