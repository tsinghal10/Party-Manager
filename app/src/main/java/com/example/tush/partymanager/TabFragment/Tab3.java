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

import com.example.tush.partymanager.DataBase.Item;
import com.example.tush.partymanager.DataBase.ItemViewModel;
import com.example.tush.partymanager.DialogBox;
import com.example.tush.partymanager.R;
import com.example.tush.partymanager.RVAdapter;
import com.example.tush.partymanager.RecyclerItemClickListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3 extends Fragment {
    List<Item> itemLists;
    RecyclerView rv;
    RVAdapter rva;
    ItemViewModel itemViewModel;

    public Tab3() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_tab3, container, false);

        RecyclerView rv = rootview.findViewById(R.id.tab3rv);
        rva = new RVAdapter(itemLists);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(rva);
        rv.setItemAnimator(new DefaultItemAnimator());

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        itemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                rva.setItems(items);
            }
        });

        final DialogBox dialogBox = new DialogBox(rv, rva, itemLists);

        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBox.showDialog(getContext(), false, null, -1, itemViewModel);
            }
        });

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        dialogBox.showActionDialog(getContext(), position, itemViewModel);
                    }
                })
        );


        return rootview;
    }

}
