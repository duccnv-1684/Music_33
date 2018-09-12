package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying.trackdetail;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.data.repository.TrackRepository;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;
import com.framgia.nguyenvanducc.soundcloud33.utils.LoopMode;
import com.framgia.nguyenvanducc.soundcloud33.utils.ShuffleMode;
import com.framgia.nguyenvanducc.soundcloud33.utils.StringUtils;

public class TrackDetailFragment extends BaseFragment implements TrackDetailContract.View,
        View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final String OBJECT_ANIMATOR_PROPERTY = "rotation";
    private static final int OBJECT_ANIMATOR_START = 0;
    private static final int OBJECT_ANIMATOR_END = 360;
    private static final int OBJECT_ANIMATOR_DURATION = 30000;
    private static final int PRIMARY_COLOR = 0xFF9C27B0;
    private static final int ONE_SECOND_INT_MILLISECOND = 1000;
    private ImageView mImageArtwork;
    private ImageView mImageFavorite;
    private ImageView mImageDownload;
    private TextView mTextCurrentTime;
    private TextView mTextDuration;
    private SeekBar mSeekBar;
    private ImageView mImageShuffle;
    private ImageView mImagePlaying;
    private ImageView mImageLoop;
    private ImageView mImagePrevious;
    private ImageView mImageNext;
    private ObjectAnimator mObjectAnimator;
    private TrackDetailPresenter mPresenter;
    private int mCurrentTime;
    private boolean mIsPlaying;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = new TrackDetailPresenter(TrackRepository.getInstance(context));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_track_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView(view);
        setUpEvent();
        setUpObjectAnimator();
        setUpHandler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_favorite:
                mPresenter.setFavorite();
                break;
            case R.id.image_download:
                mPresenter.downloadTrack();
                break;
            case R.id.image_shuffle:
                mPresenter.setShuffle();
                break;
            case R.id.image_previous:
                mPresenter.skipPrevious();
                break;
            case R.id.image_play:
                mPresenter.changePlayingStatus();
                break;
            case R.id.image_next:
                mPresenter.skipNext();
                break;
            case R.id.image_loop:
                mPresenter.setLoop();
                break;
            default:
                break;
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mCurrentTime = progress;
            mTextCurrentTime.setText(StringUtils.convertTime(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setUpView(View view) {
        mPresenter.setView(this);
        mImageArtwork = view.findViewById(R.id.image_artwork);
        mImageFavorite = view.findViewById(R.id.image_favorite);
        mImageDownload = view.findViewById(R.id.image_download);
        mTextCurrentTime = view.findViewById(R.id.text_current_time);
        mTextDuration = view.findViewById(R.id.text_duration);
        mSeekBar = view.findViewById(R.id.seek_bar);
        mImageShuffle = view.findViewById(R.id.image_shuffle);
        mImagePrevious = view.findViewById(R.id.image_previous);
        mImagePlaying = view.findViewById(R.id.image_play);
        mImageNext = view.findViewById(R.id.image_next);
        mImageLoop = view.findViewById(R.id.image_loop);
    }

    private void setUpEvent() {
        mImageFavorite.setOnClickListener(this);
        mImageDownload.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mImageShuffle.setOnClickListener(this);
        mImagePrevious.setOnClickListener(this);
        mImagePlaying.setOnClickListener(this);
        mImageNext.setOnClickListener(this);
        mImageLoop.setOnClickListener(this);
    }

    private void setUpObjectAnimator() {
        mObjectAnimator = ObjectAnimator.ofFloat(mImageArtwork, OBJECT_ANIMATOR_PROPERTY
                , OBJECT_ANIMATOR_START, OBJECT_ANIMATOR_END);
        mObjectAnimator.setDuration(OBJECT_ANIMATOR_DURATION);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
    }

    private void setUpHandler() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mIsPlaying) {
                    mCurrentTime += ONE_SECOND_INT_MILLISECOND;
                    mSeekBar.setProgress(mCurrentTime);
                    mTextCurrentTime.setText(StringUtils.convertTime(mCurrentTime));
                }
                handler.postDelayed(this, ONE_SECOND_INT_MILLISECOND);
            }
        });
    }

    @Override
    public void updatePlayingTrack(Track track, boolean isPlaying, int loopMode, int shuffleMode) {

    }

    @Override
    public void updatePlayingInfo(Track track) {
        mCurrentTime = 0;
        String artworkUrl = track.getArtworkUrl();
        setUpArtWorkImage(artworkUrl);
        updateFavorite(mPresenter.checkTrackIsInFavorite(track));
        mTextCurrentTime.setText(StringUtils.convertTime(mCurrentTime));
        mTextDuration.setText(StringUtils.convertTime((int) track.getDuration()));
        mSeekBar.setMax((int) track.getDuration());

    }

    @Override
    public void updateFavorite(boolean isFavorite) {
        mImageFavorite.setImageResource(isFavorite ?
                R.drawable.ic_favorite_full : R.drawable.ic_favorite);
    }

    @Override
    public void updateDownload(boolean isDownloadable) {
    }

    @Override
    public void updateShuffle(@ShuffleMode int shuffleMode) {
        switch (shuffleMode) {
            case ShuffleMode.ON:
                mImageShuffle.setColorFilter(PRIMARY_COLOR);
                break;
            case ShuffleMode.OFF:
                mImageShuffle.setColorFilter(Color.WHITE);
                break;
            default:
                break;
        }
    }

    @Override
    public void updateLoop(@LoopMode int loopMode) {
        switch (loopMode) {
            case LoopMode.NO_LOOP:
                mImageLoop.setImageResource(R.drawable.ic_loop);
                mImageLoop.setColorFilter(Color.WHITE);
                break;
            case LoopMode.LOOP_ONE:
                mImageLoop.setImageResource(R.drawable.ic_loop_one);
                mImageLoop.setColorFilter(PRIMARY_COLOR);
                break;
            case LoopMode.LOOP_ALL:
                mImageLoop.setImageResource(R.drawable.ic_loop);
                mImageLoop.setColorFilter(PRIMARY_COLOR);
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void updatePlayingStatus(boolean isPlaying) {
        mIsPlaying = isPlaying;
        mImagePlaying.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
    }

    private void setUpArtWorkImage(String artworkUrl) {
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .error(R.drawable.image_default_artwork)
                .placeholder(R.drawable.image_default_artwork);
        if (StringUtils.isOnlineUrl(artworkUrl)) {
            Glide.with(mImageArtwork)
                    .load(artworkUrl == null ? Constants.NULL : artworkUrl)
                    .apply(options)
                    .into(mImageArtwork);
        } else {
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            metadataRetriever.setDataSource(artworkUrl);
            byte[] artwork = metadataRetriever.getEmbeddedPicture();
            Glide.with(mImageArtwork)
                    .load(artwork == null ? Constants.NULL : artwork)
                    .apply(options)
                    .into(mImageArtwork);
        }
    }
}
