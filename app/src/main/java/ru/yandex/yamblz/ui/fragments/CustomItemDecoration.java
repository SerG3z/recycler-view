package ru.yandex.yamblz.ui.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 29.07.16.
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paintBlue;

    public CustomItemDecoration() {
        paintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setStrokeWidth(10);
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
                        paintBlue);
            }
        }
    }
}