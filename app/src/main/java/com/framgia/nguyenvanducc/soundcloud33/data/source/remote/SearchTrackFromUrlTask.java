package com.framgia.nguyenvanducc.soundcloud33.data.source.remote;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.source.BaseHttpTask;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchTrackFromUrlTask extends BaseHttpTask<Track> {
    private static final String COLLECTION_ENTITY = "collection";
    private static final String ID_ENTITY = "id";
    private static final String USER_ID_ENTITY = "user_id";
    private static final String TITLE_ENTITY = "title";
    private static final String URL_ENTITY = "uri";
    private static final String ARTWORK_ENTITY = "artwork_url";
    private static final String DURATION__ENTITY = "duration";
    private static final String GENRE_ENTITY = "genre";
    private static final String IS_DOWNLOADABLE_ENTITY = "downloadable";
    private static final String DOWNLOAD_URL_ENTITY = "download_url";
    private static final String USER_ENTITY = "user";
    private static final String USERNAME_ENTITY = "username";

    public SearchTrackFromUrlTask(OnLoadDataCompleteListener<Track> onLoadDataCompleteListener) {
        super(onLoadDataCompleteListener);
    }

    @Override
    public List<Track> getJsonDataFromString(String string) throws JSONException {
        List<Track> tracks = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(string);
        JSONArray trackJsonArray = jsonObject.getJSONArray(COLLECTION_ENTITY);
        for (int i = 0; i < trackJsonArray.length(); i++) {
            JSONObject trackJson = trackJsonArray.getJSONObject(i);
            int id = trackJson.getInt(ID_ENTITY);
            int userId = trackJson.getInt(USER_ID_ENTITY);
            String title = trackJson.getString(TITLE_ENTITY);
            String url = trackJson.getString(URL_ENTITY);
            String artworkUrl = trackJson.getString(ARTWORK_ENTITY);
            artworkUrl = artworkUrl == null ? Constants.NULL : artworkUrl;
            long duration = trackJson.getLong(DURATION__ENTITY);
            String genre = trackJson.getString(GENRE_ENTITY);
            boolean isDownloadable = trackJson.getBoolean(IS_DOWNLOADABLE_ENTITY);
            String downloadUrl = isDownloadable ?
                    trackJson.getString(DOWNLOAD_URL_ENTITY) : null;
            JSONObject user = trackJson.getJSONObject(USER_ENTITY);
            String artist = user.getString(USERNAME_ENTITY);
            Track track = new Track.Builder()
                    .setId(id)
                    .setUserId(userId)
                    .setTitle(title)
                    .setUrl(url)
                    .setArtworkUrl(artworkUrl)
                    .setDuration(duration)
                    .setGenre(genre)
                    .setDownloadable(isDownloadable)
                    .setDownloadUrl(downloadUrl)
                    .setArtist(artist)
                    .build();
            tracks.add(track);
        }
        return tracks;
    }
}
