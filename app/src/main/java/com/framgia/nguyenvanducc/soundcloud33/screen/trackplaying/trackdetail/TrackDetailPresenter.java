package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying.trackdetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.utils.ShuffleMode;
import com.framgia.nguyenvanducc.soundcloud33.utils.LoopMode;

public class TrackDetailPresenter implements TrackDetailContract.Presenter {
    private TrackRepository mTrackRepository;
    private TrackDetailContract.View mView;
    private Track mPlayingTrack;
    private boolean mIsFavorite;
    private boolean mIsDownloadable;
    private boolean mIsPlaying;
    private int mShuffleMode;
    private int mLoopMode;

    public TrackDetailPresenter(TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
    }

    @Override
    public void setView(TrackDetailContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void playNewTrack(Track track) {
        mPlayingTrack = track;
        mIsFavorite = mTrackRepository.isTrackInFavorite(track);
        mIsDownloadable = track.isDownloadable();
        mView.updatePlayingInfo(track);
    }

    @Override
    public boolean checkTrackIsInFavorite(Track track) {
        return mTrackRepository.isTrackInFavorite(track);
    }

    @Override
    public void setFavorite() {
        mIsFavorite = !mIsFavorite;
        mView.updateFavorite(mIsFavorite);
    }

    @Override
    public void downloadTrack() {
        mView.updateDownload(mIsDownloadable);
    }

    @Override
    public void setShuffle() {
        switch (mShuffleMode) {
            case ShuffleMode.OFF:
                mShuffleMode = ShuffleMode.ON;
                break;
            case ShuffleMode.ON:
                mShuffleMode = ShuffleMode.OFF;
                break;
            default:
                break;
        }
        mView.updateShuffle(mShuffleMode);
    }

    @Override
    public void skipPrevious() {
        mView.updatePlayingInfo(mPlayingTrack);
    }

    @Override
    public void changePlayingStatus() {
        mIsPlaying = !mIsPlaying;
        mView.updatePlayingStatus(mIsPlaying);
    }

    @Override
    public void skipNext() {
        mView.updatePlayingInfo(mPlayingTrack);
    }

    @Override
    public void setLoop() {
        switch (mLoopMode) {
            case LoopMode.NO_LOOP:
                mLoopMode = LoopMode.LOOP_ONE;
                break;
            case LoopMode.LOOP_ONE:
                mLoopMode = LoopMode.LOOP_ALL;
                break;
            case LoopMode.LOOP_ALL:
                mLoopMode = LoopMode.NO_LOOP;
                break;
            default:
                break;
        }
        mView.updateLoop(mLoopMode);
    }
}
