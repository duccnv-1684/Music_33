package com.framgia.nguyenvanducc.soundcloud33.screen.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Genre;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.GenreRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.GenreLocalDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.local.TrackLocalDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.remote.GenreRemoteDataSource;
import com.framgia.nguyenvanducc.soundcloud33.data.source.remote.TrackRemoteDataSource;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;

import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.View
        , HomeAdapter.TrackClickListener {
    private HomeContract.Presenter mPresenter;
    private OnGenreDetailSelectListener mOnGenreDetailSelectListener;
    private View mView;
    private int[] mGenreLayoutIds = {R.id.genre_all_music, R.id.genre_all_audio
            , R.id.genre_alternative_rock, R.id.genre_ambient, R.id.genre_classical
            , R.id.genre_country};

    public HomeFragment() {
        TrackRepository trackRepository = TrackRepository.getInstance(
                TrackLocalDataSource.getInstance(getContext().getContentResolver()),
                TrackRemoteDataSource.getInstance());
        GenreRepository genreRepository = GenreRepository.getInstance(
                GenreLocalDataSource.getInstance(), GenreRemoteDataSource.getInstance());
        mPresenter = new HomePresenter(trackRepository, genreRepository);
        mPresenter.setView(this);
    }

    public void setOnGenreDetailSelectListener(OnGenreDetailSelectListener listener) {
        this.mOnGenreDetailSelectListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.mView = view;
        mPresenter.getAllGenre();
        return view;
    }

    @Override
    public void onTrackClick(Track track) {
    }

    @Override
    public void showTracksOfGenre(final Genre genre, List<Track> tracks, int currentIndex) {
        ConstraintLayout genreItem = mView.findViewById(mGenreLayoutIds[currentIndex]);
        TextView textGenreTitle = genreItem.findViewById(R.id.text_genre_title);
        textGenreTitle.setText(genre.getName());
        TextView textMoreTrack = genreItem.findViewById(R.id.text_more_track);
        textMoreTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showGenreDetail(genre);
            }
        });
        RecyclerView recyclerTracks = genreItem.findViewById(R.id.recycler_list_tracks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.HORIZONTAL, false);
        recyclerTracks.setLayoutManager(linearLayoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(getContext(), tracks, this);
        recyclerTracks.setAdapter(homeAdapter);
        genreItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void switchToGenreDetail(String genreName, String genreUrl) {
        mOnGenreDetailSelectListener.showGenreDetail(genreName, genreUrl);

    }

    public interface OnGenreDetailSelectListener {
        void showGenreDetail(String genreName, String genreUrl);
    }
}
