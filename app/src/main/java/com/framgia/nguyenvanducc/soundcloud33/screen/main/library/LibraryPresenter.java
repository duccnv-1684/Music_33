package com.framgia.nguyenvanducc.soundcloud33.screen.main.library;

import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;

public class LibraryPresenter implements LibraryContract.Presenter {
    private LibraryContract.View mView;

    @Override
    public void setView(LibraryContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void switchToMusicOnDevice() {
        mView.switchToLibrary(Constants.KEY_MUSIC_ON_DEVICE);
    }

    @Override
    public void switchToPlaylist() {
        mView.switchToLibrary(Constants.KEY_PLAYLIST);
    }

    @Override
    public void switchToFavorite() {
        mView.switchToLibrary(Constants.KEY_FAVORITE);
    }

    @Override
    public void switchToDownloaded() {
        mView.switchToLibrary(Constants.KEY_DOWNLOADED);
    }
}
