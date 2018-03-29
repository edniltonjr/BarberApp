package com.example.juniorsantos.barberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.model.Agendamento;

import java.util.List;

/**
 * Created by Junior Santos on 21/08/2017.
 */

public class ServicoAdapter extends BaseAdapter {

    public ServicoAdapter(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    private List<Agendamento> agendamentos;

    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Agendamento getItem(int i) {
        return agendamentos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**o que aparece na tela
     *
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.two_line_list_item,viewGroup,false);

        ((TextView)view.findViewById(android.R.id.text1)).setText("Serviço: "+getItem(posicao).getServico());
        ((TextView)view.findViewById(android.R.id.text2)).setText("Barbeiro: "+getItem(posicao).getBarbeiro());

        return view;
    }
}
