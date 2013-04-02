package edu.fsu.cs.group5socialnetwork;

import android.app.Activity;
import android.content.Intent;
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
        username.setText(MainActivity.mUserName.getText());
        
        mLV = (ListView) findViewById(R.id.listView1);
		
		// event handler for when you click on a listView item
		mLV.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent myIntent;
				switch( position ) 
				{
				case 0:  
					/*myIntent = new Intent(getApplicationContext(), Profile.class);
					myIntent.putExtra("quizType","profileQuiz");
					startActivity(myIntent);*/
					myIntent = new Intent(getApplicationContext(), ProfileOptions.class);
					startActivity(myIntent);
					break;
				case 1:  
					if (profileFlag == false)
						Toast.makeText(FirstCategories.this, "Please select \"Profile\" first", Toast.LENGTH_SHORT).show();
					else
					{
						myIntent = new Intent(getApplicationContext(), LocationCategories.class);
						startActivity(myIntent);
					}
					break;
				case 2:
					if (profileFlag == false)
						Toast.makeText(FirstCategories.this, "Please select \"Profile\" first", Toast.LENGTH_SHORT).show();
					else
					{
						Toast.makeText(FirstCategories.this, "You've clicked settings!", Toast.LENGTH_SHORT).show();
					}
					break;
				case 3:  
					leave(); 
					break;
				}
			}}); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile_options, menu);
        return true;
    }
}