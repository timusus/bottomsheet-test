package com.simplecityapps.test.ui.adapter;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.view.ItemViewHolder;

public abstract class BaseItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    /**
     * @return a unique String identifying this adapter. Used for transitions.
     */
    public abstract String getTransitionPrefix();

    ItemViewHolder.ItemClickListener itemClickListener;

    public BaseItemAdapter(ItemViewHolder.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false), new ItemViewHolder.ItemClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                itemClickListener.itemClicked(v, position);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(String.format("Item %d", position + 1));

        ViewCompat.setTransitionName(holder.imageView, String.format("%S_image_%d", getTransitionPrefix(), position));
    }

    @Override
    public int getItemCount() {
        return 250;
    }
}