package edu.fsu.cs.group5socialnetwork;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Answer extends Activity {
	String answerString;
	int points; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_answer, menu);
        return true;
    }
    
    public void setAnswerString(String ans) {
    	answerString = ans; 
    }
    
    public String getAnswerString() {
    	return answerString; 
    }
    
    public void setPoints(int point) {
    	points = point; 
    }
    
    public int getPoints() {
    	return points; 
    }
}
