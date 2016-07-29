package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.SimpleItemTouchCallback;

public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private GridLayoutManager gridLayoutManager;

    private ItemTouchHelper itemTouchHelper;
    private int columns = 1;
    private boolean flagDecoration = false;
    private DefaultItemAnimator animator = new DefaultItemAnimator();
    private CustomItemDecoration customItemDecoration = new CustomItemDecoration();
    private FrameItemDecoration frameItemDecoration;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.custom_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.decoration:
                setItemDecorator();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setItemDecorator() {
        if (flagDecoration) {
            recyclerView.removeItemDecoration(customItemDecoration);
            flagDecoration = false;
        } else {
            recyclerView.addItemDecoration(customItemDecoration);
            flagDecoration = true;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ContentAdapter contentAdapter = new ContentAdapter();
        contentAdapter.setHasStableIds(true);
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(getActivity(), columns);
        recyclerView.setLayoutManager(gridLayoutManager);
        animator.setAddDuration(1000);
        recyclerView.setItemAnimator(animator);
        frameItemDecoration = new FrameItemDecoration(getContext());
        ItemTouchHelper.Callback callback = new SimpleItemTouchCallback(contentAdapter, frameItemDecoration, getContext());
        recyclerView.addItemDecoration(frameItemDecoration);

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
