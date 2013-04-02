package edu.fsu.cs.group5socialnetwork;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.mobdb.android.GetRowData;

public class MainActivity extends Activity {

	// global variables
	public static EditText mUserName; EditText mPassword;
	String mPass, mUser; Boolean mIsValid; 
	CheckBox mCheckBox; 
	Cursor mCursor;
	CursorAdapter mCursorAdapter;
	TextView logout; 

	// always goes into this function first
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// create the layout on the screen, the layout is called activity_main
		setContentView(R.layout.activity_main);
		// lock the orientation to portrait view
		// if you try to change to landscape mode the app will not rotate with it
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// get a handler on these items
		mUserName = (EditText)findViewById(R.id.mainUsername);
		mPassword = (EditText)findViewById(R.id.mainPassword);
		mCheckBox = (CheckBox)findViewById(R.id.rememberCheck);
		logout = (TextView)findViewById(R.id.logoutTV);

		// get previously saved information
		SharedPreferences userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_WORLD_READABLE);
		mUser = userDetails.getString("username", "");
		mUserName.setText(mUser);
		mPass = userDetails.getString("password", "");
		mPassword.setText(mPass);

		if (!(mUser.equals("") && mPass.equals("")))
			mCheckBox.setChecked(true);

		// set the bool to make sure the credentials are valid to true
		mIsValid = true;
	}

	// event handler for when you click the "login" button
	public void myLoginHandler(View v){
		//Logs the person in if they have a valid username, if not then
		//they are asked to register, we can test this with a query to the database
		//we set up with the username's and passwords.

		do {
			// get the username they typed in
			mUser = mUserName.getText().toString();
			// get the password they typed in
			mPass = mPassword.getText().toString();
			// if either one of these are invalid then mIsValid will be set to false
			// and the loop will continue
			invalid(mUserName);
			invalid(mPassword);

			// checks to make sure the user name is in the database 
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
	}

	public void query() {
		String[] mProjection = new String[] { MyCP.COLUMN_USERNAME, MyCP.COLUMN_PASSWORD, };

		// WHERE username
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

	// when you click "register" button
	public void myRegisterHandler(View v){
		// set a new intent to go to the RegisterActiivty page
		Intent myIntent = new Intent(this, RegisterActivity.class);
		// go!
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
	
	public void logoutNow(View v) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}
	
	public void sendReminder(View v) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"kmf09@my.fsu.edu"});
		i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
		i.putExtra(Intent.EXTRA_TEXT   , "body of email");
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(this, "E-mail has been sent", Toast.LENGTH_SHORT).show(); 
	}
}
