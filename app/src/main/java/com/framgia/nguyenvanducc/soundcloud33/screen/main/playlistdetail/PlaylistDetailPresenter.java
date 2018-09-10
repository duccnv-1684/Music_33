package com.framgia.nguyenvanducc.soundcloud33.screen.main.playlistdetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;

import java.util.List;

public class PlaylistDetailPresenter implements PlaylistDetailContract.Presenter {
    private PlaylistDetailContract.View mView;
    private PlaylistRepository mPlaylistRepository;

    public PlaylistDetailPresenter(PlaylistRepository playlistRepository) {
        mPlaylistRepository = playlistRepository;
    }

    @Override
    public void getAllPlayListTrack(Playlist playlist) {
        List<Track> tracks = mPlaylistRepository.getAllTrackOfPlaylist(playlist);
        mView.showAllPlaylistTrack(tracks);
    }

    @Override
    public void removeTrack(Playlist playlist, Track track) {
        mPlaylistRepository.removeTrackFromPlaylist(playlist, track);
        mView.removeTrack(track);
    }

    @Override
    public void setView(PlaylistDetailContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
