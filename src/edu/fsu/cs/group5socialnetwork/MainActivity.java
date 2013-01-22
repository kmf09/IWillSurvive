package edu.fsu.cs.group5socialnetwork;
import android.app.Activity;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.mobdb.android.GetRowData;

public class MainActivity extends Activity {
	//final String APP_KEY = "WP37QQ-lDR-0kQ202741padtS7tS710Ji36-BLUjKEcBJpPSpoppop";

	EditText mUserName; EditText mPassword;
	String mPass, mUser; Boolean mIsValid; 
	CheckBox mCheckBox; 

	Cursor mCursor;
	CursorAdapter mCursorAdapter;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mUserName = (EditText)findViewById(R.id.mainUsername);
		mPassword = (EditText)findViewById(R.id.mainPassword);
		mCheckBox = (CheckBox)findViewById(R.id.rememberCheck);

		SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
		mUser = userDetails.getString("username", "");
		mUserName.setText(mUser);
		mPass = userDetails.getString("password", "");
		mPassword.setText(mPass);

		if (!(mUser.equals("") && mPass.equals("")))
			mCheckBox.setChecked(true);

		mIsValid = true;
	}

	public void myLoginHandler(View v){
		//Logs the person in if they have a valid username, if not then
		//they are asked to register, we can test this with a query to the database
		//we set up with the username's and passwords.

		do {
			mUser = mUserName.getText().toString();
			mPass = mPassword.getText().toString();
			invalid(mUserName);
			invalid(mPassword);

			query();
		} while (mIsValid == false);

		if (mIsValid == true) {
			//Where we check the two against one another to make sure they work
			//Then proceed to the categories page
			String TABLE_NAME = "users";
			GetRowData data = new GetRowData(TABLE_NAME);
			data.whereEqualsTo("username", mUser);
			data.andEqualsTo("password", mPass);

			// save preferences if "Remember Me" is checked
			if (mCheckBox.isChecked()) {
				SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
				Editor edit = userDetails.edit();
				edit.clear();
				edit.putString("username", mUser);
				edit.putString("password", mPass);
				edit.commit();
			}
			// otherwise don't save anything
			else {
				SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
				Editor edit = userDetails.edit();
				edit.clear();
				edit.commit();
			}
		}
		else
			Toast.makeText(MainActivity.this, "Invalid username/password. \n Please register or try again.", Toast.LENGTH_SHORT).show();

		/*MobDB.getInstance().execute(APP_KEY, null, data, null, false, new MobDBResponseListener() {
				public void mobDBSuccessResponse() { }
				public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
					if (result.size() > 0) {
						Intent myIntent = new Intent(MainActivity.this, FirstCategories.class);
						startActivity(myIntent);	

						// save preferences if "Remember Me" is checked
						if (mCheckBox.isChecked()) {
							SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
							Editor edit = userDetails.edit();
							edit.clear();
							edit.putString("username", mUser);
							edit.putString("password", mPass);
							edit.commit();
						}
						// otherwise don't save anything
						else {
							SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
							Editor edit = userDetails.edit();
							edit.clear();
							edit.commit();
						}
					}
					else
						Toast.makeText(MainActivity.this, "Invalid username/password. \n Please register or try again.", Toast.LENGTH_SHORT).show();
				}
				public void mobDBResponse(String jsonObj) {}
				public void mobDBFileResponse(String fileName, byte[] fileData) {}
				public void mobDBErrorResponse(Integer errValue, String errMsg) {}
			});
		}*/
	}

	public void query() {
		String[] mProjection = new String[]
		                                  {
				MyCP.COLUMN_USERNAME,
				MyCP.COLUMN_PASSWORD,
		                                  };

		// where username
		String mSelectionClause = MyCP.COLUMN_USERNAME + " = ?";

		// is this equal to this username
		String[] mSelectionArgs = new String[]{mUser};

		mCursor = getContentResolver().query(
				MyCP.CONTENT_URI,
				mProjection,
				mSelectionClause,
				mSelectionArgs,
				null);

		// moveToNext goes to the next row
		// column 0 is column username
		// column 1 is column password 
		if (mCursor.moveToFirst() == true) {
			if (mCursor.getString(1).equals(mPass)) {
				Intent myIntent = new Intent(MainActivity.this, FirstCategories.class);
				startActivity(myIntent);
			}
			else 
				Toast.makeText(MainActivity.this, "Invalid username/password. \n Please register or try again.", Toast.LENGTH_SHORT).show(); 
		}	
		else 
			Toast.makeText(MainActivity.this, "Invalid username/password. \n Please register or try again.", Toast.LENGTH_SHORT).show();
	}

	// check for invalid characters
	public void invalid (EditText edit) {
		String str = edit.getText().toString();
		str = str.toUpperCase();
		for (int i = 0; i < edit.length(); i++) {
			if ((str.charAt(i) == '\'') || (str.charAt(i) == '"') || (str.charAt(i) == '/') || 
					(str.charAt(i) == '\\') || (str.charAt(i) == ';') || (str.charAt(i) == '-') || 
					(str.charAt(i) == '#')) {
				edit.setError("must not contain \', \", /, \\, ;, -, #");
				mIsValid = false; 
			}
			else if (str.substring(0).equals("NULL")) {
				edit.setError("must not contain NULL");
				mIsValid = false;
			}
		}
	}

	public void myRegisterHandler(View v){
		Intent myIntent = new Intent(this, RegisterActivity.class);
		startActivity(myIntent);
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		// save preferences if "Remember Me" is checked
		if (mCheckBox.isChecked()) {
			SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
			Editor edit = userDetails.edit();
			edit.clear();
			edit.putString("username", mUser);
			edit.putString("password", mPass);
			edit.commit();
		}
		else {
			SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
			Editor edit = userDetails.edit();
			edit.clear();
			edit.commit();
		}
	}
}
