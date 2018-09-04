package com.framgia.nguyenvanducc.soundcloud33.data.repository;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;
import com.framgia.nguyenvanducc.soundcloud33.data.source.TrackDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.TrackLocalDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.remote.TrackRemoteDataSource;

public class TrackRepository
        implements TrackDataSource.LocalDataSource, TrackDataSource.RemoteDataSource {
    private static TrackRepository sTrackRepository;
    private TrackLocalDataSource mTrackLocalDataSource;
    private TrackRemoteDataSource mTrackRemoteDataSource;

    private TrackRepository(TrackLocalDataSource trackLocalDataSource,
                            TrackRemoteDataSource trackRemoteDataSource) {
        this.mTrackLocalDataSource = trackLocalDataSource;
        this.mTrackRemoteDataSource = trackRemoteDataSource;
    }

    public static synchronized TrackRepository getInstance(
            TrackLocalDataSource trackLocalDataSource
            , TrackRemoteDataSource trackRemoteDataSource) {
        if (sTrackRepository == null)
            sTrackRepository = new TrackRepository(trackLocalDataSource, trackRemoteDataSource);
        return sTrackRepository;
    }

    @Override
    public void getTrackOfGenre(String genre, int limit, int offset,
                                OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener) {
        mTrackRemoteDataSource.getTrackOfGenre(genre, limit
                , offset, trackOnLoadDataCompleteListener);
    }
}
