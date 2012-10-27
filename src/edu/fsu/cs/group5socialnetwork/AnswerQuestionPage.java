package edu.fsu.cs.group5socialnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import com.mobdb.android.GetRowData;

import com.mobdb.android.InsertRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AnswerQuestionPage extends Activity {
	final String APP_KEY = "66TP6D-1Ss-00L7SKWoWLlKpaduIiUiUMIR-BLUuIiZxZpPSCIAeua";

	String mQuestion, mAnswer, mUsername;
	TextView mTopQuestion;
	EditText mAnswerbox;
	ListView mListView;

	@Override public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.answerquestionpage);
	    	     
	    // get question
	    Intent myIntent = getIntent();
	    Bundle myBundle = myIntent.getExtras();
	    if (myBundle != null)
	    	mQuestion = myBundle.getString("question");
	    
	    mTopQuestion = (TextView)findViewById(R.id.textView1);
	    mAnswerbox = (EditText)findViewById(R.id.editText1);
	    mListView = (ListView)findViewById(R.id.listView1);
	    
		SharedPreferences userDetails = AnswerQuestionPage.this.getSharedPreferences("userdetails", MODE_PRIVATE);
		mUsername = userDetails.getString("username", "");		
	    
		// main question
	    mTopQuestion.setText(mQuestion);
	    
	    // populate the list view with the questions
	    populate();
	}
	
	public void answerQuestionHandler(View v){
		boolean testing = true;
		
		// mobDB does not accept these characters
		if(mAnswerbox.getText().toString().contains("'"))
		{
			mAnswer = mAnswerbox.getText().toString().replace("'", "");
			testing = false;
		}
		else if(mAnswerbox.getText().toString().contains("\\"))
		{
			mAnswer = mAnswerbox.getText().toString().replace("\\", "");
			testing = false;
		}
		
		if (!mAnswerbox.getText().toString().equals("")) {
			if(testing != false)
				mAnswer = mAnswerbox.getText().toString();

			InsertRowData insertRowData = new InsertRowData("answers");
			insertRowData.setValue("question", mQuestion);
			insertRowData.setValue("username", mUsername);
			insertRowData.setValue("answer", mAnswer);
			MobDB.getInstance().execute(APP_KEY, null, insertRowData, null, false, new MobDBResponseListener() {
				public void mobDBSuccessResponse() {}
				public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}
				public void mobDBResponse(String jsonObj) {}
				public void mobDBFileResponse(String fileName, byte[] fileData) {}
				public void mobDBErrorResponse(Integer errValue, String errMsg) {}
			});
			
			populate();
			sendSMS();
		}
		
		mAnswerbox.setText("");
	}
	
	// populate the list view with the questions, answers, and username's
	public void populate() {
		GetRowData data = new GetRowData("answers");
		data.getField("question");
		data.getField("answer");
		data.getField("username");

		MobDB.getInstance().execute(APP_KEY, null, data, null, false, new MobDBResponseListener() {
			public void mobDBSuccessResponse() { }
			public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {

				List <Map<String,String>> datay = new ArrayList<Map<String,String>>();
				HashMap<String, String> map;
				int count = 0;
				// result.get(0) = first row
				// .get("question") = question attribute
				// [0] since it is a 2D array always have to have [0]
				if (result.size() > 0) {  
					do {
						if (result.get(count).get("question")[0].toString().equals(mQuestion)) {
							map = new HashMap<String, String>();
							map.put("ans", result.get(count).get("answer")[0].toString());
							map.put("name", result.get(count).get("username")[0].toString());
							datay.add(map);
						}
						count++;
					} while (count < result.size());
					
					SimpleAdapter adapter = new SimpleAdapter(
							AnswerQuestionPage.this, datay, 
							android.R.layout.simple_list_item_2, 
							new String[]{"ans","name"}, 
							new int[] {android.R.id.text1, android.R.id.text2});
					mListView.setAdapter(adapter);
			    }
				
			}
			public void mobDBResponse(String jsonObj) {}
			public void mobDBFileResponse(String fileName, byte[] fileData) {}
			public void mobDBErrorResponse(Integer errValue, String errMsg) {}
		});
	}

	// sends an SMS message to another device
	public void sendSMS() {		
		GetRowData data = new GetRowData("questions");
		data.getField("username");
		data.whereEqualsTo("question", mTopQuestion.getText().toString());

		MobDB.getInstance().execute(APP_KEY, null, data, null, false, new MobDBResponseListener() {
			public void mobDBSuccessResponse() { }
			public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
				if (result.size() > 0) {
					String questionUser; 
					questionUser = result.get(0).get("username")[0].toString(); 
					getPhone(questionUser); 
				}
			}
			public void mobDBResponse(String jsonObj) {}
			public void mobDBFileResponse(String fileName, byte[] fileData) {}
			public void mobDBErrorResponse(Integer errValue, String errMsg) {}
		});
	}
	
	public void getPhone(String questionUser) {
		GetRowData data = new GetRowData("users");
		data.getField("phonenum");
		data.whereEqualsTo("username", questionUser);

		MobDB.getInstance().execute(APP_KEY, null, data, null, false, new MobDBResponseListener() {
			public void mobDBSuccessResponse() { }
			public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
				String number;
				// result.get(0) = first row
				// .get("question") = question attribute
				// [0] since it is a 2D array always have to have [0]
				if (result.size() > 0) {
					number = result.get(0).get("phonenum")[0].toString();
					SmsManager sm = SmsManager.getDefault();
					// here is where the destination of the text should go
					sm.sendTextMessage(number, null, mAnswer, null, null);
				}
			}
			public void mobDBResponse(String jsonObj) {}
			public void mobDBFileResponse(String fileName, byte[] fileData) {}
			public void mobDBErrorResponse(Integer errValue, String errMsg) {}
		});	
	}
}
