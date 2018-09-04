package com.framgia.nguyenvanducc.soundcloud33.data.source;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;

public interface TrackDataSource {
    interface LocalDataSource {

    }

    interface RemoteDataSource {
        void getTrackOfGenre(String genre, int limit, int offset
                , OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener);
    }
}
