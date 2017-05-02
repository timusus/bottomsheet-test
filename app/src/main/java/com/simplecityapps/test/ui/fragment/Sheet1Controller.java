package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplecityapps.navigation.fragment.BaseController;
import com.simplecityapps.test.R;


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

        return rootView;
    }
}
