package com.example.tush.partymanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tush.partymanager.DataBase.Item;
import com.example.tush.partymanager.DataBase.ItemViewModel;

import java.util.List;

public class DialogBox {
    RecyclerView rv;
    RVAdapter rva;
    List<Item> itemLists;

    public DialogBox(RecyclerView rv, RVAdapter rva, List<Item> itemLists) {
        this.rv = rv;
        this.rva = rva;
        this.itemLists = itemLists;
    }

    public void showActionDialog(final Context context, final int position, final ItemViewModel itemViewModel, final int fragment_no) {
        CharSequence option[] = new CharSequence[]{"Edit", "Delete"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("Choose Option");
        alertBuilder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {                    //update
                    Item item = rva.getItemPosition(position);
                    showDialog(context, true, item, position, itemViewModel, fragment_no);
                } else {                            //delete
                    Item item = rva.getItemPosition(position);
                    itemViewModel.delete(item);
                }
            }
        });
        alertBuilder.show();
    }

    public void showDialog(final Context context, final boolean shouldUpdate, final Item item, final int position,
                           final ItemViewModel itemViewModel, final int fragment_no) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View view = layoutInflaterAndroid.inflate(R.layout.item_dialog, null);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(view);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        final EditText itemName = view.findViewById(R.id.item_name);
        final EditText itemPrice = view.findViewById(R.id.item_price);

        dialogTitle.setText(!shouldUpdate ? "Enter New Item" : "Update Item");

        if (shouldUpdate && item != null) {
            itemName.setText(item.getItem_name());
            itemPrice.setText(Integer.toString(item.getItem_price()));
        }

        alertDialog
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });
        final AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();

        alertDialog1.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(itemName.getText()) || TextUtils.isEmpty(itemPrice.getText())) {
                    Toast.makeText(context, "Enter Item Details", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog1.dismiss();
                }

                String name = itemName.getText().toString();
                int price = Integer.parseInt(itemPrice.getText().toString());

                if (shouldUpdate && item != null) {         //update
                    Item object = rva.getItemPosition(position);
                    object.setFragment_no(item.getFragment_no());
                    object.setId(item.getId());
                    object.setItem_name(name);
                    object.setItem_price(price);
                    object.setItem_quantity(item.getItem_quantity());
                    itemViewModel.update(object);

                } else {                                    //insert
                    Item object = new Item(name, price, 0, fragment_no);
                    itemViewModel.insert(object);
                }
            }
        });
    }


}
