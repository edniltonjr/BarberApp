package com.example.juniorsantos.barberapp.Entidades;

import java.util.HashMap;

public class Produtos {


    private int id;
    private String nome;
    private String serviço;
    private String date;

    public Produtos() {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
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
