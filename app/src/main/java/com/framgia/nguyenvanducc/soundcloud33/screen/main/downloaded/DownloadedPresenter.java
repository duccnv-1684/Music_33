package com.framgia.nguyenvanducc.soundcloud33.screen.main.downloaded;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;

import java.util.List;

public class DownloadedPresenter implements DownloadedContract.Presenter {
    private DownloadedContract.View mView;
    private TrackRepository mTrackRepository;

    DownloadedPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void setView(DownloadedContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getAllDownloadedTrack() {
        List<Track> favoriteTracks = mTrackRepository.getAllDownloadedTrack();
        mView.showDownloadedTrack(favoriteTracks);
    }

}
