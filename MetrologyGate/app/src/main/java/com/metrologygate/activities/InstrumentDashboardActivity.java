package com.metrologygate.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.metrologygate.fragments.DialControllerFragment;
import com.metrologygate.R;
import com.metrologygate.fragments.ErrorChartFragment;
import com.metrologygate.fragments.ErrorTableFragment;
import com.metrologygate.fragments.StatusFragment;
import com.metrologygate.models.InstrumentDashboard.Error;
import com.metrologygate.models.InstrumentDashboard.InstrumentDashboard;
import com.metrologygate.retrofit.ApiClient;
import com.metrologygate.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstrumentDashboardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String instName;
    List<Error> errors;
    int collisionCount, emergencyPressedCount, genericErrorCount, workPieceNotFoundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_dashboard);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        instName = getIntent().getStringExtra("inst_name");

        getInstrumentDashboardData();

        Bundle bundle = new Bundle();
        bundle.putString("edttext", instName);
        DialControllerFragment fragobj = new DialControllerFragment();
        fragobj.setArguments(bundle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StatusFragment(), "Status");
        adapter.addFragment(new DialControllerFragment(), "Counters");
        adapter.addFragment(new ErrorChartFragment(), "Errors");
        adapter.addFragment(new ErrorTableFragment(), "Logs");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public String getMyData() {
        return instName;
    }

    public List<Error> getErrors(){
        return errors;
    }

    public int getCollisionCount(){
        return collisionCount;
    }

    public int getEmergencyPressedCount(){
        return emergencyPressedCount;
    }

    public int getGenericErrorCount(){
        return genericErrorCount;
    }

    public int getWorkPieceNotFoundCount(){
        return workPieceNotFoundCount;
    }

    private void getInstrumentDashboardData() {
//        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<InstrumentDashboard> call = apiInterface.getInstrumentsData(instName);
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


                    if (response.body().getData().getErrors().size() > 10){
//                        init(response.body().getData().getErrors());
                        Log.e("KKKKKK", " " + response.body().getData());
                        errors = response.body().getData().getErrors();
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
}