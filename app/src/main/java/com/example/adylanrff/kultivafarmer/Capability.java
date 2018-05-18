package com.example.adylanrff.kultivafarmer;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Capability {
    private int id;
    private double volume;
    private Date startDate;
    private Date endDate;
    private int productId;
    private String productName;
    private String productImage;
    private String productPrice;

    public Capability(int id, double volume, Date startDate, Date endDate, int productId, String productName, String productImage, String productPrice) {
        this.id = id;
        this.volume = volume;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
