package edu.fsu.cs.group5socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class Question {
	ArrayList<Answer> mAnswers;
	String mQuestionValue; 
	int mPoints_awarded; 
	
	public Question() {
		mAnswers = new ArrayList<Answer>(); 
	}
    
    public void setQuestion(String question) {
    	mQuestionValue = question; 
    }
    
    public String getQuestion() {
    	return mQuestionValue;
    }
    
    public void addAnswer(String ans, int pointValue) {
    	mAnswers.add(new Answer(ans, pointValue)); 
    }
    
    public List<Answer> getAnswers() {
    	return mAnswers; 
    }
    
    public String getAnswer(int answerNum) {
    	return mAnswers.get(answerNum).getAnswerString(); 
    }
}
