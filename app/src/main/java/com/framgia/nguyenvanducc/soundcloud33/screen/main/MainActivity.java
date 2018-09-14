package com.framgia.nguyenvanducc.soundcloud33.screen.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.nguyenvanducc.soundcloud33.R;
import com.framgia.nguyenvanducc.soundcloud33.data.model.Playlist;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseActivity;
import com.framgia.nguyenvanducc.soundcloud33.screen.BaseFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.downloaded.DownloadedFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.favorite.FavoriteFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.genredetail.GenreDetailFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.home.HomeFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.library.LibraryFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.localtrack.LocalTrackFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.playlist.PlaylistFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.playlistdetail.PlaylistDetailFragment;
import com.framgia.nguyenvanducc.soundcloud33.screen.main.search.SearchFragment;
import com.framgia.nguyenvanducc.soundcloud33.utils.Constants;

public class MainActivity extends BaseActivity implements MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnGenreDetailSelectListener, SearchView.OnQueryTextListener,
        LibraryFragment.OnItemSelected, PlaylistFragment.OnPlaylistSelectListener {
    private static final int REQUEST_WRITE_EXTERNAL = 212;
    private TextView mTextToolbarTitle;
    private final HomeFragment mHomeFragment = new HomeFragment();
    private final SearchFragment mSearchFragment = new SearchFragment();
    private final LibraryFragment mLibraryFragment = new LibraryFragment();
    private FragmentManager mFragmentManager;
    private BaseFragment mActiveFragment;
    private BaseFragment mPreviousFragment;
    private SearchView mSearchView;
    private PlaylistFragment mPlaylistFragment;
    private String mPreviousTitle;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentToMainFrame();
        setUpView();
        mPresenter = new MainPresenter();
        mPresenter.setView(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_nav_home:
                selectTabHome();
                return true;
            case R.id.menu_nav_search:
                selectTabSearch();
                return true;
            case R.id.menu_nav_library:
                if (isGrantedPermission()) selectTabLibrary();
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void showGenreDetail(String genreName, String genreUrl) {
        GenreDetailFragment genreDetailFragment = new GenreDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGUMENT_GENRE_URL, genreUrl);
        genreDetailFragment.setArguments(bundle);
        showNewFragment(genreDetailFragment, mHomeFragment, GenreDetailFragment.TAG, genreName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPreviousFragment != null && mPreviousTitle != null) {
            setActiveFragment(mPreviousFragment);
            setTitle(mPreviousTitle);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        mSearchView.clearFocus();
        mSearchFragment.querySubmit(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void switchToLibrary(int key) {
        switch (key) {
            case Constants.KEY_MUSIC_ON_DEVICE:
                LocalTrackFragment localTrackFragment = new LocalTrackFragment();
                showNewFragment(localTrackFragment, mLibraryFragment, LocalTrackFragment.TAG,
                        getString(R.string.title_music_on_this_device));
                break;
            case Constants.KEY_PLAYLIST:
                mPlaylistFragment = new PlaylistFragment();
                showNewFragment(mPlaylistFragment, mLibraryFragment, PlaylistFragment.TAG,
                        getString(R.string.title_playlist));
                break;
            case Constants.KEY_FAVORITE:
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                showNewFragment(favoriteFragment, mLibraryFragment, FavoriteFragment.TAG,
                        getString(R.string.title_favorite));
                break;
            case Constants.KEY_DOWNLOADED:
                DownloadedFragment downloadedFragment = new DownloadedFragment();
                showNewFragment(downloadedFragment, mLibraryFragment, DownloadedFragment.TAG,
                        getString(R.string.title_downloaded));
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectTabLibrary();
                } else {
                    Toast.makeText(this, getString(R.string.msg_permission_denied)
                            , Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPlaylistSelect(Playlist playlist) {
        PlaylistDetailFragment playlistDetailFragment = new PlaylistDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ARGUMENT_PLAYLIST_ID, playlist.getId());
        bundle.putString(Constants.ARGUMENT_PLAYLIST_TITLE, playlist.getTitle());
        playlistDetailFragment.setArguments(bundle);
        showNewFragment(playlistDetailFragment, mPlaylistFragment, PlaylistDetailFragment.TAG,
                playlist.getTitle());
    }

    private void selectTabHome() {
        setTitle(getString(R.string.title_home));
        mTextToolbarTitle.setVisibility(View.VISIBLE);
        mSearchView.setVisibility(View.GONE);
        setActiveFragment(mHomeFragment);
    }

    private void selectTabSearch() {
        setActiveFragment(mSearchFragment);
        mTextToolbarTitle.setVisibility(View.GONE);
        mSearchView.setVisibility(View.VISIBLE);
        mSearchView.onActionViewExpanded();
    }

    private void selectTabLibrary() {
        setActiveFragment(mLibraryFragment);
        mTextToolbarTitle.setVisibility(View.VISIBLE);
        mSearchView.setVisibility(View.GONE);
        setTitle(getString(R.string.title_library));
    }

    private void setUpView() {
        mTextToolbarTitle = findViewById(R.id.text_toolbar_title);
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(this);
        EditText editTextSearch
                = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editTextSearch.setTextColor(Color.WHITE);
        editTextSearch.setHint(R.string.hint_search);
        editTextSearch.setHintTextColor(Color.WHITE);
        editTextSearch.setTextSize(Constants.SEARCH_HINT_TEXT_SIZE);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu_nav_home);
    }

    private void addFragmentToMainFrame() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_main, mHomeFragment, HomeFragment.TAG)
                .hide(mHomeFragment).commit();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_main, mSearchFragment, SearchFragment.TAG)
                .hide(mSearchFragment).commit();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_main, mLibraryFragment, LibraryFragment.TAG)
                .hide(mLibraryFragment).commit();
        mActiveFragment = mHomeFragment;
    }

    private void setActiveFragment(BaseFragment activeFragment) {
        mFragmentManager.beginTransaction().hide(mActiveFragment)
                .show(activeFragment).commit();
        mActiveFragment = activeFragment;
    }

    private void setTitle(String newTitle) {
        mPreviousTitle = mTextToolbarTitle.getText().toString();
        mTextToolbarTitle.setText(newTitle);
    }

    private boolean isGrantedPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        else if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    , REQUEST_WRITE_EXTERNAL);
        }
        return false;
    }

    private void showNewFragment(BaseFragment newFragment, BaseFragment previousFragment,
                                 String tag, String title) {
        mFragmentManager.beginTransaction().add(R.id.frame_main, newFragment,
                tag).hide(newFragment).addToBackStack(null).commit();
        setActiveFragment(newFragment);
        mPreviousFragment = previousFragment;
        mPreviousTitle = getString(R.string.title_home);
        setTitle(title);
    }
}
