package edu.fsu.cs.group5socialnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Profile extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		Question question = new Question(); 
		String[] questionAndAnswer; 
		int pointValue, j = 0; 
		String answer; 

		// Retrieves everything from the text file
		String textInfo = readTextFile("desertKatrinaVersion.txt"); 
		// In the file the questions are separated by @ signs 
		// First, split on the at signs to get the questions and answers together
		String[] temp = textInfo.split("@");
		for (int i = 0; i < temp.length; i++) {
			// Split on the new lines to get question1 answer1 pointvalue1 a2 pv2 a3 pv3 a4 pv4
			questionAndAnswer = temp[i].split("\n");
			// The first array index holds the question, so this will always be 0
			// Put the question in the question class
			question.setQuestion(questionAndAnswer[j]);
			// Continue 0-7 (total of 8) times since there are 4 answers and each answer has a point value 
			while (j < 8) {
				// The one above the first array index holds the answer
				answer = questionAndAnswer[++j];
				// The index above the one with the answer holds the point value
				pointValue = Integer.parseInt(questionAndAnswer[++j]);
				// Add the answer and the point value to the question class
				question.addAnswer(answer, pointValue);
			}
			j = 0; 
		} 
		
		// Everything is read and parsed, next is to put it inside of the activity itself and make it look pretty
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_profile, menu);
		return true;
	}

	// THIS IS WHAT MEGAN DID. 
	/* private void readFile(String fileName, Question[] questionList)
    { // pass in the file name as a string and the array of questions by reference

    	// flag will identify what the line being read is
    	int flag = 0;	// file starts with a question
    	int index = 0;	// index of the question
    	int answerIndex = 0;

    	try{
    		// Open the file that is the first 
    		FileInputStream fstream = new FileInputStream(fileName);
    		// Get the object of DataInputStream
    		DataInputStream in = new DataInputStream(fstream);
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		String strLine;
    		//Read File Line By Line
    		while ((strLine = br.readLine()) != null)   {
    			// Print the content on the console
    			System.out.println (strLine);	// remove this line eventually -- it's for testing
    			if (strLine == "")
    			{ // this line is a blank between

    				// reset values

    				flag = 0;
    				answerIndex = 0;
    			}
    			else if (flag == 0)
    			{ // this line is a question

    				index++;	// increment to next question
    				questionList[index].questionString = strLine;	// set question
    				questionList[index].points_awarded = 0;	// no points awarded yet
    				Answer mAnswer[] = new Answer[4];	// each question has 4 answer choices

    				flag++;

    			}
    			else if (flag % 2 != 0)
    			{
    				// this is an answer choice
    				questionList[index].mAnswer[answerIndex].answerString = strLine;
    				flag++;

    			}
    			else 
    			{	
    				// this is an answer value
    				questionList[index].mAnswer[answerIndex].points = strLine;

    				answerIndex++;
    				flag++;

    			}


    		}
    		//Close the input stream
    		in.close();
    	}catch (Exception e){//Catch exception if any
    		System.err.println("Error: " + e.getMessage());
    	}
    }
	 */

	private String readTextFile(String fileName) {
		BufferedReader in = null;

		try {
			String line;
			in = new BufferedReader(new InputStreamReader(this.getAssets().open(fileName)));
			final StringBuilder buffer = new StringBuilder();
			while ((line = in.readLine()) != null)
				buffer.append(line).append(System.getProperty("line.separator"));
			return buffer.toString();
		}
		catch (final IOException e) {
			return "";
		}
		finally
		{
			try {in.close();}
			catch (IOException e) {
				// ignore //
			}
		}
	}
}
