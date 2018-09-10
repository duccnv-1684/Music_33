package com.framgia.nguyenvanducc.soundcloud33.screen.main.playlistdetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface PlaylistDetailContract {
    interface View {
        void showAllPlaylistTrack(List<Track> tracks);

        void removeTrack(Track track);
    }

    interface Presenter extends BasePresenter<PlaylistDetailContract.View> {
        void getAllPlayListTrack(Playlist playlist);

        void removeTrack(Playlist playlist, Track track);
    }
}
