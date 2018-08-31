package com.framgia.nguyenvanducc.soundcloud33.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseActivity;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener, MainContract.View {
    private TextView mTextToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        mTextToolbarTitle = findViewById(R.id.text_toolbar_title);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_nav_home:
                mTextToolbarTitle.setText(getString(R.string.title_home));
                return true;
            case R.id.menu_nav_search:
                mTextToolbarTitle.setText(getString(R.string.title_search));
                return true;
            case R.id.menu_nav_library:
                mTextToolbarTitle.setText(getString(R.string.title_library));
                return true;
        }
        return false;
    }
}
