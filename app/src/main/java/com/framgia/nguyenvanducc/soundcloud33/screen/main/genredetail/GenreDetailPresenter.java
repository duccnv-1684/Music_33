package com.framgia.nguyenvanducc.soundcloud33.screen.main.genredetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;

import java.util.List;

public class GenreDetailPresenter implements GenreDetailContract.Presenter
        , OnLoadDataCompleteListener<Track> {
    private static final int LIMIT = 20;
    private GenreDetailContract.View mView;
    private TrackRepository mTrackRepository;
    private int offset = 0;

    GenreDetailPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void setView(GenreDetailContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getTrack(String genreUrl) {
        mTrackRepository.getTrackOfGenre(genreUrl, LIMIT, offset, this);
    }

    @Override
    public void onSuccess(List<Track> data) {
        mView.showTrack(data);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
