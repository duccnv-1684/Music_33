package com.framgia.nguyenvanducc.soundcloud33.screen.main.playlist;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;
import com.framgia.nguyenvanducc.soundcloud33.utils.StringUtils;

import java.util.List;

public class PlaylistPresenter implements PlaylistContract.Presenter {
    private PlaylistContract.View mView;
    private PlaylistRepository mPlaylistRepository;

    public PlaylistPresenter(PlaylistRepository playlistRepository) {
        mPlaylistRepository = playlistRepository;
    }

    @Override
    public void getAllPlaylist() {
        List<Playlist> playlists = mPlaylistRepository.getAllPlaylist();
        mView.showPlaylist(playlists);
    }

    @Override
    public void insertPlaylist(String playlistName) {
        Playlist playlist = new Playlist.Builder()
                .setId(StringUtils.createPlaylistId())
                .setTitle(playlistName)
                .build();
        mPlaylistRepository.insertPlaylist(playlist);
        mView.insertPlaylist(playlist);
    }

    @Override
    public void removePlaylist(Playlist playlist) {
        mPlaylistRepository.removePlaylist(playlist);
        mView.removePlaylist(playlist);
    }

    @Override
    public void setView(PlaylistContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
