package com.framgia.nguyenvanducc.soundcloud33.data.repository;

import android.content.Context;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.PlaylistDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.PlaylistLocalDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.remote.PlaylistRemoteDataSource;

import java.util.List;

public class PlaylistRepository implements
        PlaylistDataSource.LocalDataSource,PlaylistDataSource.RemoteDataSource {
    private static PlaylistRepository sPlaylistRepository;
    private PlaylistLocalDataSource mPlaylistLocalDataSource;
    private PlaylistRemoteDataSource mPlaylistRemoteDataSource;

    private PlaylistRepository(Context context) {
        this.mPlaylistLocalDataSource = PlaylistLocalDataSource.getInstance(context);
        this.mPlaylistRemoteDataSource = PlaylistRemoteDataSource.getInstance();
    }

    public static synchronized PlaylistRepository getInstance(Context context) {
        if (sPlaylistRepository == null)
            sPlaylistRepository = new PlaylistRepository(context);
        return sPlaylistRepository;
    }
    @Override
    public List<Playlist> getAllPlaylist() {
        return mPlaylistLocalDataSource.getAllPlaylist();
    }

    @Override
    public void insertPlaylist(Playlist playlist) {
        mPlaylistLocalDataSource.insertPlaylist(playlist);
    }

    @Override
    public void removePlaylist(Playlist playlist) {
        mPlaylistLocalDataSource.removePlaylist(playlist);
    }

    @Override
    public List<Track> getAllTrackOfPlaylist(Playlist playlist) {
        return mPlaylistLocalDataSource.getAllTrackOfPlaylist(playlist);
    }

    @Override
    public void addTrackToPlaylist(Playlist playlist, Track track) {
        mPlaylistLocalDataSource.addTrackToPlaylist(playlist,track);
    }

    @Override
    public void removeTrackFromPlaylist(Playlist playlist, Track track) {
        mPlaylistLocalDataSource.removeTrackFromPlaylist(playlist,track);
    }

    @Override
    public void newPlayingQueue(List<Track> tracks) {
        mPlaylistLocalDataSource.newPlayingQueue(tracks);
    }

    @Override
    public List<Track> getTrackInPlayingQueue() {
        return mPlaylistLocalDataSource.getTrackInPlayingQueue();
    }
}
