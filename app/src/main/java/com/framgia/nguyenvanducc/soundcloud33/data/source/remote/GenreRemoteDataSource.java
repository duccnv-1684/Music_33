package com.framgia.nguyenvanducc.soundcloud33.data.source.remote;

import com.framgia.nguyenvanducc.soundcloud33.data.source.GenreDataSource;

public class GenreRemoteDataSource implements GenreDataSource.RemoteDataSource {
    private static GenreRemoteDataSource sGenreRemoteDataSource;

    private GenreRemoteDataSource() {

    }

    public static synchronized GenreRemoteDataSource getInstance() {
        if (sGenreRemoteDataSource == null) sGenreRemoteDataSource = new GenreRemoteDataSource();
        return sGenreRemoteDataSource;
    }
}
