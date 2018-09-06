package com.framgia.nguyenvanducc.soundcloud33.screen.main.localtrack;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;

import java.util.List;

public class LocalTrackPresenter implements LocalTrackContract.Presenter
        , OnLoadDataCompleteListener<Track> {
    private LocalTrackContract.View mView;
    private TrackRepository mTrackRepository;

    LocalTrackPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void getLocalTrack() {
        mTrackRepository.getLocalTrack(this);
    }

    @Override
    public void setView(LocalTrackContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSuccess(List<Track> data) {
        mView.showLocalTrack(data);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
