package com.simarc86.limpiezasreig;

import java.io.Serializable;

/**
 * Created by marctamaritromero on 25/11/15.
 */
public class Product implements Serializable{
    private String name;
    private float price;
    private int quantity;
    private int stock;
    private boolean added;

    public Product(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
