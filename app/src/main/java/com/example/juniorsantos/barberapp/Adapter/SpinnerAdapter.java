package com.example.juniorsantos.barberapp.Adapter;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juniorsantos.barberapp.Entidades.Agendamento;

import java.util.List;

public class SpinnerAdapter implements android.widget.SpinnerAdapter {
    private List<Agendamento> agendamentos;

    public SpinnerAdapter(List<Agendamento> produtos) {
        this.agendamentos = produtos;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }


    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Agendamento getItem(int position) {
        return agendamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        TextView tx = convertView.findViewById(android.R.id.text1);
        tx.setText(getItem(position).getNome());
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return agendamentos.isEmpty();
    }
}