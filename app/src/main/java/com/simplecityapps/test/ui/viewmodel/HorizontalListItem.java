package com.simplecityapps.test.ui.viewmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecityapps.recycler_adapter.model.BaseViewModel;
import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;
import com.simplecityapps.test.R;

public class HorizontalListItem extends BaseViewModel<HorizontalListItem.ViewHolder> {

    String string;

    public HorizontalListItem(String string) {
        this.string = string;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.list_item_horizontal;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup viewGroup) {
        return new ViewHolder(createView(viewGroup));
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);

        ((TextView) holder.itemView).setText(string);
    }

    static class ViewHolder extends BaseViewHolder<HorizontalListItem> {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
