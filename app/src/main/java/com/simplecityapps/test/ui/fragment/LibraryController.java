package com.simplecityapps.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simplecityapps.navigation.fragment.BaseController;
import com.simplecityapps.navigation.fragment.FragmentInfo;
import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.activity.ToolbarListener;
import com.simplecityapps.test.ui.adapter.BaseItemAdapter;
import com.simplecityapps.test.ui.view.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LibraryController extends BaseController {

    public interface OtherClickListener {
        void onItemClick(String transitionName, List<Pair<View, String>> sharedElements);
    }

    private static final String TAG = "LibraryController";

    private int prevOffset = 0;

    public static FragmentInfo fragmentInfo() {
        return new FragmentInfo(LibraryController.class, null, "LibraryController");
    }

    public LibraryController() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_library, container, false);

        final Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ViewCompat.setTransitionName(toolbar, "toolbar");

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new ItemAdapter(new ItemViewHolder.ItemClickListener() {
            @Override
            public void itemClicked(View v, int position) {

                ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
                String imageTransitionName = ViewCompat.getTransitionName(imageView);

                List<Pair<View, String>> sharedElements = new ArrayList<>();
                sharedElements.add(new Pair<View, String>(imageView, imageTransitionName));
                sharedElements.add(new Pair<View, String>(toolbar, "toolbar"));

                BaseController controller = DetailController.newInstance(imageTransitionName);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Transition moveTransition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move);
                    controller.setSharedElementEnterTransition(moveTransition);
                    controller.setSharedElementReturnTransition(moveTransition);
                }

                getNavigationController().pushViewController(controller, "DetailController", sharedElements);
            }
        }));

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
            return "LibraryAdapter";
        }
    }
}