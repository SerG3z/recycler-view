package ru.yandex.yamblz.ui.fragments;

import android.animation.ObjectAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 28.07.16.
 */

class AnimationAppearanceScrollListener extends RecyclerView.OnScrollListener {

    private static final int DURATION = 1000;
    private static final int ROTATION = 360;

    private int tempFirstItemPosition;
    private int tempLastItemPosition;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        if (dy < 0 && tempFirstItemPosition != firstVisibleItemPosition) {
            onDrawAnimation(recyclerView, firstVisibleItemPosition,
                    firstVisibleItemPosition + layoutManager.getSpanCount());
        }
        if (dy > 0 && tempLastItemPosition != lastVisibleItemPosition) {
            onDrawAnimation(recyclerView, lastVisibleItemPosition - layoutManager.getSpanCount() + 1,
                    lastVisibleItemPosition + 1);
        }

        tempFirstItemPosition = layoutManager.findFirstVisibleItemPosition();
        tempLastItemPosition = layoutManager.findLastVisibleItemPosition();
    }

    private void onDrawAnimation(RecyclerView recyclerView, int startPos, int endPos) {
        for (int i = startPos; i < endPos; i++) {
            ObjectAnimator.ofFloat(
                    recyclerView.findViewHolderForAdapterPosition(i).itemView,
                    View.ROTATION_X, 0, ROTATION)
                    .setDuration(DURATION)
                    .start();
        }
    }
}
