package com.metrologygate.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.metrologygate.R;
import com.metrologygate.adapters.MachineAdapter;
import com.metrologygate.utility.PreferenceManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MachineAdapter adapter;
    LinearLayoutManager manager;
    ArrayList<String> instrumentsList;
    TextView tvToolbarTitle;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_list);

        recyclerView = findViewById(R.id.recycler_view);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Metrology Gate");

        instrumentsList = new ArrayList<>();
        instrumentsList.add("Machine Monitor");
        instrumentsList.add("Metrology Gate");
        instrumentsList.add("Favorites");
        new PreferenceManager(this);

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        recyclerView.setVisibility(View.VISIBLE);
        adapter = new MachineAdapter(MainActivity.this, instrumentsList);
        recyclerView.setAdapter(adapter);
    }
}
