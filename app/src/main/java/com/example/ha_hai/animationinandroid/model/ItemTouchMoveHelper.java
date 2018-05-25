package com.example.ha_hai.animationinandroid.model;

/**
 * Created by ha_hai on 5/25/2018.
 */

public interface ItemTouchMoveHelper {

    void onItemMove(int position);

    void onItemRestore(Food food, int position);
}
