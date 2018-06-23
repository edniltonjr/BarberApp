package com.example.juniorsantos.barberapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.Entidades.Agendamento;
import com.example.juniorsantos.barberapp.R;

import java.util.ArrayList;



/**
 * Created by Daniel Lopes on 23/06/2017.
 */

public class ProdutosAdapter extends ArrayAdapter<Agendamento> {

    private ArrayList<Agendamento> produto;
    private Context context;

    public ProdutosAdapter(Context c, ArrayList<Agendamento> objects) {
        super(c, 0, objects);

        this.context = c;
        this.produto = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (produto != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.lista_produtos, parent, false);

            TextView textViewNome = (TextView) view.findViewById(R.id.textViewNome);
            TextView textViewValor = (TextView) view.findViewById(R.id.textViewValor);
            TextView textViewDate = (TextView) view.findViewById(R.id.textViewDate);

            Agendamento agendamento2 = produto.get(position);
            textViewNome.setText(agendamento2.getNome());
            textViewValor.setText(agendamento2.getNome().toString());
            textViewDate.setText(agendamento2.getDate().toString());


        }

        return view;
    }
}
