package com.framgia.nguyenvanducc.soundcloud33.utils;

import android.support.annotation.StringDef;

@StringDef({
        TrackEntity.ID,
        TrackEntity.TITLE,
        TrackEntity.ARTIST,
        TrackEntity.ARTWORK_URL,
        TrackEntity.FULL_DURATION,
        TrackEntity.URL,
        TrackEntity.IS_DOWNLOADABLE
})

public @interface TrackEntity {
    String ID = "id";
    String TITLE = "title";
    String ARTIST = "artist";
    String ARTWORK_URL = "artwork_url";
    String FULL_DURATION = "duration";
    String URL = "url";
    String IS_DOWNLOADABLE = "is_downloadable";
}
