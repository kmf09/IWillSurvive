package edu.fsu.cs.group5socialnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.mobdb.android.GetRowData;
import com.mobdb.android.InsertRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

public class MainQuestionsActivity extends Activity {
	final String APP_KEY = "66TP6D-1Ss-00L7SKWoWLlKpaduIiUiUMIR-BLUuIiZxZpPSCIAeua";
	final String TABLE_NAME = "questions";
	EditText theQuestion;
	Button questionButton;
	String username, subcat, Q;
	ListView listView; 

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TableLayout linear = new TableLayout(this);
		listView = new ListView(this);
		listView.setCacheColorHint(0);
		
		TableRow row1 = new TableRow(this);
		theQuestion = new EditText(this);
		row1.addView(theQuestion);
		
		TableRow row2 = new TableRow(this);
		questionButton = new Button(this);
		questionButton.setText("Ask a Question");
		questionButton.setWidth(480);
		row2.addView(questionButton);
		
		TableRow row3 = new TableRow(this);
		row3.addView(listView);
		
		linear.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
		linear.addView(row1);
		linear.addView(row2);
		linear.addView(row3);
		setContentView(linear);
		
		Intent myIntent = getIntent();
		Bundle myBundle = myIntent.getExtras();
		
		if (myBundle != null) {
			subcat = myBundle.getString("subcat");
		}

		populate();

		SharedPreferences userDetails = MainQuestionsActivity.this.getSharedPreferences("userdetails", MODE_PRIVATE);
		username = userDetails.getString("username", "");		

		//Toast.makeText(this, subcat, Toast.LENGTH_SHORT).show();
		
	    listView.setOnItemClickListener(new OnItemClickListener(){
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			String tempquest = (String)listView.getItemAtPosition(position);
			Intent answerIntent = new Intent(getApplicationContext(), AnswerQuestionPage.class);
			answerIntent.putExtra("question", tempquest);
			startActivity(answerIntent);
		}});
	    
		
		
		questionButton.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View v) {
				boolean testing = true;
				
				if(theQuestion.getText().toString().contains("'"))
				{
					Q = theQuestion.getText().toString().replace("'", "");
					testing = false;
				}
				else if(theQuestion.getText().toString().contains("\\"))
				{
					Q = theQuestion.getText().toString().replace("\\", "");
					testing = false;
				}
				
				
				if (theQuestion.getText().toString() != null) {
					if(testing != false)
					{
					Toast.makeText(MainQuestionsActivity.this, "Question Asked", Toast.LENGTH_SHORT).show();
					Q = theQuestion.getText().toString();
					}
					InsertRowData insertRowData = new InsertRowData(TABLE_NAME);
					insertRowData.setValue("question", Q);
					insertRowData.setValue("username", username);
					insertRowData.setValue("subcat", subcat);
					MobDB.getInstance().execute(APP_KEY, insertRowData, null, false, new MobDBResponseListener() {
						public void mobDBSuccessResponse() {}
						public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}
						public void mobDBResponse(String jsonObj) {}
						public void mobDBFileResponse(String fileName, byte[] fileData) {}
						public void mobDBErrorResponse(Integer errValue, String errMsg) {}
					});
				}
				populate();
				theQuestion.setText("");
			}
		});
	}
	
	public void populate() {
		GetRowData data = new GetRowData(TABLE_NAME);
		data.getField("question");
		data.getField("subcat");

		MobDB.getInstance().execute(APP_KEY, data, null, false, new MobDBResponseListener() {
			public void mobDBSuccessResponse() { }
			public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
				ArrayList<String> toAdd = new ArrayList<String>(); 
				int count = 0; 
				// result.get(0) = first row
				// .get("question") = question attribute
				// [0] since it is a 2D array always have to have [0]
				if (result.size() > 0) { 
					do {
						if (result.get(count).get("subcat")[0].toString().equals(subcat)) 
							toAdd.add(result.get(count).get("question")[0].toString());
						count++;
					} while (count < result.size());
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainQuestionsActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, toAdd);
					listView.setAdapter(adapter);
				}
			}
			public void mobDBResponse(String jsonObj) {}
			public void mobDBFileResponse(String fileName, byte[] fileData) {}
			public void mobDBErrorResponse(Integer errValue, String errMsg) {}
		});	
	}
	
}


