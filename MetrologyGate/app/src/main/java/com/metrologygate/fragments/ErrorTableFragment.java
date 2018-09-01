package com.metrologygate.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.metrologygate.R;
import com.metrologygate.activities.InstrumentDashboardActivity;
import com.metrologygate.utility.Constants;

public class ErrorTableFragment extends Fragment {
    InstrumentDashboardActivity instrumentDashboardActivity;
    TableLayout stk;
    TextView tvNoErrorLogs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_error_table, container, false);
         stk = view.findViewById(R.id.table_main);
         tvNoErrorLogs = view.findViewById(R.id.tv_no_error_log);
        instrumentDashboardActivity = (InstrumentDashboardActivity) getActivity();
        init();
        return view;
    }

    public void init() {

        if (instrumentDashboardActivity.getErrors().size() > 10){
            TableRow tbrow0 = new TableRow(getActivity());
            TextView tv0 = new TextView(getActivity());
            tv0.setText(" ID ");
            tv0.setTextColor(Color.WHITE);
            tbrow0.addView(tv0);
            TextView tv1 = new TextView(getActivity());
            tv1.setText(" Type ");
            tv1.setTextColor(Color.WHITE);
            tbrow0.addView(tv1);
            TextView tv2 = new TextView(getActivity());
            tv2.setText(" Date ");
            tv2.setTextColor(Color.WHITE);
            tbrow0.addView(tv2);
//        TextView tv3 = new TextView(getActivity());
//        tv3.setText(" Stock Remaining ");
//        tv3.setTextColor(Color.WHITE);
//        tbrow0.addView(tv3);
            stk.addView(tbrow0);
            for (int i = 0; i < instrumentDashboardActivity.getErrors().size(); i++) {
                Log.e("SIZE", " " + instrumentDashboardActivity.getErrors().size());
                TableRow tbrow = new TableRow(getActivity());
                TextView t1v = new TextView(getActivity());
                t1v.setText(String.valueOf(instrumentDashboardActivity.getErrors().get(i).getId()));
                t1v.setTextColor(Color.WHITE);
                t1v.setGravity(Gravity.START);
                tbrow.addView(t1v);
                TextView t2v = new TextView(getActivity());
                t2v.setText(Constants.getTypeName(instrumentDashboardActivity.getErrors().get(i).getType()));
                t2v.setTextColor(Color.WHITE);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);
                TextView t3v = new TextView(getActivity());
                t3v.setText(instrumentDashboardActivity.getErrors().get(i).getFormattedDate());
                t3v.setTextColor(Color.WHITE);
                t3v.setGravity(Gravity.END);
                tbrow.addView(t3v);
                TextView t4v = new TextView(getActivity());
//            t4v.setText("" + i * 15 / 32 * 10);
//            t4v.setTextColor(Color.WHITE);
//            t4v.setGravity(Gravity.CENTER);
//            tbrow.addView(t4v);
                stk.addView(tbrow);
            }
        } else {
            tvNoErrorLogs.setVisibility(View.VISIBLE);
            stk.setVisibility(View.GONE);
        }
    }
}



