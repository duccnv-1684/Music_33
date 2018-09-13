package com.framgia.nguyenvanducc.soundcloud33.screen.main.genredetail;

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
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;

import java.util.List;

public class GenreDetailFragment extends BaseFragment implements GenreDetailContract.View,
        GenreDetailAdapter.TrackClickListener {
    public static final String TAG = "genre_detail";
    private GenreDetailContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrackList;

    public GenreDetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TrackRepository trackRepository = TrackRepository.getInstance(context);
        mPresenter = new GenreDetailPresenter(trackRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_track, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mRecyclerTrackList = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTrackList.setLayoutManager(linearLayoutManager);
        assert getArguments() != null;
        String url = getArguments().getString(Constants.ARGUMENT_GENRE_URL);
        mPresenter.getTrack(url);
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
