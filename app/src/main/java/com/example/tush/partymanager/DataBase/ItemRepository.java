package com.example.tush.partymanager.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    private LiveData<List<Item>> itemList;

    ItemRepository(Application application) {
        ItemRoom db = ItemRoom.getDatabase(application);
        itemDao = db.itemDao();
        itemList = itemDao.getAllItems();
    }

    LiveData<List<Item>> getAllItems() {
        return itemList;
    }

    public void insert(Item item) {
        new insertAsyncTask(itemDao).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao mAsyncTaskDao;

        insertAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            mAsyncTaskDao.insert(items[0]);
            return null;
        }
    }

    public void deleteAll() {
        new deleteAllAsyncTask(itemDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao mAsyncTaskDao;

        deleteAllAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void delete(Item item) {
        new deleteAsyncTask(itemDao).execute(item);
    }

    private static class deleteAsyncTask extends AsyncTask<Item, Void, Void> {
        private ItemDao mAsyncTaskDao;

        deleteAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void update(Item item) {

    }

}
