package com.example.juniorsantos.barberapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Junior Santos on 20/08/2017.
 */



public class Usuario implements Parcelable{

    private int idUsuario;
    private String nome;
    private String serv;
    private String barbeiro;

    public Usuario() {
    }

    protected Usuario(Parcel in) {
        idUsuario = in.readInt();
        nome = in.readString();
        serv = in.readString();
        barbeiro = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }



    public String getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(String barbeiro) {
        this.barbeiro = barbeiro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(idUsuario);
        parcel.writeString(nome);
        parcel.writeString(serv);
        parcel.writeString(barbeiro);
    }
}
