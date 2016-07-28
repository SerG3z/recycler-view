package ru.yandex.yamblz.ui.adapters;

/**
 * Created by user on 28.07.16.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int startPosition, int endPosition);
    void onItemDismiss(int position);
}
