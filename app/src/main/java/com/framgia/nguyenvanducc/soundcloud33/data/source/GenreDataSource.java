package com.framgia.nguyenvanducc.soundcloud33.data.source;


import com.framgia.nguyenvanducc.soundcloud33.data.model.Genre;

import java.util.List;

public interface GenreDataSource {
    interface LocalDataSource {
        List<Genre> getAllGenre();

    }

    interface RemoteDataSource {

    }
}
