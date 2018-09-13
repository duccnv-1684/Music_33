package com.framgia.nguyenvanducc.soundcloud33.screen.main.downloaded;

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
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;

import java.util.List;

public class DownloadedFragment extends BaseFragment implements DownloadedContract.View,
        DownloadedAdapter.TrackClickListener {
    public static final String TAG = "download";
    private DownloadedContract.Presenter mPresenter;
    private RecyclerView mRecyclerTracks;

    public DownloadedFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TrackRepository trackRepository = TrackRepository.getInstance(context);
        mPresenter = new DownloadedPresenter(trackRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_downloaded, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mRecyclerTracks = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTracks.setLayoutManager(linearLayoutManager);
        mPresenter.getAllDownloadedTrack();
    }

    @Override
    public void onTrackClick(Track track) {
    }

    @Override
    public void onFavoriteClick(Track track) {
    }

    @Override
    public void onMoreClick(Track track) {
    }

    @Override
    public void showDownloadedTrack(List<Track> tracks) {
        DownloadedAdapter downloadedAdapter = new DownloadedAdapter(getContext(), tracks, this);
        mRecyclerTracks.setAdapter(downloadedAdapter);
    }
}
