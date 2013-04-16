package edu.fsu.cs.group5socialnetwork;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LocationCategories extends Activity {
	// global member variable for the listView, hence the 'm'
	public ListView mLV;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_categories);
        
        // gets a handle on the id named listView1 which is found
		// by opening the first_list in res/layout
		// this is the ID in the layout that corresponds: android:id="@+id/listView1"
		mLV = (ListView) findViewById(R.id.listView1);

		// event handler for when you click on a listView item
		mLV.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent myIntent;
				switch( position ) 
				{
				case 0:  
					// You've clicked arctic
					myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","arcticQuiz");
					startActivity(myIntent);
					break;
				case 1:  
					// You've clicked desert
					myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","desertQuiz");
					startActivity(myIntent);
					break;
				case 2:  
					// You've clicked forest
					myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","forestQuiz");
					startActivity(myIntent);
					break;
				case 3:   
					// You've clicked mountain
					myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","mountainQuiz");
					startActivity(myIntent);
					break;
				case 4:
					// You've clicked swamp
					myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","swampQuiz");
					startActivity(myIntent);
					break; 
				}
			}});
    }
}
