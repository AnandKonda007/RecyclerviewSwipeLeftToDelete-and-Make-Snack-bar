package com.example.recyclerviewwithswipelefttodeleteandlongpresstodelete;
public class contact_model {
    int img;
    String name, number;

    public contact_model(int img, String name, String number) {
        this.name = name;
        this.number = number;
        this.img = img;
    }

    public contact_model(String name, String number) {
        this.name = name;
        this.number = number;

    }
}
