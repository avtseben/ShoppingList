package ru.alexandertsebenko.shoppinglist.datamodel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pack extends RealmObject{

    @PrimaryKey
    private String name;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
