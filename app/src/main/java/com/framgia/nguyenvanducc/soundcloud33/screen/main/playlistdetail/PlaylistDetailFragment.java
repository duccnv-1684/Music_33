package com.framgia.nguyenvanducc.soundcloud33.screen.main.playlistdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;

import java.util.List;

public class PlaylistDetailFragment extends BaseFragment implements PlaylistDetailContract.View,
        PlaylistDetailAdapter.TrackClickListener {
    private PlaylistDetailContract.Presenter mPresenter;
    private RecyclerView mRecyclerTracks;
    private PlaylistDetailAdapter mAdapter;
    private Playlist mPlaylist;

    public PlaylistDetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PlaylistRepository playlistRepository = PlaylistRepository.getInstance(context);
        mPresenter = new PlaylistDetailPresenter(playlistRepository);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mRecyclerTracks = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTracks.setLayoutManager(linearLayoutManager);
        assert getArguments() != null;
        int id = getArguments().getInt(Constants.ARGUMENT_PLAYLIST_ID);
        String title = getArguments().getString(Constants.ARGUMENT_PLAYLIST_TITLE);
        mPlaylist = new Playlist.Builder().setId(id).setTitle(title).build();
        mPresenter.getAllPlayListTrack(mPlaylist);
    }

    @Override
    public void onTrackClick(Track track) {

    }

    @Override
    public void onFavoriteClick(Track track) {

    }

    @Override
    public void onMoreClick(Track track) {
        mPresenter.removeTrack(mPlaylist, track);
    }

    @Override
    public void showAllPlaylistTrack(List<Track> tracks) {
        mAdapter = new PlaylistDetailAdapter(getContext(), tracks, this);
        mRecyclerTracks.setAdapter(mAdapter);
    }

    @Override
    public void removeTrack(Track track) {
        mAdapter.removeTrack(track);
    }
}
