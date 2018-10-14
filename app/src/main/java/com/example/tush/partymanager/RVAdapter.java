package com.example.tush.partymanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

import com.example.tush.partymanager.DataBase.Item;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Viewholder> {
    private List<Item> itemLists;

    public RVAdapter(List<Item> itemLists) {
        this.itemLists = itemLists;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder Viewholder, int i) {
        if (itemLists != null) {
            Viewholder.item.setText(itemLists.get(i).getItem_name());
            Viewholder.price.setText("Rs. " + Integer.toString(itemLists.get(i).getItem_price()));
            Viewholder.quantity.setText(Integer.toString(itemLists.get(i).getItem_quantity()));
        } else {
            Viewholder.item.setText("No Item");
            Viewholder.price.setText("Rs. 0");
            Viewholder.quantity.setText("0");
        }
    }

    public void setItems(List<Item> items) {
        itemLists = items;
        notifyDataSetChanged();
    }

    public Item getItemPosition(int position) {
        return itemLists.get(position);
    }

    @Override
    public int getItemCount() {

        if (itemLists != null)
            return itemLists.size();
        return 0;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView item, price, quantity;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
        }


    }
}
