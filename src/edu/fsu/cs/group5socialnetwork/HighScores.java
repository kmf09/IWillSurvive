package edu.fsu.cs.group5socialnetwork;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HighScores extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_high_scores, menu);
        return true;
    }
}
