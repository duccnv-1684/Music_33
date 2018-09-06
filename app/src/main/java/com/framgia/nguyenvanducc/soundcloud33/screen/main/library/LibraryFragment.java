package com.framgia.nguyenvanducc.soundcloud33.screen.main.library;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;

public class LibraryFragment extends BaseFragment implements LibraryContract.View,
        View.OnClickListener {
    private LibraryContract.Presenter mPresenter;
    private OnItemSelected mOnItemSelected;

    public LibraryFragment() {
        mPresenter = new LibraryPresenter();
    }

    public void setOnItemSelected(OnItemSelected onItemSelected) {
        mOnItemSelected = onItemSelected;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        mPresenter.setView(this);
        setUpView(view);
        return view;
    }

    @Override
    public void switchToLibrary(int key) {
        mOnItemSelected.switchToLibrary(key);
    }

    private void setUpView(View view) {
        int[] ids = {R.id.card_music_on_device, R.id.card_playlist,
                R.id.card_favorite, R.id.card_downloaded};
        for (int id : ids) {
            CardView cardView = view.findViewById(id);
            cardView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_music_on_device:
                mPresenter.switchToMusicOnDevice();
                break;
            case R.id.card_playlist:
                mPresenter.switchToPlaylist();
                break;
            case R.id.card_favorite:
                mPresenter.switchToFavorite();
                break;
            case R.id.card_downloaded:
                mPresenter.switchToDownloaded();
                break;
            default:
                break;
        }
    }

    public interface OnItemSelected {
        void switchToLibrary(int key);
    }
}
