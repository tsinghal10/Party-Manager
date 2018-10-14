package com.example.tush.partymanager.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Query("DELETE FROM ITEM_TABLE")
    void deleteAll();

    @Query("SELECT * FROM ITEM_TABLE")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM ITEM_TABLE LIMIT 1")
    Item[] getAnyItem();

    @Delete
    void delete(Item item);

    @Update
    void update(Item item);
}

