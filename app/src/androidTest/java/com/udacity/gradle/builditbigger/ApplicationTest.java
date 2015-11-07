package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    /*
        Check JokeAsyncTask
     */
    @Override
    protected void runTest() throws Throwable {
        super.runTest();
        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask();
        jokeAsyncTask.execute();
        String joke = "";
        try {
            joke = jokeAsyncTask.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(joke.length() > 0);
    }
}