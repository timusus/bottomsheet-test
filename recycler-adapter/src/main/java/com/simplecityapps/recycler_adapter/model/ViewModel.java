package com.simplecityapps.recycler_adapter.model;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public interface ViewModel<VH extends RecyclerView.ViewHolder> extends ContentsComparator {

    int getViewType();

    void bindView(VH holder);

    void bindView(VH holder, int position, List payloads);

    VH createViewHolder(ViewGroup parent);

    int getSpanSize(int spanCount);
}