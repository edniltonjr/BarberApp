package com.example.juniorsantos.barberapp.Entidades;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.example.juniorsantos.barberapp.core.DadosSingleton;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class Agendamento {


    private String id;
    private String nome;
    private String cliente;
    private String serviço;
    private String barbeiro;
    private String date;

    public Agendamento() {

    }

    public boolean salvar(Agendamento agendamento){
        try {

            DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
            referenciaFirebase.child("agendamento").child(String.valueOf(getId())).setValue(this);

            referenciaFirebase = ConfiguracaoFirebase.getFirebase().child("agendamento");
            referenciaFirebase.child(agendamento.getNome()).setValue(agendamento);
            return true;

        }

        catch (Exception e) {
            e.printStackTrace();
            return false;

        }




    }

    public String getCliente() {
        return DadosSingleton.getInstance().getUser().getEmail().toString();
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServiço() {
        return serviço;
    }

    public void setServiço(String serviço) {
        this.serviço = serviço;
    }

    public String getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(String barbeiro) {



        this.barbeiro = barbeiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<String,String> toFirebaseObject() {
        HashMap<String,String> produtos =  new HashMap<String,String>();
        produtos.put("nome", nome);
        produtos.put("serviço", serviço);
        produtos.put("date", date);

        return produtos;
    }


}
