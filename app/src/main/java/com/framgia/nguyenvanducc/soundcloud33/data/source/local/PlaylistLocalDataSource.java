package com.framgia.nguyenvanducc.soundcloud33.data.source.local;

import android.content.Context;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.PlaylistDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.sqlite.DatabaseHelper;

import java.util.List;

public class PlaylistLocalDataSource implements PlaylistDataSource.LocalDataSource {
    private static PlaylistLocalDataSource sPlaylistLocalDataSource;
    private DatabaseHelper mDatabaseHelper;
    private PlaylistLocalDataSource(Context context){
        this.mDatabaseHelper = DatabaseHelper.getInstance(context);
    }
    public static synchronized PlaylistLocalDataSource getInstance(Context context){
        if (sPlaylistLocalDataSource==null)
            sPlaylistLocalDataSource = new PlaylistLocalDataSource(context);
        return sPlaylistLocalDataSource;
    }

    @Override
    public List<Playlist> getAllPlaylist() {
        return mDatabaseHelper.getAllPlaylist();
    }

    @Override
    public void insertPlaylist(Playlist playlist) {
        mDatabaseHelper.insertPlaylist(playlist);
    }

    @Override
    public void removePlaylist(Playlist playlist) {
        mDatabaseHelper.removePlaylist(playlist);
    }

    @Override
    public List<Track> getAllTrackOfPlaylist(Playlist playlist) {
        return mDatabaseHelper.getAllTrackOfPlaylist(playlist);
    }

    @Override
    public void addTrackToPlaylist(Playlist playlist, Track track) {
        mDatabaseHelper.addTrackToPlaylist(playlist,track);
    }

    @Override
    public void removeTrackFromPlaylist(Playlist playlist, Track track) {
        mDatabaseHelper.removeTrackFromPlaylist(playlist,track);
    }

    @Override
    public void newPlayingQueue(List<Track> tracks) {
        mDatabaseHelper.newPlayingQueue(tracks);
    }

    @Override
    public List<Track> getTrackInPlayingQueue() {
        return mDatabaseHelper.getTrackInPlayingQueue();
    }
}
