package com.framgia.nguyenvanducc.soundcloud33.screen.main.localtrack;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface LocalTrackContract {
    interface View {
        void showLocalTrack(List<Track> tracks);
    }

    interface Presenter extends BasePresenter<LocalTrackContract.View> {
        void getLocalTrack();
    }
}
