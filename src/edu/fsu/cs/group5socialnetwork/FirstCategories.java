package edu.fsu.cs.group5socialnetwork;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FirstCategories extends Activity {

	// global member variable for the listView, hence the 'm'
	public ListView mLV;

	// always goes to onCreate() first
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set's the layout to first_list
		setContentView(R.layout.first_list);

		// gets a handle on the id named listView1 which is found
		// by opening the first_list in res/layout
		// this is the ID in the layout that corresponds: android:id="@+id/listView1"
		mLV = (ListView) findViewById(R.id.listView1);
		
		// event handler for when you click on a listView item
		mLV.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {	
				switch( position ) 
				{
					case 0:  
						// a toast prints to the screen the grey box with words in it
						Toast.makeText(FirstCategories.this, "You've clicked edit profile!", Toast.LENGTH_SHORT).show();
					/*Intent myIntent = new Intent(getApplicationContext(), SecondCompSciCategories.class);
					myIntent.putExtra("category","Computer Science");
					startActivity(myIntent);*/
					break;
					case 1:  
						Toast.makeText(FirstCategories.this, "You've clicked calculate survival rate!", Toast.LENGTH_SHORT).show();
					break;
					case 2:  
						Toast.makeText(FirstCategories.this, "You've clicked share!", Toast.LENGTH_SHORT).show();
					break;
					case 3:  
						Toast.makeText(FirstCategories.this, "You've clicked friend list!", Toast.LENGTH_SHORT).show();
					break;
					case 4:  
						Toast.makeText(FirstCategories.this, "You've clicked friend requests!", Toast.LENGTH_SHORT).show();
					break;
					case 5: 
						Toast.makeText(FirstCategories.this, "You've clicked account settings!", Toast.LENGTH_SHORT).show();
					break;
					case 6: 
						Toast.makeText(FirstCategories.this, "You've clicked logout!", Toast.LENGTH_SHORT).show();
						break;
				}
			}});
	}
}
