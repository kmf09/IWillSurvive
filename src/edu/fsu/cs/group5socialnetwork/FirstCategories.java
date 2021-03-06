package edu.fsu.cs.group5socialnetwork;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FirstCategories extends Activity {

	// global member variable for the listView, hence the 'm'
	public ListView mLV;
	public static boolean profileFlag = false;

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
				Intent myIntent;
				switch( position ) 
				{
				case 0:  
					myIntent = new Intent(getApplicationContext(), ProfileOptions.class);
					startActivity(myIntent);
					break;
				case 1:  
					if (profileFlag == false)
						Toast.makeText(FirstCategories.this, "Profile information must first be completed", Toast.LENGTH_SHORT).show();
					else {
						myIntent = new Intent(getApplicationContext(), LocationCategories.class);
						startActivity(myIntent);
					}
					break;
				case 2:
					leave();
					break;
				}
			}}); 
	}

	// exit application
	public void leave() {
		finish();
		/*Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);*/
	}
}
