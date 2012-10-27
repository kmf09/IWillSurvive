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

public class SecondCompSciCategories extends Activity {
	final String APP_KEY = "66TP6D-1Ss-00L7SKWoWLlKpaduIiUiUMIR-BLUuIiZxZpPSCIAeua";
	final String TABLE_NAME = "subcats";
	String mCategory = "Computer Science";
	String mNewCat;
	Button mCatButton;
	public ListView mlv;
	EditText mCatName;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_compsci_list);

		mlv = (ListView) findViewById(R.id.listView1);
		mCatButton = (Button)findViewById(R.id.button1);
		mCatName = (EditText)findViewById(R.id.editText1);
		
		populate();
		
	    mlv.setOnItemClickListener(new OnItemClickListener(){
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			String tempcat = (String)mlv.getItemAtPosition(position);
			Intent answerIntent = new Intent(getApplicationContext(), MainQuestionsActivity.class);
			answerIntent.putExtra("subcat", tempcat);
			startActivity(answerIntent);
		}});


	}
	
	public void myNewCategoryHandler(View v){
		if (mCatName.getText().toString() != null) {
			Toast.makeText(SecondCompSciCategories.this, "New Category Added!", Toast.LENGTH_SHORT).show();

			mNewCat = mCatName.getText().toString();

			InsertRowData insertRowData = new InsertRowData("subcats");
			insertRowData.setValue("category", mCategory);
			insertRowData.setValue("subcat", mNewCat);
			MobDB.getInstance().execute(APP_KEY, null, insertRowData, null, false, new MobDBResponseListener() {
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
		GetRowData data = new GetRowData(TABLE_NAME);
		data.getField("subcat");
		data.getField("category");

		MobDB.getInstance().execute(APP_KEY, null, data, null, false, new MobDBResponseListener() {
			public void mobDBSuccessResponse() { }
			public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
				ArrayList<String> toAdd = new ArrayList<String>(); 
				int count = 0; 
				// result.get(0) = first row
				// .get("question") = question attribute
				// [0] since it is a 2D array always have to have [0]
				if (result.size() > 0) { 
					do {
						if (result.get(count).get("category")[0].toString().equals(mCategory)) 
							toAdd.add(result.get(count).get("subcat")[0].toString());
						count++;
					} while (count < result.size());
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondCompSciCategories.this, android.R.layout.simple_list_item_1, android.R.id.text1, toAdd);
					mlv.setAdapter(adapter);
				}
			}
			public void mobDBResponse(String jsonObj) {}
			public void mobDBFileResponse(String fileName, byte[] fileData) {}
			public void mobDBErrorResponse(Integer errValue, String errMsg) {}
		});	
	}
}


