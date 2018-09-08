package com.framgia.nguyenvanducc.soundcloud33.data.source.local;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;
import com.framgia.nguyenvanducc.soundcloud33.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GetLocalTrackTask extends AsyncTask<Void, Void, List<Track>> {
    private ContentResolver mContentResolver;
    private OnLoadDataCompleteListener<Track> mTrackOnLoadDataCompleteListener;
    private static final String NOT_EQUAL_ZERO = " != 0";
    private static final String ASCENDING = " ASC";

    public GetLocalTrackTask(ContentResolver contentResolver
            , OnLoadDataCompleteListener<Track> trackOnLoadDataCompleteListener) {
        mContentResolver = contentResolver;
        this.mTrackOnLoadDataCompleteListener = trackOnLoadDataCompleteListener;
    }

    @Override
    protected List<Track> doInBackground(Void... voids) {
        List<Track> tracks = new ArrayList<>();
        Uri mediaUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = StringUtils.buildString(MediaStore.Audio.Media.IS_MUSIC,
                NOT_EQUAL_ZERO);
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
        };
        String sortOrder = StringUtils.buildString(MediaStore.Audio.Media.TITLE, ASCENDING);
        Cursor cursor = mContentResolver.query(
                mediaUri
                , projection
                , selection
                , null
                , sortOrder);
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String title
                        = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist
                        = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String data
                        = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long duration
                        = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                Track track = new Track.Builder()
                        .setTitle(title)
                        .setArtist(artist)
                        .setUrl(data)
                        .setDuration(duration)
                        .build();
                tracks.add(track);
            }
            cursor.close();
        }
        return tracks;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        mTrackOnLoadDataCompleteListener.onSuccess(tracks);
    }
}
