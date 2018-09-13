package com.framgia.nguyenvanducc.soundcloud33.screen.splash;

import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.shareprf.SharedPrefs;

public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mView;
    private SharedPrefs mSharedPrefs;
    private PlaylistRepository mPlaylistRepository;

    public SplashPresenter(SharedPrefs sharedPrefs, PlaylistRepository playlistRepository) {
        mSharedPrefs = sharedPrefs;
        mPlaylistRepository = playlistRepository;
    }

    @Override
    public void setView(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setUpParameter() {
        mView.switchToMain();
    }
}
