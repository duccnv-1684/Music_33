package com.framgia.nguyenvanducc.soundcloud33.screen.main.genredetail;

import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

import java.util.List;

public interface GenreDetailContract {
    interface View{
        void showTrack(List<Track> tracks);
    }
    interface Presenter extends BasePresenter<GenreDetailContract.View>{
        void getTrack(String genreUrl);
    }
}
