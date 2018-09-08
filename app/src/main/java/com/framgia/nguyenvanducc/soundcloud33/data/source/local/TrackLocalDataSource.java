package com.framgia.nguyenvanducc.soundcloud33.data.source.local;

import android.content.ContentResolver;
import android.content.Context;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;
import com.framgia.nguyenvanducc.soundcloud33.data.source.TrackDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.sqlite.DatabaseHelper;

import java.util.List;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {
    private static TrackLocalDataSource sTrackLocalDataSource;
    private ContentResolver mContentResolver;
    private DatabaseHelper mDatabaseHelper;

    private TrackLocalDataSource(Context context) {
        this.mContentResolver = context.getContentResolver();
        this.mDatabaseHelper = DatabaseHelper.getInstance(context);
    }

    public static synchronized TrackLocalDataSource getInstance(Context context) {
        if (sTrackLocalDataSource == null)
            sTrackLocalDataSource = new TrackLocalDataSource(context);
        return sTrackLocalDataSource;
    }

    @Override
    public void getLocalTrack(OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener) {
        new GetLocalTrackTask(mContentResolver, trackOnLoadDataCompleteListener).execute();
    }

    @Override
    public List<Track> getAllDownloadedTrack() {
        return mDatabaseHelper.getAllDownloadedTrack();
    }

    @Override
    public List<Track> getAllFavoriteTrack() {
        return mDatabaseHelper.getAllFavoriteTrack();
    }

    @Override
    public void insertDownloadedTrack(Track track) {
        mDatabaseHelper.insertDownloadedTrack(track);
    }

    @Override
    public void insertFavoriteTrack(Track track) {
        mDatabaseHelper.insertFavoriteTrack(track);
    }

    @Override
    public void removeDownloadedTrack(Track track) {
        mDatabaseHelper.removeDownloadedTrack(track);
    }

    @Override
    public void removeFavoriteTrack(Track track) {
        mDatabaseHelper.removeFavoriteTrack(track);
    }

    @Override
    public boolean isTrackInDownloaded(Track track) {
        return mDatabaseHelper.isTrackInDownloaded(track);
    }

    @Override
    public boolean isTrackInFavorite(Track track) {
        return mDatabaseHelper.isTrackInFavorite(track);
    }
}
