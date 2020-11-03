package com.simplecityapps.navigation.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.simplecityapps.navigation.base.Controller;
import com.simplecityapps.navigation.base.NavigationController;

/**
 * An abstract implementation of {@link Controller}, which can be used as a base for {@link Fragment}s
 * who wish to be aware of their parent {@link NavigationController}
 */
public abstract class BaseController extends Fragment implements Controller {

    @Nullable
    @Override
    public NavigationController<BaseController> getNavigationController() {
        return findNavigationController(this);
    }

    /**
     * Traverses the fragment hierarchy searching for the first available {@link NavigationController}.
     * If none are found, then this method checks whether the parent {@link android.app.Activity} is
     * a {@link NavigationController} and returns that instead.
     *
     * @param fragment the fragment whose hierarchy will be searched.
     */
    @Nullable
    private static NavigationController<BaseController> findNavigationController(@NonNull Fragment fragment) {

        Fragment parent = fragment.getParentFragment();

        if (parent instanceof NavigationController) {
            return (NavigationController) parent;
        }

        if (parent != null) {
            return findNavigationController(parent);
        } else {
            if (fragment.getActivity() instanceof NavigationController) {
                return (NavigationController) fragment.getActivity();
            }
        }

        return null;
    }

}