package com.simplecityapps.test.ui.viewmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecityapps.recycler_adapter.model.BaseViewModel;
import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;
import com.simplecityapps.test.R;

public class VerticalListItem extends BaseViewModel<VerticalListItem.ViewHolder> {

    String string;

    public VerticalListItem(String string) {
        this.string = string;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.list_item_vertical;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup viewGroup) {
        return new ViewHolder(createView(viewGroup));
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);

        holder.textView.setText(string);
    }

    static class ViewHolder extends BaseViewHolder<VerticalListItem> {

        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}