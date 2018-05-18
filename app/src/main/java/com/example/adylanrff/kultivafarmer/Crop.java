package com.example.adylanrff.kultivafarmer;
import com.google.gson.annotations.SerializedName;

public class Crop {
    private String name;
    private int price;
    private String unit;
    private int status;

    public Crop(String name, int price, String unit, int status) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}
