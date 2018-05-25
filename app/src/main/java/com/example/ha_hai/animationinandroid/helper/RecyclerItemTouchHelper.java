package com.example.ha_hai.animationinandroid.helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.ha_hai.animationinandroid.adapter.FoodAdapter;
import com.example.ha_hai.animationinandroid.model.ItemTouchDragHelper;
import com.example.ha_hai.animationinandroid.model.ItemTouchMoveHelper;

/**
 * Created by ha_hai on 5/24/2018.
 */

public class RecyclerItemTouchHelper extends android.support.v7.widget.helper.ItemTouchHelper.Callback {

    ItemTouchMoveHelper itemTouchMoveHelper;
    ItemTouchDragHelper itemTouchDragHelper;

    public RecyclerItemTouchHelper(ItemTouchMoveHelper itemTouchMoveHelper, ItemTouchDragHelper itemTouchDragHelper) {
        this.itemTouchMoveHelper = itemTouchMoveHelper;
        this.itemTouchDragHelper = itemTouchDragHelper;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (viewHolder != null) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                View foregroundView = ((FoodAdapter.MyHolder) viewHolder).foregroundView;
                getDefaultUIUtil().onSelected(foregroundView);
            } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {

            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder != null) {
            View foregroundView = ((FoodAdapter.MyHolder) viewHolder).foregroundView;
            getDefaultUIUtil().clearView(foregroundView);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            View foregroundView = ((FoodAdapter.MyHolder) viewHolder).foregroundView;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            View rootView = ((FoodAdapter.MyHolder) viewHolder).rootView;
            getDefaultUIUtil().onDraw(c, recyclerView, rootView, dX, dY, actionState, isCurrentlyActive);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            View foregroundView = ((FoodAdapter.MyHolder) viewHolder).foregroundView;
            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive);
        } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            View rootView = ((FoodAdapter.MyHolder) viewHolder).rootView;
            getDefaultUIUtil().onDraw(c, recyclerView, rootView, dX, dY, actionState, isCurrentlyActive);
        } else {
            super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        itemTouchDragHelper.onItemDrag(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchMoveHelper.onItemMove(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
