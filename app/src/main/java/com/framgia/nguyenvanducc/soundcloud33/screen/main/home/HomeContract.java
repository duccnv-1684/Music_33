package com.framgia.nguyenvanducc.soundcloud33.screen.main.home;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Genre;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface HomeContract {
    interface View {
        void showTracksOfGenre(Genre genre, List<Track> tracks, int currentIndex);
        void switchToGenreDetail(String genreName,String genreUrl);
    }

    interface Presenter extends BasePresenter<View> {
        void getAllGenre();
        void showGenreDetail(Genre genre);
    }
}
