package edu.fsu.cs.group5socialnetwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Question extends Activity {
	Answer mAnswer[];
	int points_awarded; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_question, menu);
        return true;
    }
    
    public void setAnswer() {
    	// This was where I got confused - Katrina
    }
}
