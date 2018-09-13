package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying.playinglist;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;

import java.util.ArrayList;
import java.util.List;

public class PlayingListPresenter implements PlayingListContract.Presenter {
    private PlayingListContract.View mView;
    private PlaylistRepository mPlaylistRepository;
    private List<Track> mTracks;

    PlayingListPresenter(PlaylistRepository playlistRepository) {
        mPlaylistRepository = playlistRepository;
        mTracks = new ArrayList<>();
    }

    @Override
    public void setView(PlayingListContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getTrack() {
        List<Track> tracks = mPlaylistRepository.getTrackInPlayingQueue();
        mTracks.addAll(tracks);
        mView.showTrack(tracks);
    }
}
