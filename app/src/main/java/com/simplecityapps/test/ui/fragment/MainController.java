package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.simplecityapps.navigation.fragment.BaseNavigationController;
import com.simplecityapps.navigation.fragment.FragmentInfo;
import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.view.MultiSheetView;

public class MainController extends BaseNavigationController {

    private static final String TAG = "MainController";

    private MultiSheetView multiSheetView;

    public static MainController newInstance() {
        Bundle args = new Bundle();
        MainController fragment = new MainController();
        fragment.setArguments(args);
        return fragment;
    }

    public MainController() {

    }

    @Override
    public FragmentInfo getRootViewControllerInfo() {
        return LibraryController.fragmentInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        multiSheetView = (MultiSheetView) inflater.inflate(R.layout.multi_sheet, container, false);

        if (savedInstanceState == null) {

            getChildFragmentManager()
                    .beginTransaction()
                    .add(multiSheetView.getSheet1ContainerResId(), Sheet1Controller.newInstance())
                    .add(multiSheetView.getSheet2ContainerResId(), Sheet2Controller.newInstance())
                    .commit();
        }

        return multiSheetView;
    }

    @Override
    public boolean consumeBackPress() {

        if (multiSheetView.consumeBackPress()) {
            return true;
        }

        return super.consumeBackPress();
    }
}