package com.framgia.nguyenvanducc.soundcloud33.data.model;

public class Genre {
    private String mName;
    private String mGenreUrl;

    public Genre(String name, String genreUrl) {
        mName = name;
        mGenreUrl = genreUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getGenreUrl() {
        return mGenreUrl;
    }

    public void setGenreUrl(String genreUrl) {
        mGenreUrl = genreUrl;
    }
}
