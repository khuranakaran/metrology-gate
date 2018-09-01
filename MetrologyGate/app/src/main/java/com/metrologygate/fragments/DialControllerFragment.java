package com.metrologygate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.anastr.speedviewlib.SpeedView;
import com.metrologygate.R;
import com.metrologygate.activities.InstrumentDashboardActivity;
import com.metrologygate.models.InstrumentDashboard.InstrumentDashboard;
import com.metrologygate.retrofit.ApiClient;
import com.metrologygate.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Karan on 31/8/18.
 */
public class DialControllerFragment extends Fragment{

    ProgressBar progressBar;
    SpeedView speedViewProbeTrigger, speedViewXAxis, speedViewYAxis, speedViewZAxis;
    InstrumentDashboardActivity instrumentDashboardActivity;

    public DialControllerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dial_controller, container, false);

        instrumentDashboardActivity = (InstrumentDashboardActivity) getActivity();
//        instName = getArguments().getString("edttext");
//        Log.e("KKKKKKKKKK", ""+instName);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        progressBar = view.findViewById(R.id.instrument_dashboard_progress);
        speedViewProbeTrigger = view.findViewById(R.id.speedViewProbeTrigger);
        speedViewXAxis = view.findViewById(R.id.speedViewXAxis);
        speedViewYAxis = view.findViewById(R.id.speedViewYAxis);
        speedViewZAxis = view.findViewById(R.id.speedViewZAxis);

        progressBar.setVisibility(View.VISIBLE);

        getInstrumentDashboardData();
    }

    private void getInstrumentDashboardData() {
        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<InstrumentDashboard> call = apiInterface.getInstrumentsData(instrumentDashboardActivity.getMyData());
        call.enqueue(new Callback<InstrumentDashboard>() {
            @Override
            public void onResponse(@NonNull Call<InstrumentDashboard> call, @NonNull Response<InstrumentDashboard> response) {
                progressBar.setVisibility(View.GONE);
                Log.e("API", " " + response.code());
                Log.e("API", " " + response.raw());
                Log.e("API", " " + response.body());
                Log.e("API", " " + response.errorBody());
                Log.e("API", " " + response.message());
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    assert response.body() != null;
                    assert response.body() != null;
                    Log.e("API", " " + response.body().getData());
                    if (response.body().getData().getAxes().size() > 0) {
                        Log.e("API", " " + "Yess"+ response.body().getData().getAxes().get(0).getDistance());
                        speedViewXAxis.speedTo((response.body().getData().getAxes().get(0).getDistance()).floatValue());
                        speedViewYAxis.speedTo((response.body().getData().getAxes().get(1).getDistance()).floatValue());
                        speedViewZAxis.speedTo((response.body().getData().getAxes().get(2).getDistance()).floatValue());
                    }

                    if (response.body().getData().getProbeTriggers() > 0) {
                        speedViewProbeTrigger.speedTo((response.body().getData().getProbeTriggers()).floatValue());
                    }
                }
            }

            @Override
            public void onFailure(Call<InstrumentDashboard> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
