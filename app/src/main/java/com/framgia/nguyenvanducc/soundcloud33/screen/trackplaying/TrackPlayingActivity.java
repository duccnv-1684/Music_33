package com.framgia.nguyenvanducc.soundcloud33.screen.trackplaying;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseActivity;

public class TrackPlayingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_playing);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textToolbarTitle = toolbar.findViewById(R.id.text_toolbar_title);
        TextView textToolbarSubtitle = toolbar.findViewById(R.id.text_toolbar_subtitle);
    }
}
