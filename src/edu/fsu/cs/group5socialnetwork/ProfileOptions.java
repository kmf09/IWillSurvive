package edu.fsu.cs.group5socialnetwork;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProfileOptions extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_options);

		TextView username = (TextView) findViewById(R.id.usernameTV);
		username.setText(MainActivity.mUser);

		ListView mLV = (ListView) findViewById(R.id.listView1);

		// event handler for when you click on a listView item
		mLV.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent myIntent;
				switch( position ) 
				{
				case 0:  
					// High scores
					/*myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","profileQuiz");
					startActivity(myIntent);*/
					myIntent = new Intent(getApplicationContext(), HighScores.class);
					startActivity(myIntent);
					break;
				case 1:  
					myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","profileQuiz");
					startActivity(myIntent);
					break;
				case 2:

					break;
				case 3:  
					// Defines selection criteria for the rows you want to delete
					String[] mSelectionArgs = {MainActivity.mUser};
					
					// Defines a variable to contain the number of rows deleted
					//int mRowsDeleted = 0;

					// Deletes the words that match the selection criteria
					getContentResolver().delete(
						Uri.parse("content://co.NoCoffee.provider/" + "users"),   // the user dictionary content URI
					    "username = ?",                  // the column to select on
					    mSelectionArgs                      // the value to compare to
					);
					
					myIntent = new Intent(ProfileOptions.this, MainActivity.class);
					startActivity(myIntent);
					break;
				}
			}}); 
	}
}
