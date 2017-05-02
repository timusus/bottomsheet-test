package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simplecityapps.navigation.fragment.BaseController;
import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.activity.ToolbarListener;
import com.simplecityapps.test.ui.adapter.BaseItemAdapter;
import com.simplecityapps.test.ui.view.ItemViewHolder;

public class DetailController extends BaseController {

    public static final String ARG_TRANSITION_NAME = "transition_name";

    public static DetailController newInstance(String transitionName) {

        Bundle args = new Bundle();
        args.putString(ARG_TRANSITION_NAME, transitionName);
        DetailController fragment = new DetailController();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailController() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(new ItemViewHolder.ItemClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                //Nothing to do
            }
        }));

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ViewCompat.setTransitionName(toolbar, "toolbar");

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        ViewCompat.setTransitionName(imageView, getArguments().getString(ARG_TRANSITION_NAME));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() instanceof ToolbarListener) {
            ((ToolbarListener) getActivity()).toolbarAttached((Toolbar) view.findViewById(R.id.toolbar));
        }
    }

    private static class ItemAdapter extends BaseItemAdapter {

        ItemAdapter(ItemViewHolder.ItemClickListener itemClickListener) {
            super(itemClickListener);
        }

        @Override
        public String getTransitionPrefix() {
            return "DetailAdapter";
        }
    }
}
