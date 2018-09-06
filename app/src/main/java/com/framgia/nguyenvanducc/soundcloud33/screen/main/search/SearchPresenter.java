package com.framgia.nguyenvanducc.soundcloud33.screen.main.search;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter, OnLoadDataCompleteListener<Track> {
    private SearchContract.View mView;
    private TrackRepository mTrackRepository;

    SearchPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void searchTrack(String searchString) {
        mTrackRepository.getTrackFromSearch(searchString,20,0,this);
    }

    @Override
    public void setView(SearchContract.View view) {
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
        mView.showSearchResult(data);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
