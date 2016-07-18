package ru.alexandertsebenko.shoppinglist.datamodel;

import io.realm.RealmObject;

public class ShoppingShedule extends RealmObject{

    private ProductInstance productInstance;
    private int quantity;
    private Shop shop;
    private int dateOfMonth;

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

    public int getDateOfMonth() {
        return dateOfMonth;
    }

    public void setDateOfMonth(int dateOfMonth) {
        this.dateOfMonth = dateOfMonth;
    }
}
