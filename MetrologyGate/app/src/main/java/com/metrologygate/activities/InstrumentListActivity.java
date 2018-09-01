package com.metrologygate.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.metrologygate.R;
import com.metrologygate.adapters.InstrumentListAdapter;
import com.metrologygate.retrofit.ApiClient;
import com.metrologygate.retrofit.ApiInterface;
import com.metrologygate.utility.Constants;
import com.metrologygate.utility.PreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstrumentListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    InstrumentListAdapter adapter;
    LinearLayoutManager manager;
    ArrayList<String> instrumentsList;
    ProgressBar progressBar;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_list);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar);

        progressBar = findViewById(R.id.login_progress);

        instrumentsList = new ArrayList<>();
        new PreferenceManager(this);

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        getInstrumentListData();

    }

    private void getInstrumentListData() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<String>> call = apiInterface.getInstrumentsList(PreferenceManager.getPrefString(Constants.USER_NAME), PreferenceManager.getPrefString(Constants.PASSWORD));
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<String>> call, @NonNull Response<ArrayList<String>> response) {
                progressBar.setVisibility(View.GONE);
                Log.e("API", " " + response.code());
                Log.e("API", " " + response.raw());
                Log.e("API", " " + response.body());
                Log.e("API", " " + response.errorBody());
                Log.e("API", " " + response.message());
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter = new InstrumentListAdapter(InstrumentListActivity.this, response.body());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        })
        ;
    }
}

