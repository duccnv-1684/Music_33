package com.framgia.nguyenvanducc.soundcloud33.screen.main.home;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Genre;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.GenreRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter
        , OnLoadDataCompleteListener<Track> {
    private static final int LIMIT = 25;
    private static final int OFFSET = 0;
    private HomeContract.View mView;
    private TrackRepository mTrackRepository;
    private GenreRepository mGenreRepository;
    private List<Genre> mGenres;
    private int mCurrentIndex = -1;

    HomePresenter(TrackRepository trackRepository, GenreRepository genreRepository) {
        mTrackRepository = trackRepository;
        mGenreRepository = genreRepository;
    }

    @Override
    public void setView(HomeContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getAllGenre() {
        mGenres = mGenreRepository.getAllGenre();
        loadTrack();
    }

    @Override
    public void showGenreDetail(Genre genre) {
        mView.switchToGenreDetail(genre.getName(), genre.getGenreUrl());
    }

    @Override
    public void onSuccess(List<Track> data) {
        mView.showTracksOfGenre(mGenres.get(mCurrentIndex), data, mCurrentIndex);
        loadTrack();
    }

    @Override
    public void onFailure(Exception e) {
        loadTrack();
    }

    private void loadTrack() {
        if (++mCurrentIndex < mGenres.size())
            mTrackRepository.getTrackOfGenre(mGenres.get(mCurrentIndex).getGenreUrl()
                    , LIMIT, OFFSET, this);
    }
}
