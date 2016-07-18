package ru.alexandertsebenko.shoppinglist.datamodel;

import io.realm.RealmObject;

/**
 * Created by avtseben on 16.07.2016.
 */
public class Shop extends RealmObject{

    private int id;
    private String name;
    private String location;
    private int logoDrawableId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLogoDrawableId() {
        return logoDrawableId;
    }

    public void setLogoDrawableId(int logoDrawableId) {
        this.logoDrawableId = logoDrawableId;
    }
}
