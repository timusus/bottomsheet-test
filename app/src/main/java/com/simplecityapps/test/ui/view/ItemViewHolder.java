package com.simplecityapps.test.ui.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplecityapps.test.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public interface ItemClickListener {
        void itemClicked(View v, int position);
    }

    public ItemClickListener listener;
    public TextView textView;
    public ImageView imageView;

    public ItemViewHolder(View itemView, ItemClickListener listener) {
        super(itemView);

        this.listener = listener;

        textView = (TextView) itemView.findViewById(R.id.textView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.itemClicked(v, getAdapterPosition());
    }

}