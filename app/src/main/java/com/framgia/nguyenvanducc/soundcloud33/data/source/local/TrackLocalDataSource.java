package com.framgia.nguyenvanducc.soundcloud33.data.source.local;

import com.framgia.nguyenvanducc.soundcloud33.data.source.TrackDataSource;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {
    private static TrackLocalDataSource sTrackLocalDataSource;
    private TrackLocalDataSource(){

    }
    public static synchronized TrackLocalDataSource getInstance(){
        if (sTrackLocalDataSource==null)sTrackLocalDataSource = new TrackLocalDataSource();
        return sTrackLocalDataSource;
    }
}
