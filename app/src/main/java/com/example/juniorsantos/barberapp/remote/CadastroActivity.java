package com.example.juniorsantos.barberapp.remote;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Usuarios;
import com.example.juniorsantos.barberapp.Helper.Base64Custom;
import com.example.juniorsantos.barberapp.Helper.PreferenciasAndroid;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtCadNome;
    private EditText edtCadSobrenome;
    private EditText edtCadAniversario;
    private EditText edtCadEmail;
    private EditText edtCadSenha;
    private EditText edtCadConfirmarSenha;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btnGravar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;
    private Teste teste;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtCadNome = (EditText) findViewById(R.id.edtCadNome);
        edtCadSobrenome = (EditText) findViewById(R.id.edtCadSobrenome);
        edtCadAniversario = (EditText) findViewById(R.id.edtCadAniversario);
        edtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        edtCadConfirmarSenha = (EditText) findViewById(R.id.edtCadConfirmarSenha);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        btnGravar = (Button) findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())){

                    usuarios = new Usuarios();
                    usuarios.setNome(edtCadNome.getText().toString());
                    usuarios.setNome(edtCadSobrenome.getText().toString());
                    usuarios.setEmail(edtCadEmail.getText().toString());
                    usuarios.setSenha(edtCadSenha.getText().toString());
                    usuarios.setAniversario(edtCadAniversario.getText().toString());
                    usuarios.setTipo("1");



                    if(rbMasculino.isChecked()){
                        usuarios.setSexo("Masculino");
                    }

                    else{
                        usuarios.setSexo("Feminino");
                    }

                    cadastrarUsuario();




                }
                else{
                    Toast.makeText(CadastroActivity.this, "As Senhas não são correspondentes", Toast.LENGTH_SHORT).show();
                }



            }
        });{

        }
    }


    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(

                usuarios.getEmail(),
                usuarios.getSenha()

        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usuario Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();

                    String idenficadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(idenficadorUsuario);
                    usuarios.setImg("https://cdn.icon-icons.com/icons2/1097/PNG/512/1485477097-avatar_78580.png");
                    usuarios.salvar();

                    PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid((CadastroActivity.this));
                    preferenciasAndroid.salvarUsuarioPreferencias(idenficadorUsuario, usuarios.getId());

                    abrirLoginUsuario();




                }

                else{
                    String erroExcecao = "";
                    try{

                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){

                        erroExcecao = "Digite uma senha mais forte, contendo no minimo 8 caracteres de letras e numeros";

                    }

                    catch (FirebaseAuthInvalidCredentialsException e){

                        erroExcecao = "O E-mail digitado é invalido, digite um novo email";

                    }

                    catch (FirebaseAuthUserCollisionException e){

                        erroExcecao = "Esse E-mail já esta cadastrado no sistema";

                    }

                    catch (Exception e){

                        erroExcecao = "Erro ao efetuar o cadastro";
                        e.printStackTrace();

                    }

                    Toast.makeText(CadastroActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();




                }

            }
        });
    }

    public void abrirLoginUsuario(){

        Intent intentEnviadora = new Intent (getApplicationContext(), LoginFire.class);
        startActivity(intentEnviadora);
        finish();
    }

}