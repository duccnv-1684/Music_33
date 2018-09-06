package com.framgia.nguyenvanducc.soundcloud33.screen.main.library;

import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

public interface LibraryContract {
    interface View {
        void switchToLibrary(int key);
    }

    interface Presenter extends BasePresenter<LibraryContract.View> {
        void switchToMusicOnDevice();

        void switchToPlaylist();

        void switchToFavorite();

        void switchToDownloaded();
    }
}
