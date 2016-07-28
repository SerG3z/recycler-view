package ru.yandex.yamblz.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import ru.yandex.yamblz.App;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.developer_settings.DeveloperSettingsModule;
import ru.yandex.yamblz.ui.fragments.ContentFragment;
import ru.yandex.yamblz.ui.other.ViewModifier;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Inject @Named(DeveloperSettingsModule.MAIN_ACTIVITY_VIEW_MODIFIER)
    ViewModifier viewModifier;

    public static final String TAG_COTENT_FRAGMENT = "tag_content_fragment";

    @SuppressLint("InflateParams") // It's okay in our case.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).applicationComponent().inject(this);

        setContentView(viewModifier.modify(getLayoutInflater().inflate(R.layout.activity_main, null)));

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame_layout, new ContentFragment(), TAG_COTENT_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                ((ContentFragment) getSupportFragmentManager()
                        .findFragmentByTag(TAG_COTENT_FRAGMENT)).onKeyUp();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                ((ContentFragment) getSupportFragmentManager()
                        .findFragmentByTag(TAG_COTENT_FRAGMENT)).onKeyDown();
                return true;
        }
        return true;
    }
}
