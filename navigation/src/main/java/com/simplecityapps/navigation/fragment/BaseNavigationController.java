package com.simplecityapps.navigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.simplecityapps.androidnavigation.R;
import com.simplecityapps.navigation.base.NavigationController;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * An abstract implementation of {@link NavigationController}. Subclasses need only provide a {@link FragmentInfo} object
 * which will be used to instantiate the root view controller.
 */
public abstract class BaseNavigationController extends Fragment
        implements NavigationController<BaseController> {

    private static final String TAG = "BaseNavigationControlle";

    public abstract FragmentInfo getRootViewControllerInfo();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            addRootFragment();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.navigation_fragment, container, false);
    }

    protected void addRootFragment() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.mainContainer, getRootViewControllerInfo().instantiateFragment(getContext()), getRootViewControllerInfo().rootViewControllerTag)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof BackPressHandler) {
            ((BackPressHandler) getActivity()).setBackPressListener(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (getActivity() instanceof BackPressHandler) {
            ((BackPressHandler) getActivity()).setBackPressListener(null);
        }
    }

    @Override
    public boolean consumeBackPress() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            popViewController();
            return true;
        }
        return false;
    }

    @Override
    public void pushViewController(@NonNull BaseController controller, @Nullable String tag, @Nullable List<Pair<View, String>> sharedElements) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager()
                .beginTransaction();

        if (sharedElements != null) {
            for (Pair<View, String> pair : sharedElements) {
                fragmentTransaction.addSharedElement(pair.first, pair.second);
            }
        }

        fragmentTransaction.addToBackStack(null)
                .replace(R.id.mainContainer, controller, tag)
                .commit();
    }

    @Override
    public void pushViewController(@NonNull BaseController controller, @Nullable String tag) {
        pushViewController(controller, tag, null);
    }

    @Override
    public void popViewController() {
        getChildFragmentManager().popBackStack();
    }

    @Override
    public void popToRootViewController() {
        getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}