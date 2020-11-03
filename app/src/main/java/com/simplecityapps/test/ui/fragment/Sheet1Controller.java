package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.simplecityapps.navigation.fragment.BaseController;
import com.simplecityapps.recycler_adapter.adapter.ViewModelAdapter;
import com.simplecityapps.recycler_adapter.model.ViewModel;
import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.viewmodel.HorizontalListItem;

import java.util.ArrayList;
import java.util.List;


public class Sheet1Controller extends BaseController {

    public static Sheet1Controller newInstance() {

        Bundle args = new Bundle();

        Sheet1Controller fragment = new Sheet1Controller();
        fragment.setArguments(args);
        return fragment;
    }


    public Sheet1Controller() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sheet1, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        ViewModelAdapter adapter = new ViewModelAdapter();
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        List<ViewModel> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(new HorizontalListItem(String.format("Item %d", i)));
        }
        adapter.setItems(items);

        return rootView;
    }
}
