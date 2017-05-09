package com.simplecityapps.test.ui.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.simplecityapps.test.R;
import com.simplecityapps.test.ui.behavior.CustomBottomSheetBehavior;

public class MultiSheetView extends CoordinatorLayout {

    private static final String TAG = "MultiSheetView";

    @interface Sheet {
        int NONE = 0;
        int FIRST = 1;
        int SECOND = 2;
    }

    private CustomBottomSheetBehavior bottomSheetBehavior1;
    private CustomBottomSheetBehavior bottomSheetBehavior2;

    public MultiSheetView(Context context) {
        this(context, null);
    }

    public MultiSheetView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiSheetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View sheet1 = findViewById(R.id.sheet1);
        bottomSheetBehavior1 = (CustomBottomSheetBehavior) BottomSheetBehavior.from(sheet1);

        View sheet2 = findViewById(R.id.sheet2);
        bottomSheetBehavior2 = (CustomBottomSheetBehavior) BottomSheetBehavior.from(sheet2);
        bottomSheetBehavior2.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED || newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior1.setAllowDragging(false);
                } else {
                    bottomSheetBehavior1.setAllowDragging(true);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                bottomSheetBehavior1.setAllowDragging(false);
            }
        });

        //First sheet view click listener
        findViewById(getSheet1PeekViewResId()).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        // FIXME:
        // This click listener (combined with a nested RecyclerView in Sheet 2's container), causes
        // the second peek view to stop responding to drag events.
        // See `Sheet2Controller`. Remove this ClickListener here to see things working as they should.

        //Second sheet view click listener
        findViewById(getSheet2PeekViewResId()).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        // FIXED:
        // issue was  bottomSheetBehavior1 is taking drag event when getSheet2PeekView is dragging
        // so detect touch event  getSheet2PeekView set bottomSheetBehavior1 dragging false and bottomSheetBehavior2 true
        findViewById(getSheet2PeekViewResId()).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "onTouch: ");
                bottomSheetBehavior1.setAllowDragging(false);
                bottomSheetBehavior2.setAllowDragging(true);
                return false;
            }
        });
    }

    @Sheet
    public int getCurrentSheet() {
        if (bottomSheetBehavior2.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            return Sheet.SECOND;
        } else if (bottomSheetBehavior1.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            return Sheet.FIRST;
        } else {
            return Sheet.NONE;
        }
    }

    public void showSheet(@Sheet int sheet) {

        // if we are already at our target panel, then don't do anything
        if (sheet == getCurrentSheet()) {
            return;
        }

        switch (sheet) {
            case Sheet.NONE:
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case Sheet.FIRST:
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case Sheet.SECOND:
                bottomSheetBehavior2.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
        }
    }

    public boolean consumeBackPress() {
        switch (getCurrentSheet()) {
            case Sheet.SECOND:
                showSheet(Sheet.FIRST);
                return true;
            case Sheet.FIRST:
                showSheet(Sheet.NONE);
                return true;
            case Sheet.NONE:
                break;
        }
        return false;
    }

    @IdRes
    public int getMainContainerResId() {
        return R.id.mainContainer;
    }

    @IdRes
    public int getSheet1ContainerResId() {
        return R.id.sheet1Container;
    }

    @IdRes
    public int getSheet2ContainerResId() {
        return R.id.sheet2Container;
    }

    @IdRes
    public int getSheet1PeekViewResId() {
        return R.id.sheet1PeekView;
    }

    @IdRes
    public int getSheet2PeekViewResId() {
        return R.id.sheet2PeekView;
    }
}
