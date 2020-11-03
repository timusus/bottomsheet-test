package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simplecityapps.navigation.fragment.BaseController;
import com.simplecityapps.recycler_adapter.adapter.ViewModelAdapter;
import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.activity.ToolbarListener;

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
        recyclerView.setAdapter(new ViewModelAdapter());

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
}
