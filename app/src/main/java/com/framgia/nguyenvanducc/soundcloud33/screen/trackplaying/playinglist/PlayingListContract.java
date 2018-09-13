package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying.playinglist;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public class PlayingListContract {
    interface View {
        void showTrack(List<Track> tracks);
    }

    interface Presenter extends BasePresenter<PlayingListContract.View> {
        void getTrack();
    }
}
