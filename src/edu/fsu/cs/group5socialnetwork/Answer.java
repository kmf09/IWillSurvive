package edu.fsu.cs.group5socialnetwork;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Answer {
	String answerString;
	int points; 
	
	public Answer(String ans, int pointValue) {
		setAnswerString(ans);
		setPoints(pointValue);
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
