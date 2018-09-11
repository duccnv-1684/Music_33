package com.framgia.nguyenvanducc.soundcloud33.screen.main.playlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;
import com.framgia.nguyenvanducc.soundcloud33.utils.StringUtils;

import java.util.List;

public class PlaylistFragment extends BaseFragment implements PlaylistContract.View,
        PlaylistAdapter.PlaylistClickListener, View.OnClickListener {
    private PlaylistContract.Presenter mPresenter;
    private RecyclerView mRecyclerPlaylists;
    private PlaylistAdapter mPlaylistAdapter;
    private FloatingActionButton mButtonInsertPlaylist;
    private OnPlaylistSelectListener mListener;

    public PlaylistFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PlaylistRepository playlistRepository = PlaylistRepository.getInstance(context);
        mPresenter = new PlaylistPresenter(playlistRepository);
        mListener = (OnPlaylistSelectListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mRecyclerPlaylists = view.findViewById(R.id.recycler_list_playlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerPlaylists.setLayoutManager(linearLayoutManager);
        mButtonInsertPlaylist = view.findViewById(R.id.fab_insert_playlist);
        mButtonInsertPlaylist.setOnClickListener(this);
        mPresenter.getAllPlaylist();
    }

    @Override
    public void showPlaylist(List<Playlist> playlists) {
        mPlaylistAdapter = new PlaylistAdapter(getContext(), playlists, this);
        mRecyclerPlaylists.setAdapter(mPlaylistAdapter);
    }

    @Override
    public void insertPlaylist(Playlist playlist) {
        mPlaylistAdapter.insertPlaylist(playlist);
    }

    @Override
    public void removePlaylist(Playlist playlist) {
        mPlaylistAdapter.removePlaylist(playlist);
    }

    @Override
    public void onPlaylistClick(Playlist playlist) {
        mListener.onPlaylistSelect(playlist);
    }


    @Override
    public void onMoreClick(Playlist playlist) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_insert_playlist:
                break;
            default:
                break;
        }
    }

    public interface OnPlaylistSelectListener {
        void onPlaylistSelect(Playlist playlist);
    }
}
