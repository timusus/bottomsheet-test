package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplecityapps.navigation.fragment.BaseController;
import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.adapter.BaseItemAdapter;
import com.simplecityapps.test.ui.view.ItemViewHolder;

public class Sheet2Controller extends BaseController {

    public static Sheet2Controller newInstance() {

        Bundle args = new Bundle();
        Sheet2Controller fragment = new Sheet2Controller();
        fragment.setArguments(args);
        return fragment;
    }

    public Sheet2Controller() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sheet2, container, false);

        // FIXME:
        // This RecyclerView (when used in conjunction with a click listener on the 2nd peek view, defined in `MultiSheetView`
        // causes the 2nd peek view to stop responding to drag events. Remove here and in xml to fix.
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(new ItemViewHolder.ItemClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                //Nothing to do
            }
        }));

        return rootView;
    }

    private static class ItemAdapter extends BaseItemAdapter {

        ItemAdapter(ItemViewHolder.ItemClickListener itemClickListener) {
            super(itemClickListener);
        }

        @Override
        public String getTransitionPrefix() {
            return "QueueAdapter";
        }
    }
}
