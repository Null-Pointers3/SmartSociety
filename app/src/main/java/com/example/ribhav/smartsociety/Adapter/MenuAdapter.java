package com.example.ribhav.smartsociety.Adapter;

/*
 * Created by ribhav on 10/10/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ribhav.smartsociety.R;
import com.example.ribhav.smartsociety.ResourceClasses.MenuItem;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

        private ArrayList<MenuItem> menuItems;

        public MenuAdapter(ArrayList<MenuItem> menuItems){this.menuItems=menuItems; }

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Get the RecyclerView item layout
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);

            return new MenuViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, int position) {
            MenuItem menuItem=menuItems.get(position);
            holder.MenuImage.setImageResource(menuItem.getImageId());
        }

        @Override
        public int getItemCount() {
            return menuItems.size();
        }

        class MenuViewHolder extends RecyclerView.ViewHolder {
            private ImageView MenuImage;
            public MenuViewHolder(View itemView) {
                super(itemView);
                MenuImage=(ImageView) itemView.findViewById(R.id.MenuItemImage);

            }

        }
    }
