package com.framgia.nguyenvanducc.soundcloud33.screen.main.genredetail;

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
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;

import java.util.List;

public class GenreDetailFragment extends BaseFragment implements GenreDetailContract.View,
        GenreDetailAdapter.TrackClickListener {
    private GenreDetailContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrackList;

    public GenreDetailFragment() {
        TrackRepository trackRepository = TrackRepository.getInstance(getContext());
        mPresenter = new GenreDetailPresenter(trackRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_track, container, false);
        mPresenter.setView(this);
        mRecyclerTrackList = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTrackList.setLayoutManager(linearLayoutManager);
        assert getArguments() != null;
        String url = getArguments().getString(Constants.ARGUMENT_GENRE_URL);
        mPresenter.getTrack(url);
        return view;
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
    public void showTrack(List<Track> tracks) {
        GenreDetailAdapter genreDetailAdapter = new GenreDetailAdapter(getContext(), tracks, this);
        mRecyclerTrackList.setAdapter(genreDetailAdapter);
    }
}
