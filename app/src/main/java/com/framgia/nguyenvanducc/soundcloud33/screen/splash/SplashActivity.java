package com.framgia.nguyenvanducc.soundcloud33.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.shareprf.SharedPrefs;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseActivity;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SplashPresenter(SharedPrefs.getInstance(this),
                PlaylistRepository.getInstance(this));
        mPresenter.setView(this);
        mPresenter.setUpParameter();
    }

    @Override
    public void switchToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
