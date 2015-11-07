import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import native1989.github.com.jokes.JokeActivity;

import com.udacity.gradle.builditbigger.R;

import android.widget.Button;
import android.widget.Toast;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.JokeCallBack;
import com.udacity.gradle.builditbigger.AbstractMainFragment;
import android.widget.ProgressBar;
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends AbstractMainFragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = (Button) root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        progress = (ProgressBar) root.findViewById(R.id.progress);
        return root;
    }

    public void tellJoke() {
        progress.setVisibility(View.VISIBLE);
        jokeButton.setVisibility(View.INVISIBLE);
        new JokeAsyncTask().execute(this);
    }

    @Override
    public void displayJoke(String joke) {
        openJoke(joke);
    }
}
