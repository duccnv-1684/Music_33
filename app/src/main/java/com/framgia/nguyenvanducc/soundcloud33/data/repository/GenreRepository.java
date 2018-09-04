package com.framgia.nguyenvanducc.soundcloud33.data.repository;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Genre;
import com.framgia.nguyenvanducc.soundcloud33.data.source.GenreDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.GenreLocalDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.remote.GenreRemoteDataSource;

import java.util.List;

public class GenreRepository
        implements GenreDataSource.LocalDataSource, GenreDataSource.RemoteDataSource {
    private static GenreRepository sGenreRepository;
    private GenreLocalDataSource mGenreLocalDataSource;
    private GenreRemoteDataSource mGenreRemoteDataSource;

    private GenreRepository(GenreLocalDataSource genreLocalDataSource,
                            GenreRemoteDataSource genreRemoteDataSource) {
        this.mGenreLocalDataSource = genreLocalDataSource;
        this.mGenreRemoteDataSource = genreRemoteDataSource;
    }

    public static synchronized GenreRepository getInstance(
            GenreLocalDataSource genreLocalDataSource
            , GenreRemoteDataSource genreRemoteDataSource) {
        if (sGenreRepository == null)
            sGenreRepository = new GenreRepository(genreLocalDataSource, genreRemoteDataSource);
        return sGenreRepository;
    }

    @Override
    public List<Genre> getAllGenre() {
        return mGenreLocalDataSource.getAllGenre();
    }
}
