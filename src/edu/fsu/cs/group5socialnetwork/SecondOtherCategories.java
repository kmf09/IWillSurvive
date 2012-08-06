package edu.fsu.cs.group5socialnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.mobdb.android.GetRowData;
import com.mobdb.android.InsertRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SecondOtherCategories extends Activity {
	String APP_KEY = "66TP6D-1Ss-00L7SKWoWLlKpaduIiUiUMIR-BLUuIiZxZpPSCIAeua";
	String mCategory = "History";
	String mNewCat;
	ListView lv;
	Button mCatButton;
	EditText mCatName;

	@Override public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.second_history_list);
	    
	    lv = (ListView) findViewById(R.id.listView1);
	    mCatButton = (Button)findViewById(R.id.button1);
	    mCatName = (EditText)findViewById(R.id.editText1);
	    
	    // populate the list view with questions
	    populate();
	    
	    lv.setOnItemClickListener(new OnItemClickListener(){
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			String tempcat = (String)lv.getItemAtPosition(position);
			Intent answerIntent = new Intent(getApplicationContext(), MainQuestionsActivity.class);
			answerIntent.putExtra("subcat", tempcat);
			startActivity(answerIntent);
		}});
	}
	
	
	public void myNewmCategoryHandler(View v){
		if (mCatName.getText().toString() != null) {
			Toast.makeText(SecondOtherCategories.this, "New mCategory Added!", Toast.LENGTH_SHORT).show();

			mNewCat = mCatName.getText().toString();

			InsertRowData insertRowData = new InsertRowData("subcats");
			insertRowData.setValue("mCategory", mCategory);
			insertRowData.setValue("subcat", mNewCat);
			MobDB.getInstance().execute(APP_KEY, insertRowData, null, false, new MobDBResponseListener() {
				public void mobDBSuccessResponse() {}
				public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}
				public void mobDBResponse(String jsonObj) {}
				public void mobDBFileResponse(String fileName, byte[] fileData) {}
				public void mobDBErrorResponse(Integer errValue, String errMsg) {}
			});
			
		}
		populate();
		mCatName.setText("");
	}
	
	public void populate() {
		String TABLE_NAME = "subcats";
		GetRowData data = new GetRowData(TABLE_NAME);
		data.getField("subcat");
		data.getField("mCategory");

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
						if (result.get(count).get("mCategory")[0].toString().equals(mCategory)) 
							toAdd.add(result.get(count).get("subcat")[0].toString());
						count++;
					} while (count < result.size());
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondOtherCategories.this, android.R.layout.simple_list_item_1, android.R.id.text1, toAdd);
					lv.setAdapter(adapter);
				}
			}
			public void mobDBResponse(String jsonObj) {}
			public void mobDBFileResponse(String fileName, byte[] fileData) {}
			public void mobDBErrorResponse(Integer errValue, String errMsg) {}
		});	
	}
}
