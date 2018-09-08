package com.framgia.nguyenvanducc.soundcloud33.data.repository;

import android.content.Context;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;
import com.framgia.nguyenvanducc.soundcloud33.data.source.TrackDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.TrackLocalDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.remote.TrackRemoteDataSource;

import java.util.List;

public class TrackRepository
        implements TrackDataSource.LocalDataSource, TrackDataSource.RemoteDataSource {
    private static TrackRepository sTrackRepository;
    private TrackLocalDataSource mTrackLocalDataSource;
    private TrackRemoteDataSource mTrackRemoteDataSource;

    private TrackRepository(Context context) {
        this.mTrackLocalDataSource = TrackLocalDataSource.getInstance(context);
        this.mTrackRemoteDataSource = TrackRemoteDataSource.getInstance();
    }

    public static synchronized TrackRepository getInstance(Context context) {
        if (sTrackRepository == null)
            sTrackRepository = new TrackRepository(context);
        return sTrackRepository;
    }

    @Override
    public void getTrackOfGenre(String genre, int limit, int offset,
                                OnLoadDataCompleteListener<Track> listener) {
        mTrackRemoteDataSource.getTrackOfGenre(genre, limit, offset, listener);
    }

    @Override
    public void getTrackFromSearch(String queryString, int limit, int offset,
                                   OnLoadDataCompleteListener<Track> listener) {
        mTrackRemoteDataSource.getTrackFromSearch(queryString, limit, offset, listener);
    }

    @Override
    public void getLocalTrack(OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener) {
        mTrackLocalDataSource.getLocalTrack(trackOnLoadDataCompleteListener);
    }

    @Override
    public List<Track> getAllDownloadedTrack() {
        return mTrackLocalDataSource.getAllDownloadedTrack();
    }

    @Override
    public List<Track> getAllFavoriteTrack() {
        return mTrackLocalDataSource.getAllFavoriteTrack();
    }

    @Override
    public void insertDownloadedTrack(Track track) {
        mTrackLocalDataSource.insertDownloadedTrack(track);
    }

    @Override
    public void insertFavoriteTrack(Track track) {
        mTrackLocalDataSource.insertFavoriteTrack(track);
    }

    @Override
    public void removeDownloadedTrack(Track track) {
        mTrackLocalDataSource.removeDownloadedTrack(track);
    }

    @Override
    public void removeFavoriteTrack(Track track) {
        mTrackLocalDataSource.removeFavoriteTrack(track);
    }

    @Override
    public boolean isTrackInDownloaded(Track track) {
        return mTrackLocalDataSource.isTrackInDownloaded(track);
    }

    @Override
    public boolean isTrackInFavorite(Track track) {
        return mTrackLocalDataSource.isTrackInFavorite(track);
    }
}
