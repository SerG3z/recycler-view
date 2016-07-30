package ru.yandex.yamblz.ui.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.fragments.FrameItemDecoration;

/**
 * Created by user on 28.07.16.
 */

public class SimpleItemTouchCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter touchHelperAdapter;
    private final FrameItemDecoration frameItemDecoration;
    private Paint paint;
    private Context context;

    public SimpleItemTouchCallback(ItemTouchHelperAdapter touchHelperAdapter, FrameItemDecoration frameItemDecoration, Context context) {
        this.touchHelperAdapter = touchHelperAdapter;
        this.frameItemDecoration = frameItemDecoration;
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT |
                ItemTouchHelper.RIGHT;
        final int swipeFlag = ItemTouchHelper.END;
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        frameItemDecoration.setIndexSelectedItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        touchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        touchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.delete_icon);
            View itemView = viewHolder.itemView;
            float alpha = Math.abs(dX) / itemView.getWidth();
            itemView.setTranslationX(dX);
            if (dX >= itemView.getWidth()) {
                paint.setAlpha(255);
                drawable.setAlpha(255);
            } else {
                paint.setAlpha((int) (255 * alpha));
                drawable.setAlpha((int) (255 * alpha));
            }
            c.drawRect(itemView.getLeft(), itemView.getTop(), itemView.getRight(),
                    itemView.getBottom(), paint);

            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int left = layoutManager.getDecoratedLeft(itemView);
            int top = layoutManager.getDecoratedTop(itemView) + drawable.getMinimumHeight();
            int bottom = layoutManager.getDecoratedBottom(itemView) - drawable.getMinimumHeight();
            int right = layoutManager.getDecoratedLeft(itemView) + bottom - top;
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
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
