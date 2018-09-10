package com.framgia.nguyenvanducc.soundcloud33.data.model;

public class Playlist {
    private int mId;
    private String mTitle;

    private Playlist(Builder builder) {
        mId = builder.mId;
        mTitle = builder.mTitle;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static class Builder {
        private int mId;
        private String mTitle;

        public Builder setId(int id) {
            mId = id;
            return this;
        }
        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Playlist build() {
            return new Playlist(this);
        }
    }
}
