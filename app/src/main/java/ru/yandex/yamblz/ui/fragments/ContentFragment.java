package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.SimpleItemTouchCallback;
import timber.log.Timber;

public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private GridLayoutManager gridLayoutManager;

    private ItemTouchHelper itemTouchHelper;
    private int columns = 1;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ContentAdapter contentAdapter = new ContentAdapter();
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(getActivity(), columns);
        recyclerView.setLayoutManager(gridLayoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchCallback(contentAdapter);

        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void onKeyUp() {
        columns++;
        gridLayoutManager.setSpanCount(columns);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void onKeyDown() {
        if (columns > 1) {
            columns--;
            gridLayoutManager.setSpanCount(columns);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }



}
