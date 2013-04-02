package edu.fsu.cs.group5socialnetwork;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Results extends Activity {
	int mProfileScore, mArticHighScore, mDesertHighScore, mForrestHighScore, mMountainHighScore, mSwampHighScore;
	String mCurrentQuiz;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        
        mArticHighScore = mDesertHighScore = mForrestHighScore = mMountainHighScore = mSwampHighScore = 0;
        mCurrentQuiz = getIntent().getStringExtra("quizType");
        ArrayList<Question> mQuestion = Profile.mQuestion;
        int currentScore = 0;
        mProfileScore = Profile.mProfileScore;
        
        TextView currentScoreItem = (TextView) findViewById(R.id.currentScoreTV);
        TextView highScoreItem = (TextView) findViewById(R.id.highScoreTV);
        TextView WinOrLose = (TextView) findViewById(R.id.winloseTV);
        
		// Calculate total points awarded
        for (int i = 0; i < mQuestion.size(); i++)
                currentScore += mQuestion.get(i).mPoints_awarded;

        if (mCurrentQuiz != "profile")
        {
            currentScore += mProfileScore - 20;

            if (mCurrentQuiz == "artic")
            {
                if (currentScore > mArticHighScore)
                {
                    mArticHighScore = currentScore;
                    highScoreItem.setText("" + mArticHighScore);
                }
            }
            else if (mCurrentQuiz == "desert")
            {
                if (currentScore > mDesertHighScore)
                {
                    mDesertHighScore = currentScore;
                    highScoreItem.setText("" + mDesertHighScore);
                }
            }
            else if (mCurrentQuiz == "forrest")
            {
                if (currentScore > mForrestHighScore)
                {
                    mForrestHighScore = currentScore;
                    highScoreItem.setText("" + mForrestHighScore);
                }
            }
            else if (mCurrentQuiz == "mountain")
            {
                if (currentScore > mMountainHighScore)
                {
                    mMountainHighScore = currentScore;
                    highScoreItem.setText("" + mMountainHighScore);
                }
            }
            else if (mCurrentQuiz == "swamp")
            {
                if (currentScore > mSwampHighScore)
                {
                    mSwampHighScore = currentScore;
                    highScoreItem.setText("" + mSwampHighScore);
                }
            }
            currentScoreItem.setText("" + currentScore);
            
            if (currentScore >= 80 && currentScore <= 100)
            {
                WinOrLose.setText("You survived!");
                WinOrLose.setTextColor(Color.GREEN);
            }
            else if (currentScore >= 40 && currentScore <= 79)
            {
                WinOrLose.setText("Barley made it");
                WinOrLose.setTextColor(Color.YELLOW);
            }
            else if (currentScore >= 0 && currentScore <= 90)
            {
                WinOrLose.setText("Better luck next time");
                WinOrLose.setTextColor(Color.RED);
            }
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_results, menu);
        return true;
    }
}
