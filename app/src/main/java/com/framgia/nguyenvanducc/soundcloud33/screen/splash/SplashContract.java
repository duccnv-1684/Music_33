package com.framgia.nguyenvanducc.soundcloud33.screen.splash;

import com.framgia.nguyenvanducc.soundcloud33.screen.BasePresenter;

public class SplashContract {
    interface View {
        void switchToMain();
    }

    interface Presenter extends BasePresenter<SplashContract.View> {
        void setUpParameter();
    }
}
