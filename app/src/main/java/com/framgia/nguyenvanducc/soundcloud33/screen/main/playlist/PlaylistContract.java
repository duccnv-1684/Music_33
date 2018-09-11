package com.framgia.nguyenvanducc.soundcloud33.screen.main.playlist;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface PlaylistContract {
    interface View {
        void showPlaylist(List<Playlist> playlists);

        void insertPlaylist(Playlist playlist);

        void removePlaylist(Playlist playlist);
    }

    interface Presenter extends BasePresenter<View> {
        void getAllPlaylist();

        void insertPlaylist(String playlistName);

        void removePlaylist(Playlist playlist);
    }
}
