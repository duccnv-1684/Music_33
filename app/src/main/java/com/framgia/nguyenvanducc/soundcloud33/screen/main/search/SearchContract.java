package com.framgia.nguyenvanducc.soundcloud33.screen.main.search;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface SearchContract {
    interface View {
        void showSearchResult(List<Track> tracks);
    }

    interface Presenter extends BasePresenter<SearchContract.View> {
        void searchTrack(String searchString);
    }
}
