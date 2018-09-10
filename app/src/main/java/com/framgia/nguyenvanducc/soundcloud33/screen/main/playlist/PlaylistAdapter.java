package com.framgia.nguyenvanducc.soundcloud33.screen.main.playlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseRecyclerViewAdapter;

import java.util.List;

public class PlaylistAdapter extends BaseRecyclerViewAdapter<PlaylistAdapter.ViewHolder> {
    private List<Playlist> mPlaylists;
    private PlaylistClickListener mListener;

    PlaylistAdapter(Context context, List<Playlist> playlists, PlaylistClickListener listener) {
        super(context);
        this.mPlaylists = playlists;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_playlist, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(mPlaylists.get(i));
    }

    public void insertPlaylist(Playlist playlist) {
        int index = mPlaylists.size();
        mPlaylists.add(playlist);
        notifyItemInserted(index);
    }

    public void removePlaylist(Playlist playlist) {
        int index = mPlaylists.indexOf(playlist);
        mPlaylists.remove(playlist);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, mPlaylists.size());
    }

    @Override
    public int getItemCount() {
        return mPlaylists == null ? 0 : mPlaylists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextTitle;
        private ImageView mImageMore;
        private Playlist mPlaylist;
        private PlaylistClickListener mPlaylistClickListener;

        ViewHolder(@NonNull View itemView, PlaylistClickListener playlistClickListener) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text_item_title);
            mImageMore = itemView.findViewById(R.id.image_item_more);
            mPlaylistClickListener = playlistClickListener;
            mImageMore.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void setData(Playlist playlist) {
            mPlaylist = playlist;
            mTextTitle.setText(playlist.getTitle());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_item_more:
                    mPlaylistClickListener.onMoreClick(mPlaylist);
                    break;
                default:
                    mPlaylistClickListener.onPlaylistClick(mPlaylist);
                    break;
            }
        }
    }

    public interface PlaylistClickListener {
        void onPlaylistClick(Playlist playlist);

        void onMoreClick(Playlist playlist);
    }
}
