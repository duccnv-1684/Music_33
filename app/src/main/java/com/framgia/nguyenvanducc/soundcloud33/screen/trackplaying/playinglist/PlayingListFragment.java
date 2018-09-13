package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying.playinglist;

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
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.PlaylistRepository;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;

import java.util.List;

public class PlayingListFragment extends BaseFragment implements PlayingListContract.View,
        PlayingListAdapter.TrackClickListener {
    private PlayingListContract.Presenter mPresenter;
    private PlayingListAdapter mPlayingListAdapter;

    public PlayingListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PlaylistRepository playlistRepository = PlaylistRepository.getInstance(context);
        mPresenter = new PlayingListPresenter(playlistRepository);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playing_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        RecyclerView recyclerTracks = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerTracks.setLayoutManager(linearLayoutManager);
        mPlayingListAdapter = new PlayingListAdapter(getContext(), this);
        recyclerTracks.setAdapter(mPlayingListAdapter);
        mPresenter.getTrack();
    }

    @Override
    public void showTrack(List<Track> tracks) {
        mPlayingListAdapter.addData(tracks);
    }

    @Override
    public void onTrackClick(int position) {

    }
}
