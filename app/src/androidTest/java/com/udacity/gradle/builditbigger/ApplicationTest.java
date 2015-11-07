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
        if (joke.length() > 0) {
            assertTrue(true);
        } else {
            assertFalse(true);
        }
    }
}