package edu.fsu.cs.group5socialnetwork;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FirstCategories extends Activity {

	public ListView mLV;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_list);

		mLV = (ListView) findViewById(R.id.listView1);
		mLV.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {	
				switch( position ) 
				{
					case 0:  Intent myIntent = new Intent(getApplicationContext(), SecondCompSciCategories.class);
					startActivity(myIntent);
					break;
					case 1:  Intent myIntent1 = new Intent(getApplicationContext(), SecondMathCategories.class);
					startActivity(myIntent1);
					break;
					case 2:  Intent  myIntent2 = new Intent(getApplicationContext(), SecondScienceCategories.class);
					startActivity(myIntent2);
					break;
					case 3:  Intent myIntent3 = new Intent(getApplicationContext(), SecondHistoryCategories.class);
					startActivity(myIntent3);
					break;
					case 4:  Intent myIntent4 = new Intent(getApplicationContext(), SecondLiteratureCategories.class);
					//Literature ends after it is selected so it should go right to a page where questions
					//will be displayed with Lit stuff and allow users to ask questions
					startActivity(myIntent4);
					break;
					case 5:  Intent myIntent5 = new Intent(getApplicationContext(), MainQuestionsActivity.class);
					myIntent5.putExtra("subcat", "Other");
					startActivity(myIntent5);
					break;
				}
			}});
	}
}
