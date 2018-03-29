package com.example.juniorsantos.barberapp.model;

public class Spacecraft {

    int id;
    String name;
    String proppelant;
    String desc;
    String imageUrl;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getProppelant() {
        return proppelant;
    }
    public void setProppelant(String proppelant) {
        this.proppelant = proppelant;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}