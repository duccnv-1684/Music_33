package com.framgia.nguyenvanducc.soundcloud33.data.source.remote;

import com.framgia.nguyenvanducc.soundcloud33.data.source.PlaylistDataSource;

public class PlaylistRemoteDataSource implements PlaylistDataSource.RemoteDataSource {
    private static PlaylistRemoteDataSource sPlaylistRemoteDataSource;

    private PlaylistRemoteDataSource() {

    }

    public static synchronized PlaylistRemoteDataSource getInstance() {
        if (sPlaylistRemoteDataSource == null)
            sPlaylistRemoteDataSource = new PlaylistRemoteDataSource();
        return sPlaylistRemoteDataSource;
    }
}
