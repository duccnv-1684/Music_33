package com.framgia.nguyenvanducc.soundcloud33.data.source.local.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.utils.StringUtils;
import com.framgia.nguyenvanducc.soundcloud33.utils.TrackEntity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String EQUAL_CONDITION = " = ? ";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sound_cloud.db";
    private static final String PLAYLIST_ID = "id";
    private static final String PLAYLIST_TITLE = "title";
    private static final String DOWNLOADED_TABLE_NAME = "downloaded";
    private static final String FAVORITE_TABLE_NAME = "favorite";
    private static final String PLAYLIST_TABLE_NAME = "playlist";
    private static final String PLAYING_QUEUE_TABLE_NAME = "playing_queue";
    private static final String SQL_CREATE_DOWNLOADED_TABLE_STATEMENT =
            "CREATE TABLE " + DOWNLOADED_TABLE_NAME + " (" +
                    TrackEntity.ID + " INTEGER NOT NULL PRIMARY KEY, " +
                    TrackEntity.TITLE + " TEXT, " +
                    TrackEntity.ARTIST + " TEXT, " +
                    TrackEntity.ARTWORK_URL + " TEXT, " +
                    TrackEntity.FULL_DURATION + " INTEGER, " +
                    TrackEntity.IS_DOWNLOADABLE + " INTEGER, " +
                    TrackEntity.URL + " TEXT );";
    private static final String SQL_CREATE_FAVORITE_TABLE_STATEMENT =
            "CREATE TABLE " + FAVORITE_TABLE_NAME + " (" +
                    TrackEntity.ID + " INTEGER NOT NULL PRIMARY KEY, " +
                    TrackEntity.TITLE + " TEXT, " +
                    TrackEntity.ARTIST + " TEXT, " +
                    TrackEntity.ARTWORK_URL + " TEXT, " +
                    TrackEntity.FULL_DURATION + " INTEGER, " +
                    TrackEntity.IS_DOWNLOADABLE + " INTEGER, " +
                    TrackEntity.URL + " TEXT );";
    private static final String SQL_CREATE_PLAYLIST_TABLE_STATEMENT =
            "CREATE TABLE " + PLAYLIST_TABLE_NAME + " (" +
                    PLAYLIST_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    PLAYLIST_TITLE + " TEXT );";
    private static final String SQL_CREATE_PLAYING_QUEUE_TABLE_STATEMENT =
            "CREATE TABLE " + PLAYING_QUEUE_TABLE_NAME + " (" +
                    TrackEntity.ID + " INTEGER NOT NULL PRIMARY KEY, " +
                    TrackEntity.TITLE + " TEXT, " +
                    TrackEntity.ARTIST + " TEXT, " +
                    TrackEntity.ARTWORK_URL + " TEXT, " +
                    TrackEntity.FULL_DURATION + " INTEGER, " +
                    TrackEntity.IS_DOWNLOADABLE + " INTEGER, " +
                    TrackEntity.URL + " TEXT );";
    private static final String SQL_DROP_PLAYING_QUEUE_TABLE_STATEMENT =
            "DROP TABLE " + PLAYING_QUEUE_TABLE_NAME;

    private static DatabaseHelper sDatabaseHelper;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sDatabaseHelper == null) sDatabaseHelper = new DatabaseHelper(context);
        return sDatabaseHelper;
    }


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DOWNLOADED_TABLE_STATEMENT);
        db.execSQL(SQL_CREATE_FAVORITE_TABLE_STATEMENT);
        db.execSQL(SQL_CREATE_PLAYLIST_TABLE_STATEMENT);
        db.execSQL(SQL_CREATE_PLAYING_QUEUE_TABLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Track> getAllDownloadedTrack() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DOWNLOADED_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        List<Track> tracks = new ArrayList<>();
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                tracks.add(parseTrackFromCursor(cursor));
            }
            cursor.close();
        }
        database.close();
        return tracks;
    }

    public List<Track> getAllFavoriteTrack() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(FAVORITE_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        List<Track> tracks = new ArrayList<>();
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                tracks.add(parseTrackFromCursor(cursor));
            }
            cursor.close();
        }
        database.close();
        return tracks;
    }

    public void insertDownloadedTrack(Track track) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = createTrackContentValue(track);
        database.insert(DOWNLOADED_TABLE_NAME, null, values);
        database.close();
    }

    public void insertFavoriteTrack(Track track) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = createTrackContentValue(track);
        database.insert(FAVORITE_TABLE_NAME, null, values);
        database.close();
    }

    public void removeDownloadedTrack(Track track) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = StringUtils.buildString(TrackEntity.ID, EQUAL_CONDITION);
        String[] selectionArgs = new String[]{String.valueOf(track.getId())};
        database.delete(DOWNLOADED_TABLE_NAME, selection, selectionArgs);
        database.close();
    }

    public void removeFavoriteTrack(Track track) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = StringUtils.buildString(TrackEntity.ID, EQUAL_CONDITION);
        String[] selectionArgs = new String[]{String.valueOf(track.getId())};
        database.delete(FAVORITE_TABLE_NAME, selection, selectionArgs);
        database.close();
    }

    public boolean isTrackInDownloaded(Track track) {
        SQLiteDatabase database = getReadableDatabase();
        String selection = StringUtils.buildString(TrackEntity.ID, EQUAL_CONDITION);
        String[] selectionArgs = new String[]{String.valueOf(track.getId())};
        Cursor cursor = database.query(DOWNLOADED_TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        boolean isExist = false;
        if (cursor != null && cursor.getCount() > 0) {
            isExist = true;
            cursor.close();
        }
        database.close();
        return isExist;
    }

    public boolean isTrackInFavorite(Track track) {
        SQLiteDatabase database = getReadableDatabase();
        String selection = StringUtils.buildString(TrackEntity.ID, EQUAL_CONDITION);
        String[] selectionArgs = new String[]{String.valueOf(track.getId())};
        Cursor cursor = database.query(FAVORITE_TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        boolean isExist = false;
        if (cursor != null && cursor.getCount() > 0) {
            isExist = true;
            cursor.close();
        }
        database.close();
        return isExist;
    }

    public List<Playlist> getAllPlaylist() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(PLAYLIST_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        List<Playlist> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                list.add(parsePlaylistFromCursor(cursor));
            }
            cursor.close();
        }
        database.close();
        return list;
    }

    public void insertPlaylist(Playlist playlist) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYLIST_ID, playlist.getId());
        values.put(PLAYLIST_TITLE, playlist.getTitle());
        database.insert(PLAYLIST_TABLE_NAME, null, values);
        database.execSQL(StringUtils.buildSqlCreatePlaylistTableStatement(playlist.getId()));
        database.close();
    }

    public void removePlaylist(Playlist playlist) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = StringUtils.buildString(PLAYLIST_ID, EQUAL_CONDITION);
        String[] selectionArgs = new String[]{String.valueOf(playlist.getId())};
        database.delete(PLAYLIST_TABLE_NAME, selection, selectionArgs);
        database.execSQL(StringUtils.buildSqlDropPlaylistTableStatement(playlist.getId()));
        database.close();
    }

    public List<Track> getAllTrackOfPlaylist(Playlist playlist) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(StringUtils.buildTableNameOfPlaylist(playlist.getId()),
                null,
                null,
                null,
                null,
                null,
                null);
        List<Track> tracks = new ArrayList<>();
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                tracks.add(parseTrackFromCursor(cursor));
            }
            cursor.close();
        }
        database.close();
        return tracks;
    }

    public void addTrackToPlaylist(Playlist playlist, Track track) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = createTrackContentValue(track);
        database.insert(StringUtils.buildTableNameOfPlaylist(playlist.getId())
                , null, values);
        database.close();
    }

    public void removeTrackFromPlaylist(Playlist playlist, Track track) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = StringUtils.buildString(TrackEntity.ID, EQUAL_CONDITION);
        String[] selectionArgs = new String[]{String.valueOf(track.getId())};
        database.delete(StringUtils.buildTableNameOfPlaylist(playlist.getId())
                , selection, selectionArgs);
        database.close();
    }

    public void newPlayingQueue(List<Track> tracks) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(SQL_DROP_PLAYING_QUEUE_TABLE_STATEMENT);
        database.execSQL(SQL_CREATE_PLAYING_QUEUE_TABLE_STATEMENT);
        for (Track track : tracks) {
            ContentValues values = createTrackContentValue(track);
            database.insert(PLAYING_QUEUE_TABLE_NAME, null, values);
        }
        database.close();
    }

    public List<Track> getTrackInPlayingQueue() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(PLAYING_QUEUE_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        List<Track> tracks = new ArrayList<>();
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                tracks.add(parseTrackFromCursor(cursor));
            }
            cursor.close();
        }
        database.close();
        return tracks;
    }

    private Track parseTrackFromCursor(Cursor cursor) {
        Track track;
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(TrackEntity.ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.TITLE));
        String artwork = cursor.getString(
                cursor.getColumnIndexOrThrow(TrackEntity.ARTWORK_URL));
        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(TrackEntity.FULL_DURATION));
        String url = cursor.getString(cursor.getColumnIndexOrThrow(TrackEntity.URL));
        boolean isDownloadable = (cursor.getInt(
                cursor.getColumnIndexOrThrow(TrackEntity.IS_DOWNLOADABLE)) != 0);
        track = new Track.Builder()
                .setId(id)
                .setTitle(title)
                .setArtworkUrl(artwork)
                .setDuration(duration)
                .setUrl(url)
                .setDownloadable(isDownloadable)
                .build();
        return track;
    }

    private Playlist parsePlaylistFromCursor(Cursor cursor) {
        Playlist playlist;
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(PLAYLIST_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(PLAYLIST_TITLE));
        playlist = new Playlist.Builder()
                .setId(id)
                .setTitle(title)
                .build();
        return playlist;
    }

    private ContentValues createTrackContentValue(Track track) {
        ContentValues values = new ContentValues();
        values.put(TrackEntity.ID, track.getId());
        values.put(TrackEntity.TITLE, track.getTitle());
        values.put(TrackEntity.ARTIST, track.getArtist());
        values.put(TrackEntity.ARTWORK_URL, track.getArtworkUrl());
        values.put(TrackEntity.FULL_DURATION, track.getDuration());
        values.put(TrackEntity.URL, track.getUrl());
        values.put(TrackEntity.IS_DOWNLOADABLE, track.isDownloadable());
        return values;
    }
}
