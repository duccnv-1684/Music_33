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
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;

import java.util.List;

public class SearchFragment extends BaseFragment implements SearchContract.View,
        SearchAdapter.TrackClickListener {
    public static final String TAG = "search_fragment";
    private SearchContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrackList;

    public SearchFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TrackRepository trackRepository = TrackRepository.getInstance(getContext());
        mPresenter = new SearchPresenter(trackRepository);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        mRecyclerTrackList = view.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerTrackList.setLayoutManager(linearLayoutManager);
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

    public void querySubmit(String queryString){
        mPresenter.searchTrack(queryString);
    }
}
