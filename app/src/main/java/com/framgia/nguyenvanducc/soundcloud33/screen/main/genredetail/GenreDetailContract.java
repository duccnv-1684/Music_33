package com.framgia.nguyenvanducc.soundcloud33.screen.main.genredetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface GenreDetailContract {
    interface View {
        void showTrack(List<Track> tracks);

        void updateFavorite(int position);

        void downloadTrack(boolean isDownloadable, Track track);
    }

    interface Presenter extends BasePresenter<GenreDetailContract.View> {
        void getTrack(String genreUrl);

        void favoriteTrack(int position);

        void downloadTrack(int position);
    }
}
