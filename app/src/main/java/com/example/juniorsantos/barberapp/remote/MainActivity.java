package com.example.juniorsantos.barberapp.remote;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.Entidades.Agendamento;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mbLogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("agendamento");
        mDatabase.keepSynced(true);

        mbLogList=(RecyclerView)findViewById(R.id.myrecyclerview);
        mbLogList.setHasFixedSize(true);
        mbLogList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Agendamento, BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Agendamento, BlogViewHolder>
                (Agendamento.class, R.layout.blog_row, BlogViewHolder.class, mDatabase) {


            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Agendamento model, int position) {

                viewHolder.setData("DATA:      "+model.getDate());
                viewHolder.setHora("HORARIO:   "+model.getHorario());
                viewHolder.setNome("CLIENTE:   "+model.getNomeCliente());



            }
        };

        mbLogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

        }

        public void setHora(String hora){
            TextView HORARIO=(TextView)mView.findViewById(R.id.txhorario);
            HORARIO.setText(hora);
        }

        public void setData(String data){
            TextView DATA=(TextView)mView.findViewById(R.id.txdata);
            DATA.setText(data);
        }

        public void setNome(String nome){
            TextView NOME=(TextView)mView.findViewById(R.id.txnome);
            NOME.setText(nome);
        }




    }
}
