package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import native1989.github.com.jokes.JokeActivity;

/**
 * Created by Anton on 11/7/2015.
 */
public abstract class AbstractMainFragment extends Fragment implements JokeCallBack {

    protected Button jokeButton;
    protected ProgressBar progress;

    @Override
    public void fail() {
        jokeButton.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.server_unavailable,
                Toast.LENGTH_SHORT).show();
    }

    public void openJoke(String joke) {
        jokeButton.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }
}
