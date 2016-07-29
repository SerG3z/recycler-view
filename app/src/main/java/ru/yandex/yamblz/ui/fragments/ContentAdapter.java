package ru.yandex.yamblz.ui.fragments;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.ItemTouchHelperAdapter;

class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> implements ItemTouchHelperAdapter {

    private final Random rnd = new Random();
    private final List<Integer> colors = new ArrayList<>();

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContentHolder contentHolder = new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false));
        contentHolder.itemView.setOnClickListener(v -> onChangeItemColor(contentHolder.getAdapterPosition()));
        return contentHolder;
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, int position) {
        holder.bind(getColorForPosition(position, true));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public long getItemId(int position) {
        getColorForPosition(position, true);
        return colors.get(position);
    }

    private void onChangeItemColor(int position) {
        if (position != RecyclerView.NO_POSITION) {
            colors.set(position, getColorForPosition(position, false));
            notifyItemChanged(position);
        }
    }

    private Integer getColorForPosition(int position, boolean flagCreate) {
        if (flagCreate) {
            while (position >= colors.size()) {
                colors.add(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
            }
            return colors.get(position);
        } else {
            return Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        }
    }


    @Override
    public boolean onItemMove(int startPosition, int endPosition) {
        Collections.swap(colors, startPosition, endPosition);
        notifyItemMoved(startPosition, endPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int startPosition) {
        colors.remove(startPosition);
        notifyItemRemoved(startPosition);
    }

    static class ContentHolder extends RecyclerView.ViewHolder {
        ContentHolder(View itemView) {
            super(itemView);
        }

        void bind(Integer color) {
            itemView.setBackgroundColor(color);
            ((TextView) itemView).setText("#".concat(Integer.toHexString(color).substring(2)));
        }
    }
}
