package ru.alexandertsebenko.shoppinglist.datamodel;

import java.util.Date;

import io.realm.RealmObject;

public class ShoppingInstance extends RealmObject{

    private ProductInstance productInstance;
    private int quantity;
    private Shop shop;
    private Date date;
    private boolean inBasket;
    private boolean bougth;

    public ProductInstance getProductInstance() {
        return productInstance;
    }

    public void setProductInstance(ProductInstance productInstance) {
        this.productInstance = productInstance;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public boolean isInBasket() {
        return inBasket;
    }

    public void setInBasket(boolean inBasket) {
        this.inBasket = inBasket;
    }

    public boolean isBougth() {
        return bougth;
    }

    public void setBougth(boolean bougth) {
        this.bougth = bougth;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
