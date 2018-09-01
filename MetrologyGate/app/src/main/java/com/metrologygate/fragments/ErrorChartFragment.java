package com.metrologygate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.CartesianSeriesColumn;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.EnumsAnchor;
import com.anychart.anychart.HoverMode;
import com.anychart.anychart.Position;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;
import com.github.anastr.speedviewlib.SpeedView;
import com.metrologygate.R;
import com.metrologygate.activities.InstrumentDashboardActivity;
import com.metrologygate.models.InstrumentDashboard.InstrumentDashboard;
import com.metrologygate.retrofit.ApiClient;
import com.metrologygate.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Karan on 31/8/18.
 */
public class ErrorChartFragment  extends Fragment{

//    ProgressBar progressBar;
    SpeedView speedViewProbeTrigger, speedViewXAxis, speedViewYAxis, speedViewZAxis;
    String instName;
    Cartesian cartesian;
    CartesianSeriesColumn column;
    AnyChartView anyChartView;
    int collisionCount, emergencyPressedCount, genericErrorCount, workPieceNotFoundCount, cc;
    InstrumentDashboardActivity instrumentDashboardActivity;
    TextView textView;
    RelativeLayout rlChart;

    public ErrorChartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_error_chart, container, false);
        initUI(view);
        instrumentDashboardActivity = (InstrumentDashboardActivity) getActivity();
        getInstrumentDashboardData();

        cartesian = AnyChart.column();
        column = cartesian.column(getData());

        column.getTooltip()
                .setTitleFormat("{%X}")
                .setPosition(Position.CENTER_BOTTOM)
                .setAnchor(EnumsAnchor.CENTER_BOTTOM)
                .setOffsetX(0d)
                .setOffsetY(5d)
                .setFormat("{%Value}{groupsSeparator: }");

        cartesian.setAnimation(true);
        cartesian.setTitle("Faults");

        cartesian.getYScale().setMinimum(0d);

        cartesian.getYAxis(0).getLabels().setFormat("{%Value}{groupsSeparator: }");

        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

//        cartesian.getXAxis(0).setTitle("Product");
        cartesian.getYAxis(0).setTitle("Number of Faults");

        anyChartView.setChart(cartesian);
        return view;
    }

    private void initUI(View view) {

        anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));
        textView = view.findViewById(R.id.tv);
        rlChart = view.findViewById(R.id.rlChart);
    }

    private void getInstrumentDashboardData() {
//        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<InstrumentDashboard> call = apiInterface.getInstrumentsData(instrumentDashboardActivity.getMyData());
        call.enqueue(new Callback<InstrumentDashboard>() {
            @Override
            public void onResponse(@NonNull Call<InstrumentDashboard> call, @NonNull Response<InstrumentDashboard> response) {
//                progressBar.setVisibility(View.GONE);
                Log.e("API", " " + response.code());
                Log.e("API", " " + response.raw());
                Log.e("API", " " + response.body());
                Log.e("API", " " + response.errorBody());
                Log.e("API", " " + response.message());
                if (response.isSuccessful()) {
//                    progressBar.setVisibility(View.GONE);

                    assert response.body() != null;
                    assert response.body() != null;
                    Log.e("API", " " + response.body().getData());

                    if (response.body().getData().getErrors().size() > 10){
                        for (int i = 0; i< response.body().getData().getErrors().size(); i++){
                            if (response.body().getData().getErrors().get(i).getType() == 10){
                                collisionCount++;
                            }
                            if (response.body().getData().getErrors().get(i).getType() == 11){
                                emergencyPressedCount++;
                            }
                            if (response.body().getData().getErrors().get(i).getType() == 14){
                                genericErrorCount++;
                            }
                            if (response.body().getData().getErrors().get(i).getType() == 46){
                                workPieceNotFoundCount++;
                            }
                        }
//                        cc = collisionCount;
//                        Log.e("Karan", ""+ cc);
                    } else {
                        textView.setVisibility(View.VISIBLE);
                        rlChart.setVisibility(View.INVISIBLE);

                    }
                }
            }

            @Override
            public void onFailure(Call<InstrumentDashboard> call, Throwable t) {
                t.printStackTrace();
//                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public List<DataEntry> getData(){
        Log.e("KKKK", ""+ collisionCount);
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Collision", instrumentDashboardActivity.getCollisionCount()));
        data.add(new ValueDataEntry("Emergency Pressed", instrumentDashboardActivity.getEmergencyPressedCount()));
        data.add(new ValueDataEntry("Generic Error", instrumentDashboardActivity.getGenericErrorCount()));
//        data.add(new ValueDataEntry("Work Piece Not Found", workPieceNotFoundCount));

        return data;
    }
}
