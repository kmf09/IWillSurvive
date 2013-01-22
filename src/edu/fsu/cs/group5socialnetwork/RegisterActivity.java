package edu.fsu.cs.group5socialnetwork;

import java.util.HashMap;
import java.util.Vector;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.mobdb.android.GetRowData;
import com.mobdb.android.InsertRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

public class RegisterActivity extends Activity {
	// for database 
	//final String APP_KEY = "WP37QQ-lDR-0kQ202741padtS7tS710Ji36-BLUjKEcBJpPSpoppop";
	//final String TABLE_NAME = "users";

	EditText mFirstName;
	EditText mLastName;
	EditText mUsername;
	EditText mPassword;
	EditText mConfirmPassword;
	EditText mPhoneNumber;
	EditText mEmailAddress;
	Boolean mIsValid;
	Cursor mCursor;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		mIsValid = true;
	}

	public void myRegisterSubmitHandler(View v){ 
		mIsValid = true;

		mFirstName = (EditText)findViewById(R.id.firstName);
		final String first = mFirstName.getText().toString();
		invalid(mFirstName);
		mLastName = (EditText)findViewById(R.id.lastName);
		final String last = mLastName.getText().toString();
		invalid(mLastName);
		mUsername = (EditText)findViewById(R.id.username);
		final String username = mUsername.getText().toString();
		invalid(mUsername);
		mPassword = (EditText)findViewById(R.id.password);
		final String password = mPassword.getText().toString();
		invalid(mPassword);
		mConfirmPassword = (EditText)findViewById(R.id.confirmPassword);
		String confirm = mConfirmPassword.getText().toString();
		invalid(mConfirmPassword);
		mPhoneNumber = (EditText)findViewById(R.id.phoneNumber);
		final String phone = mPhoneNumber.getText().toString();
		invalid(mPhoneNumber);
		mEmailAddress = (EditText)findViewById(R.id.emailAddress);
		final String email = mEmailAddress.getText().toString();
		invalid(mEmailAddress);

		while (mIsValid == true) { 
			if(first.length() == 0) {
				mFirstName.setError("Please fill in your first name");
				mIsValid = false; 
			}
			if(last.length() == 0) {
				mLastName.setError("Please fill in your last name");
				mIsValid = false;
			}
			if(username.length() == 0) {
				mUsername.setError("Please fill in your username");
				mIsValid = false;
			}
			if(password.length() == 0) {
				mPassword.setError("Please fill in your password");
				mIsValid = false;
			}
			if(confirm.length() == 0) {
				mConfirmPassword.setError("Please confirm your password");
				mIsValid = false;
			}
			else if(!confirm.equals(password)) {
				mConfirmPassword.setError("Passwords do not match");
				mIsValid = false;
			}
			if(phone.length() == 0) {
				mPhoneNumber.setError("Please fill in your phone number");
				mIsValid = false;
			}
			if(email.length() == 0) {
				mEmailAddress.setError("Please fill in your email");
				mIsValid = false;
			}
			if(doesUserExist(username) == false) {
				mUsername.setError("Username already exists");
				mIsValid = false; 
			}

			if (mIsValid == true) {
				Uri mNewUri;

				ContentValues mNewValues = new ContentValues();

				mNewValues.put(MyCP.COLUMN_FIRSTNAME, first);
				mNewValues.put(MyCP.COLUMN_LASTNAME, last);
				mNewValues.put(MyCP.COLUMN_USERNAME, username);
				mNewValues.put(MyCP.COLUMN_PASSWORD, password);
				mNewValues.put(MyCP.COLUMN_PHONENUM, phone);
				mNewValues.put(MyCP.COLUMN_EMAILADDR, email);

				mNewUri = getContentResolver().insert(MyCP.CONTENT_URI, mNewValues);

				Toast.makeText(RegisterActivity.this, "Thanks for registering!", Toast.LENGTH_SHORT).show();

				Intent myIntent = new Intent(RegisterActivity.this, FirstCategories.class);
				startActivity(myIntent);
				mIsValid = false;
			}
		}
	}
	
	public boolean doesUserExist(String username) {
		String[] mProjection = new String[] { MyCP.COLUMN_USERNAME, };

		// where username
		String mSelectionClause = MyCP.COLUMN_USERNAME + " = ?";

		// is this equal to this username
		String[] mSelectionArgs = new String[]{username};

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
			return false;  
		}	
		else 
			return true; 
	
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

	public void myRegisterClearHandler(View v){
		setContentView(R.layout.register_layout);
	}
}
