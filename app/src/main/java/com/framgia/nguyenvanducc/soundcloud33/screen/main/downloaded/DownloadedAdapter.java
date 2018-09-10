package com.framgia.nguyenvanducc.soundcloud33.screen.main.downloaded;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Track;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseRecyclerViewAdapter;

import java.util.List;

public class DownloadedAdapter extends BaseRecyclerViewAdapter<DownloadedAdapter.ViewHolder> {
    private List<Track> mTracks;
    private DownloadedAdapter.TrackClickListener mTrackClickListener;

    DownloadedAdapter(Context context, List<Track> tracks, DownloadedAdapter.TrackClickListener trackClickListener) {
        super(context);
        this.mTracks = tracks;
        this.mTrackClickListener = trackClickListener;
    }

    @NonNull
    @Override
    public DownloadedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_track, viewGroup, false);
        return new DownloadedAdapter.ViewHolder(view, mTrackClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadedAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(mTracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageArtwork;
        private TextView mTextTitle;
        private TextView mTextArtist;
        private ImageView mImageFavorite;
        private ImageView mImageMore;
        private Track mTrack;
        private DownloadedAdapter.TrackClickListener mTrackClickListener;

        ViewHolder(@NonNull View itemView, DownloadedAdapter.TrackClickListener listener) {
            super(itemView);
            mImageArtwork = itemView.findViewById(R.id.image_item_artwork);
            mTextTitle = itemView.findViewById(R.id.text_item_title);
            mTextArtist = itemView.findViewById(R.id.text_item_artist);
            mImageFavorite = itemView.findViewById(R.id.image_item_favorite);
            mImageMore = itemView.findViewById(R.id.image_item_more);
            mTrackClickListener = listener;
            mImageFavorite.setOnClickListener(this);
            mImageMore.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void setData(Track track) {
            mTrack = track;
            RequestOptions options = new RequestOptions().centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.image_default_artwork);
            Glide.with(mImageArtwork)
                    .load(mTrack.getArtworkUrl())
                    .apply(options)
                    .into(mImageArtwork);
            mTextTitle.setText(track.getTitle());
            mTextArtist.setText(track.getArtist());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_item_favorite:
                    mTrackClickListener.onFavoriteClick(mTrack);
                    break;
                case R.id.image_item_more:
                    mTrackClickListener.onMoreClick(mTrack);
                    break;
                default:
                    mTrackClickListener.onTrackClick(mTrack);
                    break;
            }
        }
    }

    public interface TrackClickListener {
        void onTrackClick(Track track);

        void onFavoriteClick(Track track);

        void onMoreClick(Track track);
    }
}
