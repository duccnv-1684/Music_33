package com.framgia.nguyenvanducc.soundcloud33.screen.main.favorite;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface FavoriteContract {
    interface View {
        void showFavoriteTrack(List<Track> tracks);
        void removeTrack(Track track);
    }

    interface Presenter extends BasePresenter<View> {
        void getAllFavoriteTrack();

        void removeFavoriteTrack(Track track);

    }
}
