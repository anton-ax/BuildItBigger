import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.AbstractMainFragment;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.R;

import java.util.Random;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends AbstractMainFragment {

    private InterstitialAd mInterstitialAd;
    private boolean adIsOpened;
    private String joke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                adIsOpened = false;
                if (joke != null) {
                    displayJoke(joke);
                }
            }
        });

        jokeButton = (Button) root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        progress = (ProgressBar) root.findViewById(R.id.progress);
        requestNewInterstitial();
        return root;
    }

    public void tellJoke() {
        jokeButton.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
        new JokeAsyncTask().execute(this);
        if (showAdThisTime() && mInterstitialAd.isLoaded()) {
            adIsOpened = true;
            mInterstitialAd.show();
        }
    }

    private boolean showAdThisTime() {
        int test = new Random().nextInt(3);
        return test == 1;
    }

    @Override
    public void displayJoke(String joke) {
        this.joke = joke;
        if (!adIsOpened) {
            openJoke(joke);
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
