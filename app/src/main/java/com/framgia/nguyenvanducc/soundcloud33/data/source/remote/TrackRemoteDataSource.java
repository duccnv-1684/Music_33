package com.framgia.nguyenvanducc.soundcloud33.data.source.remote;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;
import com.framgia.nguyenvanducc.soundcloud33.data.source.TrackDataSource;
import com.framgia.nguyenvanducc.soundcloud33.utils.StringUtils;

public class TrackRemoteDataSource implements TrackDataSource.RemoteDataSource {
    private static TrackRemoteDataSource sTrackRemoteDataSource;

    private TrackRemoteDataSource() {

    }

    public static TrackRemoteDataSource getInstance() {
        if (sTrackRemoteDataSource == null) sTrackRemoteDataSource = new TrackRemoteDataSource();
        return sTrackRemoteDataSource;
    }

    @Override
    public void getTrackOfGenre(String genre, int limit, int offset
            , OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener) {
        new GetTrackFromUrlTask(trackOnLoadDataCompleteListener)
                .execute(StringUtils.buildGetTrackByGenreUrl(genre, limit, offset));
    }
}
