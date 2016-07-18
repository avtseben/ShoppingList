package ru.alexandertsebenko.shoppinglist.datamodel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {

    @PrimaryKey
    private String name;
    private ProductCategory category;

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

}
