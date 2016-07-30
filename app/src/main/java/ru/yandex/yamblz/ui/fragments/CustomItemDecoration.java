package ru.yandex.yamblz.ui.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 29.07.16.
 */

class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    CustomItemDecoration() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(child) % 2 == 0) {
                c.drawRect(
                        layoutManager.getDecoratedLeft(child),
                        layoutManager.getDecoratedTop(child),
                        layoutManager.getDecoratedRight(child),
                        layoutManager.getDecoratedBottom(child),
                        paint);
            }
        }
    }
}