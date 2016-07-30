package ru.yandex.yamblz.ui.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.yandex.yamblz.R;

/**
 * Created by user on 29.07.16.
 */

public class FrameItemDecoration extends RecyclerView.ItemDecoration {

    private static final int OFFSET = 16;
    private int indexFirstSelectedItem = -1;
    private int indexSecondSelectedItem = -1;
    private Context context;

    FrameItemDecoration(Context context) {
        this.context = context;
    }

    public void setIndexSelectedItem(int firstIndex, int secondIndex) {
        this.indexFirstSelectedItem = firstIndex;
        this.indexSecondSelectedItem = secondIndex;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (indexFirstSelectedItem != -1 && indexSecondSelectedItem != -1) {

            RecyclerView.ViewHolder child1Holder
                    = parent.findViewHolderForAdapterPosition(indexFirstSelectedItem);
            RecyclerView.ViewHolder child2Holder
                    = parent.findViewHolderForAdapterPosition(indexSecondSelectedItem);

            if (child1Holder != null && child2Holder != null) {
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.crown);
                onDrawCrownSelectedItem(c, parent, child1Holder.itemView, drawable);
                onDrawCrownSelectedItem(c, parent, child2Holder.itemView, drawable);
            }
        }
    }

    private void onDrawCrownSelectedItem(Canvas canvas, RecyclerView parent, View child, Drawable drawable) {
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        drawable.setBounds(layoutManager.getDecoratedRight(child) - drawable.getMinimumWidth() - OFFSET,
                layoutManager.getDecoratedTop(child) + OFFSET,
                layoutManager.getDecoratedRight(child) - OFFSET,
                layoutManager.getDecoratedTop(child) + drawable.getMinimumHeight() + OFFSET);
        drawable.draw(canvas);
    }
}
