package com.framgia.nguyenvanducc.soundcloud33.screen;

public interface BasePresenter<T> {
    void setView(T view);
    void onStart();
    void onStop();
}
