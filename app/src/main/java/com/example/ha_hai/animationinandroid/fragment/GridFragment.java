package com.example.ha_hai.animationinandroid.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.ha_hai.animationinandroid.R;
import com.example.ha_hai.animationinandroid.model.AnimationItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends BaseFragment {


    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_grid;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 4);
    }

    @Override
    public AnimationItem[] getAnimationItems() {
        return new AnimationItem[] {
                new AnimationItem("Slide from bottom", R.anim.item_list_layout_animation_fall_down),
                new AnimationItem("Scale", R.anim.item_grid_layout_animation_scale),
                new AnimationItem("Scale Random", R.anim.item_grid_layout_animation_random_scale)
        };
    }

}
