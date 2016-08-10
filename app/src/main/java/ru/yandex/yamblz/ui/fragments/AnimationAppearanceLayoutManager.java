package ru.yandex.yamblz.ui.fragments;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by SerG3z on 06.08.16.
 */

public class AnimationAppearanceLayoutManager extends GridLayoutManager {

    public AnimationAppearanceLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AnimationAppearanceLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public AnimationAppearanceLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollVerticallyBy(dy, recycler, state);

    }

    @Override
    public void removeAndRecycleView(View child, RecyclerView.Recycler recycler) {
        super.removeAndRecycleView(child, recycler);
    }

    @Override
    public void removeAndRecycleViewAt(int index, RecyclerView.Recycler recycler) {
        super.removeAndRecycleViewAt(index, recycler);
    }
}
