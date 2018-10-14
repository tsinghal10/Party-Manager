package com.example.tush.partymanager.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ITEM_TABLE")
public class Item {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ITEM_NAME")
    private String item_name;

    @ColumnInfo(name = "ITEM_PRICE")
    private int item_price;

    @ColumnInfo(name = "ITEM_QUANTITY")
    private int item_quantity;

    public Item(){}

    public Item(@NonNull String name, int price, int quanity) {
        item_name = name;
        item_price = price;
        item_quantity = quanity;
    }

    @NonNull
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(@NonNull String name) {
        item_name = name;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }
}
