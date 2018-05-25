package com.example.ha_hai.animationinandroid.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.ha_hai.animationinandroid.R;
import com.example.ha_hai.animationinandroid.model.AnimationItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseFragment {


    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public AnimationItem[] getAnimationItems() {
        return new AnimationItem[] {
                new AnimationItem("Fall down", R.anim.item_list_layout_animation_fall_down),
                new AnimationItem("Slide from right", R.anim.item_list_layout_animation_slide_from_right),
                new AnimationItem("Slide from bottom", R.anim.item_list_layout_animation_from_bottom)
        };
    }
}
