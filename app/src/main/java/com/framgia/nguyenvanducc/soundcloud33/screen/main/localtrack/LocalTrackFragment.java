package com.framgia.nguyenvanducc.soundcloud33.screen.main.localtrack;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
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

public class LocalTrackFragment extends BaseFragment implements LocalTrackContract.View
        , LocalTrackAdapter.TrackClickListener {
    private LocalTrackContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrackList;

    public LocalTrackFragment() {
        TrackRepository trackRepository = TrackRepository.getInstance(getContext());
        mPresenter = new LocalTrackPresenter(trackRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_on_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mRecyclerTrackList = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTrackList.setLayoutManager(linearLayoutManager);
        if (isGrantedPermission()) mPresenter.getLocalTrack();
    }

    private boolean isGrantedPermission() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLocalTrack(List<Track> tracks) {
        LocalTrackAdapter localTrackAdapter
                = new LocalTrackAdapter(getContext(), tracks, this);
        mRecyclerTrackList.setAdapter(localTrackAdapter);
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
}
