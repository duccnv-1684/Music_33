package com.framgia.nguyenvanducc.soundcloud33.utils;

import com.framgia.nguyenvanducc.soundcloud33.BuildConfig;

import java.util.Calendar;

public class StringUtils {
    private static final String BASE_URL = "https://api-v2.soundcloud.com/";
    private static final String GENRE_QUERY_URl = "charts?kind=top&genre=soundcloud%3Agenres%3A";
    private static final String TRACK_SEARCH_URL = "search/tracks?facet=genre&linked_partitioning=1&q";
    private static final String CLIENT_ID_URL = "client_id";
    private static final String LIMIT_URL = "limit";
    private static final String OFFSET_URL = "offset";
    private static final String PLAYLIST = "playlist";
    private static final char AND = '&';
    private static final char EQUAL = '=';
    private static final long BASE_TIMESTAMP = 1136073600;

    public static String buildGetTrackByGenreUrl(String genreUrl, int limit, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append(GENRE_QUERY_URl);
        stringBuilder.append(genreUrl);
        stringBuilder.append(AND);
        stringBuilder.append(CLIENT_ID_URL);
        stringBuilder.append(EQUAL);
        stringBuilder.append(BuildConfig.SOUNDCLOUD_API_KEY);
        stringBuilder.append(AND);
        stringBuilder.append(LIMIT_URL);
        stringBuilder.append(EQUAL);
        stringBuilder.append(limit);
        stringBuilder.append(AND);
        stringBuilder.append(OFFSET_URL);
        stringBuilder.append(EQUAL);
        stringBuilder.append(offset);
        return stringBuilder.toString();
    }

    public static String buildSearchTrackUrl(String queryString, int limit, int offset) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append(TRACK_SEARCH_URL);
        stringBuilder.append(EQUAL);
        stringBuilder.append(queryString);
        stringBuilder.append(AND);
        stringBuilder.append(LIMIT_URL);
        stringBuilder.append(EQUAL);
        stringBuilder.append(limit);
        stringBuilder.append(AND);
        stringBuilder.append(OFFSET_URL);
        stringBuilder.append(EQUAL);
        stringBuilder.append(offset);
        stringBuilder.append(AND);
        stringBuilder.append(CLIENT_ID_URL);
        stringBuilder.append(EQUAL);
        stringBuilder.append(BuildConfig.SOUNDCLOUD_API_KEY);
        return stringBuilder.toString();
    }

    public static String buildString(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    public static String buildTableNameOfPlaylist(int playlistId) {
        return buildString(PLAYLIST, String.valueOf(playlistId));
    }

    public static String buildSqlCreatePlaylistTableStatement(int playlistId) {
        return buildString("CREATE TABLE ", buildTableNameOfPlaylist(playlistId), " (",
                TrackEntity.ID, " INTEGER NOT NULL PRIMARY KEY, ",
                TrackEntity.TITLE, " TEXT, ",
                TrackEntity.ARTIST, " TEXT, ",
                TrackEntity.ARTWORK_URL, " TEXT, ",
                TrackEntity.FULL_DURATION, " INTEGER, ",
                TrackEntity.IS_DOWNLOADABLE, " INTEGER, ",
                TrackEntity.URL, " TEXT );");
    }

    public static String buildSqlDropPlaylistTableStatement(int playlistId) {
        return buildString("DROP TABLE ", buildTableNameOfPlaylist(playlistId));
    }

    public static int createPlaylistId() {
        long currentTimeStamp = Calendar.getInstance().getTimeInMillis() / 1000;
        return (int) (currentTimeStamp - BASE_TIMESTAMP);
    }
}
