package native1989.github.com.jokes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Anton on 11/6/2015.
 */
public class JokeActivity extends Activity {
    private TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        jokeText = (TextView) findViewById(R.id.jokes);
        String joke = getIntent().getStringExtra("joke");
        jokeText.setText(joke);
    }
}
