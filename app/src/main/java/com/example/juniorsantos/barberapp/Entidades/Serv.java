package com.example.juniorsantos.barberapp.Entidades;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Serv {

    public Serv() {

    }


    public boolean salvarServ(Serv serv){
        try {

            DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
            referenciaFirebase.child("serv").child(String.valueOf(getNomeServ())).setValue(this);

            referenciaFirebase = ConfiguracaoFirebase.getFirebase().child("serv");
            referenciaFirebase.child(serv.getNomeServ()).setValue(serv);
            return true;

        }

        catch (Exception e) {
            e.printStackTrace();
            return false;

        }




    }

    private String idServ;
    private String nomeServ;

    public String getIdServ() {
        return idServ;
    }

    public void setIdServ(String idServ) {
        this.idServ = idServ;
    }

    public String getNomeServ() {
        return nomeServ;
    }

    public void setNomeServ(String nomeServ) {
        this.nomeServ = nomeServ;
    }
}
