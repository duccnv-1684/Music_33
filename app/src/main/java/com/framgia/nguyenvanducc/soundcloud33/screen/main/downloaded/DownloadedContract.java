package com.framgia.nguyenvanducc.soundcloud33.screen.main.downloaded;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface DownloadedContract {
    interface View {
        void showDownloadedTrack(List<Track> tracks);
    }

    interface Presenter extends BasePresenter<DownloadedContract.View> {
        void getAllDownloadedTrack();
    }
}
