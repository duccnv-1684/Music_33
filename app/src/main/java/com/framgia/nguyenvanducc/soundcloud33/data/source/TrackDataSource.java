package com.framgia.nguyenvanducc.soundcloud33.data.source;

import android.content.ContentResolver;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;

import java.util.List;

public interface TrackDataSource {
    interface LocalDataSource {
        void getLocalTrack(OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener);

        List<Track> getAllDownloadedTrack();

        List<Track> getAllFavoriteTrack();

        void insertDownloadedTrack(Track track);

        void insertFavoriteTrack(Track track);

        void removeDownloadedTrack(Track track);

        void removeFavoriteTrack(Track track);

        boolean isTrackInDownloaded(Track track);

        boolean isTrackInFavorite(Track track);
    }

    interface RemoteDataSource {
        void getTrackOfGenre(String genre, int limit, int offset
                , OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener);

        void getTrackFromSearch(String queryString, int limit, int offset
                , OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener);
    }
}
