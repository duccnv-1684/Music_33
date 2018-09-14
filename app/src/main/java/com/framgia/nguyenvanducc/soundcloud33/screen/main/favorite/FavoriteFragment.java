package com.framgia.nguyenvanducc.soundcloud33.screen.main.favorite;

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

public class FavoriteFragment extends BaseFragment implements FavoriteContract.View,
        FavoriteAdapter.TrackClickListener {
    public static final String TAG = "favorite";
    private FavoriteContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrackList;
    private FavoriteAdapter mFavoriteAdapter;

    public FavoriteFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TrackRepository trackRepository = TrackRepository.getInstance(context);
        mPresenter = new FavoritePresenter(trackRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mRecyclerTrackList = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTrackList.setLayoutManager(linearLayoutManager);
        mPresenter.getAllFavoriteTrack();
    }

    @Override
    public void onTrackClick(Track track) {
    }

    @Override
    public void onFavoriteClick(Track track) {
        mPresenter.removeFavoriteTrack(track);
    }

    @Override
    public void onMoreClick(Track track) {
    }

    @Override
    public void showFavoriteTrack(List<Track> tracks) {
        mFavoriteAdapter = new FavoriteAdapter(getContext(), tracks, this);
        mRecyclerTrackList.setAdapter(mFavoriteAdapter);
    }

    @Override
    public void removeTrack(Track track) {
        mFavoriteAdapter.removeTrack(track);
    }

}
