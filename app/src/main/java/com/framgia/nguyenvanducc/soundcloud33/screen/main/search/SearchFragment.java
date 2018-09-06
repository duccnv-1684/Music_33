package com.framgia.nguyenvanducc.soundcloud33.screen.main.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.TrackLocalDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.remote.TrackRemoteDataSource;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.MainActivity;

import java.util.List;

public class SearchFragment extends BaseFragment implements SearchContract.View,
        SearchAdapter.TrackClickListener {
    private SearchContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrackList;

    public SearchFragment() {
        TrackRepository trackRepository = TrackRepository.getInstance(
                TrackLocalDataSource.getInstance(getContext().getContentResolver()),
                TrackRemoteDataSource.getInstance());
        mPresenter = new SearchPresenter(trackRepository);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mPresenter.setView(this);
        mRecyclerTrackList = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTrackList.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void showSearchResult(List<Track> tracks) {
        SearchAdapter searchAdapter = new SearchAdapter(getContext(), tracks, this);
        mRecyclerTrackList.setAdapter(searchAdapter);
    }

    @Override
    public void onTrackClick(Track track) {
        Toast.makeText(getContext(), track.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavoriteClick(Track track) {
    }

    @Override
    public void onMoreClick(Track track) {
    }

}
