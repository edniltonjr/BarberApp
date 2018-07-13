package com.example.juniorsantos.barberapp.recyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.Entidades.Agendamento;
import com.example.juniorsantos.barberapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgendamentoCliente extends RecyclerView.Adapter<AgendamentoCliente.AgendamentoViewHolder> {

    Context context;
    List<Agendamento> agendamentos;
    DatabaseReference databaseReference;
    onClickAgendamentoCliente onClickAgendamentoCliente;
    private String teste;

    public AgendamentoCliente(List<Agendamento> agendamentos, Context context, onClickAgendamentoCliente onClickAgendamentoCliente) {
        this.agendamentos = agendamentos;
        this.context = context;
        this.onClickAgendamentoCliente = onClickAgendamentoCliente;
        databaseReference = FirebaseDatabase.getInstance().getReference("agendamento");
    }

    @Override
    public AgendamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AgendamentoViewHolder(LayoutInflater.from(context).inflate(R.layout.blog_row, parent, false));
    }

    @Override
    public void onBindViewHolder(AgendamentoViewHolder holder, int i) {
        final Agendamento a = agendamentos.get(i);
        holder.DATA.setText("DATA :" + a.getDate());
        holder.HORARIO.setText("HORARIO :" + a.getHorario());
        holder.NOME.setText("NOME :" +a.getNome());
        Picasso.with(context).load(a.getImageCliente()).into(holder.post_Image1);
        Picasso.with(context).load(a.getImgStatus()).into(holder.post_Image2);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAgendamentoCliente.onClick1(a);
            }
        });

        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAgendamentoCliente.onClick2(a);
            }
        });

        switch(a.getStatus()){
            case "EM ATENDIMENTO":
                holder.btn.setEnabled(false);
                holder.btn2.setEnabled(true);
                databaseReference.child(a.getIdAgendamento()).child("imgStatus").setValue("https://media.istockphoto.com/photos/green-natural-background-picture-id649675846?k=6&m=649675846&s=612x612&w=0&h=mXTClbPjMIYE6OLNfLgCIR0GpJziAZM4oYlcJBw5EE8=");
                break;

            case "FINALIZADO":
                holder.btn.setEnabled(false);
                holder.btn2.setEnabled(false);
                databaseReference.child(a.getIdAgendamento()).child("imgStatus").setValue("https://png.pngtree.com/thumb_back/fw800/back_pic/04/22/64/495832ad8e872ee.jpg");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return agendamentos == null ? 0 : agendamentos.size();
    }

    public class AgendamentoViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public Button btn, btn2;
        TextView HORARIO, DATA, NOME;
        CircleImageView post_Image1, post_Image2;

        public AgendamentoViewHolder(View v) {
            super(v);
            linearLayout = v.findViewById(R.id.linearlayout);
            btn =  v.findViewById(R.id.btnAtender);
            btn2 =  v.findViewById(R.id.btnFINALIZAR);

            HORARIO = v.findViewById(R.id.txhorario);
            DATA = v.findViewById(R.id.txdata);
            NOME = v.findViewById(R.id.txnome);
            post_Image1 = v.findViewById(R.id.tximg);
            post_Image2 = v.findViewById(R.id.txistatus);
        }

    }

    public interface onClickAgendamentoCliente{
        void onClick1(Agendamento a);
        void onClick2(Agendamento a);
    }
}
