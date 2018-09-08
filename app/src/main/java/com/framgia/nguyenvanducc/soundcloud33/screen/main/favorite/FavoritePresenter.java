package com.framgia.nguyenvanducc.soundcloud33.screen.main.favorite;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {
    private FavoriteContract.View mView;
    private TrackRepository mTrackRepository;

    FavoritePresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void setView(FavoriteContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getAllFavoriteTrack() {
        List<Track> favoriteTracks = mTrackRepository.getAllFavoriteTrack();
        mView.showFavoriteTrack(favoriteTracks);
    }

    @Override
    public void removeFavoriteTrack(Track track) {
        mTrackRepository.removeFavoriteTrack(track);
        mView.removeTrack(track);
    }
}
