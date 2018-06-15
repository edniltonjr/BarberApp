package com.example.juniorsantos.barberapp.DAO;

import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.Entidades.Usuarios;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.example.juniorsantos.barberapp.remote.LoginFire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfiguracaoFirebase {

    private static DatabaseReference refenciaFirebase;
    private static FirebaseAuth autenticacao;

    public static DatabaseReference getFirebase(){
        if (refenciaFirebase == null) {
            refenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

        return refenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }

    public static void getDadosCliente(final String emailLogin){

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("usuario");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    if(datas.child("email").getValue().toString().contains(emailLogin)){

                        DadosSingleton.getInstance().setUser((Usuarios) datas.getValue(Usuarios.class));

                        break;
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);
    }
}
