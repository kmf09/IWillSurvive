package edu.fsu.cs.group5socialnetwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HighScores extends Activity {

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        
        TextView arcticHighScore = (TextView) findViewById(R.id.arcticHighScoreTV);
        TextView desertHighScore = (TextView) findViewById(R.id.desertHighScoreTV);
        TextView moutainHighScore = (TextView) findViewById(R.id.mountainHighScoreTV);
        TextView swampHighScore = (TextView) findViewById(R.id.swampHighScoreTV);
        TextView forestHighScore = (TextView) findViewById(R.id.forestHighScoreTV);
        
        arcticHighScore.setText(""+Results.mArcticHighScore);
        desertHighScore.setText(""+Results.mDesertHighScore);
        moutainHighScore.setText(""+Results.mMountainHighScore);
        swampHighScore.setText(""+Results.mSwampHighScore);
        forestHighScore.setText(""+Results.mForestHighScore);
    }
}
