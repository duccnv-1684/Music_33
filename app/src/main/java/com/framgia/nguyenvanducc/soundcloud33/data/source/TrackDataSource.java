package com.framgia.nguyenvanducc.soundcloud33.data.source;

import android.content.ContentResolver;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;

public interface TrackDataSource {
    interface LocalDataSource {
        void getLocalTrack(OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener);
    }

    interface RemoteDataSource {
        void getTrackOfGenre(String genre, int limit, int offset
                , OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener);

        void getTrackFromSearch(String queryString, int limit, int offset
                , OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener);
    }
}
