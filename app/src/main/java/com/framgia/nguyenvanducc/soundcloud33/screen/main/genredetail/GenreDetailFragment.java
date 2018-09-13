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
import android.widget.Toast;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;
import com.framgia.nguyenvanducc.soundcloud33.utils.EndlessRecyclerViewScrollListener;

import java.util.List;

public class GenreDetailFragment extends BaseFragment implements GenreDetailContract.View,
        GenreDetailAdapter.TrackClickListener {
    public static final String TAG = "genre_detail";
    private GenreDetailContract.Presenter mPresenter;
    private RecyclerView mRecyclerTrackList;
    private GenreDetailAdapter mGenreDetailAdapter;

    public GenreDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mGenreDetailAdapter = new GenreDetailAdapter(getContext(), this);
        mRecyclerTrackList.setAdapter(mGenreDetailAdapter);
        assert getArguments() != null;
        final String url = getArguments().getString(Constants.ARGUMENT_GENRE_URL);
        mPresenter.getTrack(url);
        EndlessRecyclerViewScrollListener scrollListener
                = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.getTrack(url);
            }
        };
        mRecyclerTrackList.addOnScrollListener(scrollListener);
    }

    @Override
    public void onTrackClick(int track) {

    }

    @Override
    public void onFavoriteClick(int position) {
        mPresenter.favoriteTrack(position);
    }

    @Override
    public void onDownloadClick(int position) {
        mPresenter.downloadTrack(position);
    }

    @Override
    public void showTrack(List<Track> tracks) {
        mGenreDetailAdapter.addData(tracks);
    }

    @Override
    public void updateFavorite(int position) {
        mGenreDetailAdapter.updateFavorite(position);
    }

    @Override
    public void downloadTrack(boolean isDownloadable, Track track) {
        if (!isDownloadable) {
            Toast.makeText(getContext()
                    , getString(R.string.msg_cant_download), Toast.LENGTH_SHORT).show();
        } else {
            // TODO: 9/12/18 Handle download track
            Toast.makeText(getContext(), getString(R.string.msg_downloading), Toast.LENGTH_SHORT).show();
        }
    }
}
