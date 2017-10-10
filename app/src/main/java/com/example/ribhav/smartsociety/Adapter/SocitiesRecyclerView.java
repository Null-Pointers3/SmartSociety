package com.example.ribhav.smartsociety.Adapter;

/*
 * Created by ribhav on 10/10/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ribhav.smartsociety.R;

public class SocitiesRecyclerView {
    public class SocietyAdapter extends RecyclerView.Adapter<SocietyAdapter.SocietyViewHolder> {
        private Context mContext;

        public SocietyAdapter(Context context) {
            this.mContext = context;

        }

        @Override
        public SocietyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Get the RecyclerView item layout
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new SocietyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SocietyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class SocietyViewHolder extends RecyclerView.ViewHolder {

            public SocietyViewHolder(View itemView) {
                super(itemView);

            }

        }
    }
}
