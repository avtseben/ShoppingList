package ru.alexandertsebenko.shoppinglist.datamodel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductCategory extends RealmObject{

    @PrimaryKey
    private String name;
    private String storage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
