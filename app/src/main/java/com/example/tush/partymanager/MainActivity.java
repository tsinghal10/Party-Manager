package com.example.tush.partymanager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.example.tush.partymanager.TabFragment.TabsPageAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager view = findViewById(R.id.viewpager);
        view.setAdapter(new TabsPageAdapter(getSupportFragmentManager()));

        TabLayout tab = (TabLayout) findViewById(R.id.tabs);

        for (int i = 0; i < 3; i++) {
            tab.addTab(tab.newTab().setText("Tab" + (i + 1)));
        }

        view.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        view.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
    }

}
