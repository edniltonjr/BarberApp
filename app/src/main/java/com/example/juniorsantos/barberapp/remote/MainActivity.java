package com.example.juniorsantos.barberapp.remote;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.Entidades.Agendamento;
import com.example.juniorsantos.barberapp.R;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mbLogList;
    private DatabaseReference mDatabase;
    private Agendamento agendamento;
    private static String autenticacao;
    List<Agendamento> agendament;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autenticacao = FirebaseAuth.getInstance().getCurrentUser().getEmail(); //RETORNA O USUARIO LOGADO



        mDatabase= FirebaseDatabase.getInstance().getReference().child("agendamento");




 //       mDatabase.orderByChild("emailCliente").equalTo(DadosSingleton.getInstance().getUser().getEmail());


        mDatabase.keepSynced(true);

        mbLogList=(RecyclerView)findViewById(R.id.myrecyclerview);

        mbLogList.setHasFixedSize(true);
        mbLogList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {

        super.onStart();
        Query query = mDatabase.orderByChild("idAgendamento");

        FirebaseRecyclerAdapter<Agendamento, BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Agendamento, BlogViewHolder>
                (Agendamento.class, R.layout.blog_row, BlogViewHolder.class, query) {




            @Override



            protected void populateViewHolder(final BlogViewHolder viewHolder, final Agendamento model, int position) {


                viewHolder.setData("DATA:      "+model.getDate());
                viewHolder.setHora("HORARIO:   "+model.getHorario());
                viewHolder.setNome("CLIENTE:   "+model.getNomeCliente());
                viewHolder.setImage(getApplicationContext(), model.getImageCliente());
                viewHolder.setImgstatus(getApplicationContext(), model.getImgStatus());

         /*       if (model.getStatus().equals("EM ATENDIMENTO")){

                    viewHolder.btn.setEnabled(false);
                    viewHolder.btn2.setEnabled(true);
                    mDatabase.child(model.getIdAgendamento()).child("imgStatus").setValue("https://media.istockphoto.com/photos/green-natural-background-picture-id649675846?k=6&m=649675846&s=612x612&w=0&h=mXTClbPjMIYE6OLNfLgCIR0GpJziAZM4oYlcJBw5EE8=");

                }

                if (model.getStatus().equals("FINALIZADO")){
                    viewHolder.btn.setEnabled(false);
                    viewHolder.btn2.setEnabled(false);
                    mDatabase.child(model.getIdAgendamento()).child("imgStatus").setValue("https://png.pngtree.com/thumb_back/fw800/back_pic/04/22/64/495832ad8e872ee.jpg");
                }

                */
                switch (model.getStatus()) {
                    case "EM ATENDIMENTO":
                        viewHolder.btn.setEnabled(false);
                        viewHolder.btn2.setEnabled(true);
                        mDatabase.child(model.getIdAgendamento()).child("imgStatus").setValue("https://media.istockphoto.com/photos/green-natural-background-picture-id649675846?k=6&m=649675846&s=612x612&w=0&h=mXTClbPjMIYE6OLNfLgCIR0GpJziAZM4oYlcJBw5EE8=");
                        break;

                    case "FINALIZADO":
                        viewHolder.btn.setEnabled(false);
                        viewHolder.btn2.setEnabled(false);
                        mDatabase.child(model.getIdAgendamento()).child("imgStatus").setValue("https://png.pngtree.com/thumb_back/fw800/back_pic/04/22/64/495832ad8e872ee.jpg");
                        break;
                }


                viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mDatabase.child(model.getIdAgendamento()).child("status").setValue("EM ATENDIMENTO");
                        Toast.makeText(MainActivity.this, "ATENDIMENTO INICIADO COM SUCESSO", Toast.LENGTH_LONG).show();


                    }
                });

                viewHolder.btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        mDatabase.child(model.getIdAgendamento()).child("status").setValue("FINALIZADO");
                        Toast.makeText(MainActivity.this, "ATENDIMENTO FINALIZADO COM SUCESSO", Toast.LENGTH_LONG).show();
                    }
                });


            }
        };

        mbLogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public LinearLayout linearLayout;
        public Button btn, btn2;



        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
            btn = (Button) itemView.findViewById(R.id.btnAtender);
            btn2 = (Button) itemView.findViewById(R.id.btnFINALIZAR);

        }


        public void setHora(String hora) {
            TextView HORARIO = (TextView) mView.findViewById(R.id.txhorario);
            HORARIO.setText(hora);
        }

        public void setData(String data) {
            TextView DATA = (TextView) mView.findViewById(R.id.txdata);
            DATA.setText(data);
        }

        public void setNome(String nome) {
            TextView NOME = (TextView) mView.findViewById(R.id.txnome);
            NOME.setText(nome);
        }

        public void setImage(Context ctx, String image) {
            CircleImageView post_Image = mView.findViewById(R.id.tximg);
            Picasso.with(ctx).load(image).into(post_Image);
        }

        public void setImgstatus(Context ctx, String imgstatus) {
            CircleImageView post_Image = mView.findViewById(R.id.txistatus);
            Picasso.with(ctx).load(imgstatus).into(post_Image);
        }


    }
}