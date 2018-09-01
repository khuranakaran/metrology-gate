package com.metrologygate.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metrologygate.R;
import com.metrologygate.activities.InstrumentDashboardActivity;
import com.metrologygate.activities.InstrumentListActivity;
import com.metrologygate.activities.MainActivity;

import java.util.ArrayList;

    public class InstrumentListAdapter extends RecyclerView.Adapter<InstrumentListAdapter.InstrumentListViewHolder> {
        Context context;
        ArrayList<String> instrumentList;

        public InstrumentListAdapter(Context context, ArrayList<String> instrumentList) {
            this.context = context;
            this.instrumentList = instrumentList;
        }

        @NonNull
        @Override
        public InstrumentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instrument, parent, false);
            return new InstrumentListAdapter.InstrumentListViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final InstrumentListViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.instName.setText(instrumentList.get(position));
            holder.instName.setText(instrumentList.get(position));
            if (context instanceof InstrumentListActivity) {
//                if (position > 0) {
//                    holder.instName.setAlpha((float) .5);
//                }
                holder.instName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (holder.instName.getAlpha() > .5) {
                            context.startActivity(new Intent(context, InstrumentDashboardActivity.class).putExtra("inst_name", instrumentList.get(position)));
//                        }

                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return instrumentList.size();
        }

        public static class InstrumentListViewHolder extends RecyclerView.ViewHolder {
            TextView instName;

            public InstrumentListViewHolder(View itemView) {
                super(itemView);
                instName = itemView.findViewById(R.id.inst_name);
            }
        }
    }

