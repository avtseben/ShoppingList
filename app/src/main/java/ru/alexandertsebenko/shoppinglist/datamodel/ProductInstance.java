package ru.alexandertsebenko.shoppinglist.datamodel;

import io.realm.RealmObject;

public class ProductInstance extends RealmObject{

    private int id;
    private Product product;
    private Pack pack;
    private Brand brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Pack getPack() {
        return pack;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }
}
