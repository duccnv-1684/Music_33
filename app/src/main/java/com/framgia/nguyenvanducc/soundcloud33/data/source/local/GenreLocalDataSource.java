package com.framgia.nguyenvanducc.soundcloud33.data.source.local;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Genre;
import com.framgia.nguyenvanducc.soundcloud33.data.source.GenreDataSource;
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class GenreLocalDataSource implements GenreDataSource.LocalDataSource {
    private static final String ALL_MUSIC_URL = "all-music";
    private static final String ALL_AUDIO_URL = "all-audio";
    private static final String ALTERNATIVE_ROCK_URL = "alternativerock";
    private static final String AMBIENT_URL = "ambient";
    private static final String CLASSICAL_URL = "classical";
    private static final String COUNTRY_URL = "country";
    private static GenreLocalDataSource  sGenreLocalDataSource;
    private GenreLocalDataSource(){

    }
    public static synchronized GenreLocalDataSource getInstance(){
        if (sGenreLocalDataSource==null) sGenreLocalDataSource = new GenreLocalDataSource();
        return sGenreLocalDataSource;
    }

    @Override
    public List<Genre> getAllGenre() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(Constants.GENRE_ALL_MUSIC, ALL_MUSIC_URL));
        genres.add(new Genre(Constants.GENRE_ALL_AUDIO, ALL_AUDIO_URL));
        genres.add(new Genre(Constants.GENRE_ALTERNATIVE_ROCK, ALTERNATIVE_ROCK_URL));
        genres.add(new Genre(Constants.GENRE_AMBIENT, AMBIENT_URL));
        genres.add(new Genre(Constants.GENRE_CLASSICAL, CLASSICAL_URL));
        genres.add(new Genre(Constants.GENRE_COUNTRY, COUNTRY_URL));
        return genres;
    }
}
