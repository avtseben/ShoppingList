package ru.alexandertsebenko.shoppinglist.datamodel;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by avtseben on 16.07.2016.
 */
public class Price extends RealmObject{

    private int id;
    private ProductInstance productInstance;
    private Shop shop;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductInstance getProductInstance() {
        return productInstance;
    }

    public void setProductInstance(ProductInstance productInstance) {
        this.productInstance = productInstance;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
