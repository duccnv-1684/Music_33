package com.framgia.nguyenvanducc.soundcloud33.data.source.local;

import android.content.ContentResolver;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;
import com.framgia.nguyenvanducc.soundcloud33.data.source.TrackDataSource;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {
    private static TrackLocalDataSource sTrackLocalDataSource;
    private ContentResolver mContentResolver;

    private TrackLocalDataSource(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    public static synchronized TrackLocalDataSource getInstance(ContentResolver contentResolver) {
        if (sTrackLocalDataSource == null)
            sTrackLocalDataSource = new TrackLocalDataSource(contentResolver);
        return sTrackLocalDataSource;
    }

    @Override
    public void getLocalTrack(OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener) {
        new GetLocalTrackTask(mContentResolver, trackOnLoadDataCompleteListener).execute();
    }
}
