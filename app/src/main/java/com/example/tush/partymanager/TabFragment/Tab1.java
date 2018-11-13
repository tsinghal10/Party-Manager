package com.example.tush.partymanager.TabFragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tush.partymanager.DataBase.Item;
import com.example.tush.partymanager.DataBase.ItemViewModel;
import com.example.tush.partymanager.DialogBox;
import com.example.tush.partymanager.R;
import com.example.tush.partymanager.RVAdapter;
import com.example.tush.partymanager.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {
    List<Item> itemLists;
    RecyclerView rv;
    RVAdapter rva;
    private ItemViewModel itemViewModel;

    final static int fragmentNo = 1;

    public Tab1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_tab1, container, false);

        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);

        rv = rootview.findViewById(R.id.tab1rv);
        rva = new RVAdapter(itemLists, new RVAdapter.MyAdapterListener() {
            @Override
            public void plusOnClick(View v, int position) {
                Item item = rva.getItemPosition(position);
                item.setItem_quantity(item.getItem_quantity() + 1);
                itemViewModel.update(item);
            }

            @Override
            public void minusOnClick(View v, int position) {
                Item item = rva.getItemPosition(position);
                if (item.getItem_quantity() > 0) {
                    item.setItem_quantity(item.getItem_quantity() - 1);
                }
                itemViewModel.update(item);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL));
        rv.setAdapter(rva);
        rv.setItemAnimator(new DefaultItemAnimator());

        itemViewModel.getAllItems().observe(getActivity(), new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                List<Item> newItems = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getFragment_no() == fragmentNo)
                        newItems.add(items.get(i));
                }
                TextView total = rootview.findViewById(R.id.total1);
                int sum = 0;
                for (Item item : newItems) {
                    sum += item.getItem_price() * item.getItem_quantity();
                }
                total.setText(getResources().getString(R.string.total_amount_rs) + Integer.toString(sum));
                rva.setItems(newItems);
            }
        });

        final DialogBox dialogBox = new DialogBox(rv, rva, itemLists);

        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBox.showDialog(getContext(), false, null, -1, itemViewModel, fragmentNo);
            }
        });

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        dialogBox.showActionDialog(getContext(), position, itemViewModel, fragmentNo);
                    }
                })
        );

        return rootview;
    }
}
