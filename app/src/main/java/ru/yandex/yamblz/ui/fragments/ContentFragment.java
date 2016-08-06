package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.SimpleItemTouchCallback;

public class ContentFragment extends BaseFragment {

    private static final String KEY_SAVE_COLUMNS = "save_columns";
//    private static final int CACHE_SIZE = 180;

    @BindView(R.id.rv)
    RecyclerView recyclerView;


    private Unbinder unbinder;
    private GridLayoutManager gridLayoutManager;
    private int columns;
    private boolean flagDecoration;
    private boolean flag = true;
    private CustomItemDecoration customItemDecoration;
    private AnimationAppearanceScrollListener animationAppearanceScrollListener;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            columns = savedInstanceState.getInt(KEY_SAVE_COLUMNS);
        } else {
            columns = 1;
        }
        animationAppearanceScrollListener = new AnimationAppearanceScrollListener();
        customItemDecoration = new CustomItemDecoration();
        final ContentAdapter contentAdapter = new ContentAdapter();
        recyclerView.setAdapter(contentAdapter);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), columns);
        recyclerView.setLayoutManager(gridLayoutManager);
        CustomItemAnimation customItemAnimation = new CustomItemAnimation();
        recyclerView.setItemAnimator(customItemAnimation);
        FrameItemDecoration frameItemDecoration = new FrameItemDecoration(getContext());
        ItemTouchHelper.Callback callback = new SimpleItemTouchCallback(contentAdapter, frameItemDecoration, getContext());
        recyclerView.addItemDecoration(frameItemDecoration);
//        recyclerView.setItemViewCacheSize(CACHE_SIZE);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 100);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

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
            case R.id.animation:
                if (flag) {
                    recyclerView.addOnScrollListener(animationAppearanceScrollListener);
                    flag = false;
                } else {
                    recyclerView.clearOnScrollListeners();
                    flag = true;
                }
                return false;
            case R.id.columnsSize:
                columns = 30;
                setSpanCountColumns();
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

    private void setSpanCountColumns() {
        gridLayoutManager.setSpanCount(columns);
        recyclerView.requestLayout();
        recyclerView.getAdapter()
                .notifyItemRangeChanged(gridLayoutManager.findFirstVisibleItemPosition(), 0);
    }

    public void onKeyUp() {
        columns++;
        setSpanCountColumns();
    }

    public void onKeyDown() {
        if (columns > 1) {
            columns--;
            setSpanCountColumns();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SAVE_COLUMNS, columns);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
