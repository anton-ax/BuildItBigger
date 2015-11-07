package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.github.native1989.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<JokeCallBack, String, String> {

    MyApi jokeApi;
    JokeCallBack callback;
    String response;

    @Override
    protected String doInBackground(JokeCallBack... params) {
        if (jokeApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokeApi = builder.build();
        }

        if (params != null && params.length > 0) {
            this.callback = params[0];
        }

        try {
            response = jokeApi.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e("Fail", e.getMessage());
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        if (callback != null) {
            if (s != null) {
                callback.displayJoke(s);
            } else {
                callback.fail();
            }
        }
    }
}