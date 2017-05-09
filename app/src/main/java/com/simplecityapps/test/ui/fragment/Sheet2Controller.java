package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplecityapps.navigation.fragment.BaseController;
import com.simplecityapps.recycler_adapter.adapter.ViewModelAdapter;
import com.simplecityapps.recycler_adapter.model.ViewModel;
import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.viewmodel.VerticalListItem;

import java.util.ArrayList;
import java.util.List;

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

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ViewModelAdapter adapter = new ViewModelAdapter();
        recyclerView.setAdapter(adapter);

        List<ViewModel> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(new VerticalListItem(String.format("Item %d", i)));
        }
        adapter.setItems(items);

        return rootView;
    }
}
