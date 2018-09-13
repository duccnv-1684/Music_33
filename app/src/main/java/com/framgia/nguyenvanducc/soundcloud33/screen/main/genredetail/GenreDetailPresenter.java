package com.framgia.nguyenvanducc.soundcloud33.screen.main.genredetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.OnLoadDataCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class GenreDetailPresenter implements GenreDetailContract.Presenter
        , OnLoadDataCompleteListener<Track> {
    private static final int LIMIT = 20;
    private GenreDetailContract.View mView;
    private TrackRepository mTrackRepository;
    private int mOffset = 0;
    private List<Track> mTracks;

    GenreDetailPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
        mTracks = new ArrayList<>();
    }

    @Override
    public void setView(GenreDetailContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getTrack(String genreUrl) {
        mTrackRepository.getTrackOfGenre(genreUrl, LIMIT, mOffset, this);
    }

    @Override
    public void favoriteTrack(int position) {
        Track track = mTracks.get(position);
        if (track.isFavorite()) mTrackRepository.removeFavoriteTrack(track);
        else mTrackRepository.insertFavoriteTrack(track);
        track.setFavorite(!track.isFavorite());
        mView.updateFavorite(position);
    }

    @Override
    public void downloadTrack(int position) {
        boolean isDownloadable = mTracks.get(position).isDownloadable();
        mView.downloadTrack(isDownloadable, mTracks.get(position));
    }

    @Override
    public void onSuccess(List<Track> data) {
        for (Track track : data) {
            if (mTrackRepository.isTrackInFavorite(track)) track.setFavorite(true);
        }
        mView.showTrack(data);
        mOffset += LIMIT;
        mTracks.addAll(data);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
