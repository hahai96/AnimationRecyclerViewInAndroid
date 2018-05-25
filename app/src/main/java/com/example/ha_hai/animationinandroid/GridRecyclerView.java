package com.example.ha_hai.animationinandroid;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;

/**
 * Created by ha_hai on 5/25/2018.
 */

public class GridRecyclerView extends RecyclerView {
    public GridRecyclerView(Context context) {
        super(context);
    }

    public GridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GridRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params, int index, int count) {
        LayoutManager layoutManager = getLayoutManager();
        if (getAdapter() != null && layoutManager instanceof GridLayoutManager) {
            GridLayoutAnimationController.AnimationParameters animationController =
                    (GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;

            if (animationController == null) {
                animationController = new GridLayoutAnimationController.AnimationParameters();
                params.layoutAnimationParameters = animationController;
            }

            animationController.count = count;
            animationController.index = index;

            int columns = ((GridLayoutManager) layoutManager).getSpanCount();
            animationController.columnsCount = columns;
            animationController.rowsCount = count / columns;

            final int invertedIndex = count - 1 - index;
            animationController.column = columns - 1 - (invertedIndex % columns);
            animationController.row = animationController.rowsCount - 1 - invertedIndex / columns;
        } else {
            super.attachLayoutAnimationParameters(child, params, index, count);
        }
    }
}
