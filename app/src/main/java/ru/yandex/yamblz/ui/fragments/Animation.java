package ru.yandex.yamblz.ui.fragments;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by SerG3z on 06.08.16.
 */

public class Animation {

    public static void animationAppearence(RecyclerView.ViewHolder holder) {
        ObjectAnimator.ofFloat(
                holder.itemView,
                View.ROTATION_X, 0, 360)
                .setDuration(1000)
                .start();
    }
}
