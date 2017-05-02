package com.simplecityapps.test.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

public class ViewUtils {

    private ViewUtils() {

    }

    public static void fadeIn(final View view, int duration) {

        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    public static void fadeOut(final View view, int duration, final int visibility) {

        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);
        objectAnimator.setDuration(duration);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(visibility);
                objectAnimator.removeAllListeners();
            }
        });
        objectAnimator.start();
    }
}