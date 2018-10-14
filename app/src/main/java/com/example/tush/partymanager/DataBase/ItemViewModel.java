package com.example.tush.partymanager.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository itemRepository;
    private LiveData<List<Item>> itemList;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        itemList = itemRepository.getAllItems();
    }

    public LiveData<List<Item>> getAllItems() {
        return itemList;
    }

    public void insert(Item item) {
        itemRepository.insert(item);
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }

    public void update(Item item) {
        itemRepository.update(item);
    }
}
