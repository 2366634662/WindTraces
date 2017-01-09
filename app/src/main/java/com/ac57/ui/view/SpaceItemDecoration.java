package com.ac57.ui.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Du_Li on 2016/12/31.
 */


public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (itemPosition != 0)
            outRect.left = space;
    }
}
