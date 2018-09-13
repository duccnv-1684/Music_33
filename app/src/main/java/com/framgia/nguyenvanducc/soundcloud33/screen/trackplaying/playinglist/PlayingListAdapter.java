package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying.playinglist;

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

import java.util.ArrayList;
import java.util.List;

public class PlayingListAdapter extends BaseRecyclerViewAdapter<PlayingListAdapter.ViewHolder> {
    private List<Track> mTracks;
    private TrackClickListener mTrackClickListener;

    PlayingListAdapter(Context context, TrackClickListener trackClickListener) {
        super(context);
        this.mTracks = new ArrayList<>();
        this.mTrackClickListener = trackClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_track_playing_list, viewGroup, false);
        return new ViewHolder(view, mTrackClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(mTracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public void addData(List<Track> tracks) {
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageArtwork;
        private TextView mTextTitle;
        private TextView mTextArtist;
        private Track mTrack;
        private TrackClickListener mTrackClickListener;

        ViewHolder(@NonNull View itemView, TrackClickListener trackClickListener) {
            super(itemView);
            mImageArtwork = itemView.findViewById(R.id.image_item_artwork);
            mTextTitle = itemView.findViewById(R.id.text_item_title);
            mTextArtist = itemView.findViewById(R.id.text_item_artist);
            mTrackClickListener = trackClickListener;
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
            mTrackClickListener.onTrackClick(getAdapterPosition());
        }
    }

    public interface TrackClickListener {
        void onTrackClick(int position);
    }
}
