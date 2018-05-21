package com.example.juniorsantos.barberapp.model;

import com.example.juniorsantos.barberapp.DAO.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Spacecraft {

    String name;

    public Spacecraft() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}