package com.framgia.nguyenvanducc.soundcloud33.data.source;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;

import java.util.List;

public interface PlaylistDataSource {
    interface LocalDataSource {
        List<Playlist> getAllPlaylist();

        void insertPlaylist(Playlist playlist);

        void removePlaylist(Playlist playlist);

        List<Track> getAllTrackOfPlaylist(Playlist playlist);

        void addTrackToPlaylist(Playlist playlist, Track track);

        void removeTrackFromPlaylist(Playlist playlist, Track track);

        void newPlayingQueue(List<Track> tracks);

        List<Track> getTrackInPlayingQueue();

    }

    interface RemoteDataSource {

    }
}
