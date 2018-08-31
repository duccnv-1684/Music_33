package com.framgia.nguyenvanducc.soundcloud33.data.model;

import java.util.List;

public class Playlist {
    private int mId;
    private int mUserId;
    private int mTrackCount;
    private String mTitle;
    private String mUri;
    private String mArtworkUrl;
    private long mDuration;
    private String mGenre;
    private boolean mIsStreamable;
    private boolean mIsDownloadable;
    private List<Track> mTracks;

    private Playlist(Builder builder) {
        mId = builder.mId;
        mUserId = builder.mUserId;
        mTrackCount = builder.mTrackCount;
        mTitle = builder.mTitle;
        mUri = builder.mUri;
        mArtworkUrl = builder.mArtworkUrl;
        mDuration = builder.mDuration;
        mGenre = builder.mGenre;
        mIsStreamable = builder.mIsStreamable;
        mIsDownloadable = builder.mIsDownloadable;
        mTracks = builder.mTracks;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getTrackCount() {
        return mTrackCount;
    }

    public void setTrackCount(int trackCount) {
        mTrackCount = trackCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public boolean isStreamable() {
        return mIsStreamable;
    }

    public void setStreamable(boolean streamable) {
        mIsStreamable = streamable;
    }

    public boolean isDownloadable() {
        return mIsDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        mIsDownloadable = downloadable;
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    public void setTracks(List<Track> tracks) {
        mTracks = tracks;
    }

    private static class Builder {
        private int mId;
        private int mUserId;
        private int mTrackCount;
        private String mTitle;
        private String mUri;
        private String mArtworkUrl;
        private long mDuration;
        private String mGenre;
        private boolean mIsStreamable;
        private boolean mIsDownloadable;
        private List<Track> mTracks;

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setUserId(int userId) {
            mUserId = userId;
            return this;
        }

        public Builder setTrackCount(int trackCount) {
            mTrackCount = trackCount;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setUri(String uri) {
            mUri = uri;
            return this;
        }

        public Builder setArtworkUrl(String artworkUrl) {
            mArtworkUrl = artworkUrl;
            return this;
        }

        public Builder setDuration(long duration) {
            mDuration = duration;
            return this;
        }

        public Builder setGenre(String genre) {
            mGenre = genre;
            return this;
        }

        public Builder setStreamable(boolean streamable) {
            mIsStreamable = streamable;
            return this;
        }

        public Builder setDownloadable(boolean downloadable) {
            mIsDownloadable = downloadable;
            return this;
        }

        public Builder setTracks(List<Track> tracks) {
            mTracks = tracks;
            return this;
        }

        public Playlist build() {
            return new Playlist(this);
        }
    }
}
