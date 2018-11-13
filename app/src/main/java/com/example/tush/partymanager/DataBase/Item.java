package com.example.tush.partymanager.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.security.PrivateKey;

@Entity(tableName = "ITEM_TABLE",
        indices = {@Index(value = "FRAGMENT_NO")})
public class Item {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "ITEM_NAME")
    private String item_name;

    @NonNull
    @ColumnInfo(name = "ITEM_PRICE")
    private int item_price;

    @ColumnInfo(name = "ITEM_QUANTITY")
    private int item_quantity;

    @ColumnInfo(name = "FRAGMENT_NO")
    private int fragment_no;

    public Item() {
    }

    public Item(@NonNull String name, @NonNull int price, int quantity, int fragment_no) {
        item_name = name;
        item_price = price;
        item_quantity = quantity;
        this.fragment_no = fragment_no;
    }

    @Ignore
    public Item(int id, @NonNull String name, @NonNull int price, int quantity, int fragment_no) {
        this.id = id;
        item_name = name;
        item_price = price;
        item_quantity = quantity;
        this.fragment_no = fragment_no;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
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

    public void setFragment_no(int fragment_no) {
        this.fragment_no = fragment_no;
    }

    public int getFragment_no() {
        return fragment_no;
    }
}
