package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying.trackdetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;
import com.framgia.nguyenvanducc.soundcloud33.utils.LoopMode;
import com.framgia.nguyenvanducc.soundcloud33.utils.ShuffleMode;

public interface TrackDetailContract {
    interface View {
        void updatePlayingTrack(Track track, boolean isPlaying
                , @LoopMode int loopMode, @ShuffleMode int shuffleMode);

        void updatePlayingInfo(Track track);

        void updateFavorite(boolean isFavorite);

        void updateDownload(boolean isDownloadable);

        void updateShuffle(@ShuffleMode int shuffleMode);

        void updateLoop(@LoopMode int loopMode);

        void updatePlayingStatus(boolean isPlaying);
    }

    interface Presenter extends BasePresenter<TrackDetailContract.View> {
        void playNewTrack(Track track);

        boolean checkTrackIsInFavorite(Track track);

        void setFavorite();

        void downloadTrack();

        void setShuffle();

        void skipPrevious();

        void changePlayingStatus();

        void skipNext();

        void setLoop();
    }
}
