package com.framgia.nguyenvanducc.soundcloud33.data.model;

public class Track {
    private int mId;
    private int mUserId;
    private String mTitle;
    private String mUrl;
    private String mArtworkUrl;
    private long mDuration;
    private String mGenre;
    private boolean mIsDownloadable;
    private boolean mIsStreamable;
    private String mDownloadUrl;
    private String mStreamUrl;
    private String mArtist;
    private boolean mIsFavorite;

    private Track(Builder builder) {
        mId = builder.mId;
        mUserId = builder.mUserId;
        mTitle = builder.mTitle;
        mUrl = builder.mUrl;
        mArtworkUrl = builder.mArtworkUrl;
        mDuration = builder.mDuration;
        mGenre = builder.mGenre;
        mIsDownloadable = builder.mIsDownloadable;
        mIsStreamable = builder.mIsStreamable;
        mDownloadUrl = builder.mDownloadUrl;
        mStreamUrl = builder.mStreamUrl;
        mArtist = builder.mArtist;
        mIsFavorite = builder.mIsFavorite;
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

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
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

    public boolean isDownloadable() {
        return mIsDownloadable;
    }

    public boolean isStreamable() {
        return mIsStreamable;
    }

    public void setStreamable(boolean streamable) {
        mIsStreamable = streamable;
    }

    public void setDownloadable(boolean downloadable) {
        mIsDownloadable = downloadable;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        mDownloadUrl = downloadUrl;
    }

    public String getStreamUrl() {
        return mStreamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        mStreamUrl = streamUrl;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setFavorite(boolean favorite) {
        mIsFavorite = favorite;
    }

    public static class Builder {
        private int mId;
        private int mUserId;
        private String mTitle;
        private String mUrl;
        private String mArtworkUrl;
        private long mDuration;
        private String mGenre;
        private boolean mIsDownloadable;
        private boolean mIsStreamable;
        private String mDownloadUrl;
        private String mStreamUrl;
        private String mArtist;
        private boolean mIsFavorite;

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setUserId(int userId) {
            mUserId = userId;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setUrl(String url) {
            mUrl = url;
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

        public Builder setDownloadable(boolean downloadable) {
            mIsDownloadable = downloadable;
            return this;
        }

        public Builder setStreamable(boolean streamable) {
            mIsStreamable = streamable;
            return this;
        }

        public Builder setDownloadUrl(String downloadUrl) {
            mDownloadUrl = downloadUrl;
            return this;
        }

        public Builder setStreamUrl(String streamUrl) {
            mStreamUrl = streamUrl;
            return this;
        }
        public Builder setArtist(String artist) {
            mArtist = artist;
            return this;
        }

        public Builder setFavorite(boolean favorite) {
            mIsFavorite = favorite;
            return this;
        }

        public Track build() {
            return new Track(this);
        }
    }
}
