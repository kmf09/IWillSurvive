package edu.fsu.cs.group5socialnetwork;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	EditText mFirstName, mLastName, mUsername, 
			 mPassword, mConfirmPassword, mPhoneNumber, mEmailAddress;
	Boolean mIsValid;
	Cursor mCursor;
 
	// comes to this function first
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set the layout to be register_layout
		setContentView(R.layout.register_layout);
		// initialize this bool
		mIsValid = true;
	}

	// when you press "submit"
	public void myRegisterSubmitHandler(View v){
		// For setting the text of the error message 
		SpannableStringBuilder ssbuilder; 
		
		// reset mIsValid
		mIsValid = true;

		// get a hold on the handler for firstName
		mFirstName = (EditText)findViewById(R.id.firstName);
		// set the string 'first' to be the text found in the handler
		final String first = mFirstName.getText().toString();
		// check to see if mFirstName is valid to prevent SQL Injection
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
			// there's nothing in first name
			if(first.length() == 0) {
				// Set error color text
				// send out an error mesasge 
				ssbuilder = setErrorColor("Please enter first name");
				// this makes it not valid
				mFirstName.setError(ssbuilder);
				mIsValid = false; 
			}
			// must be all if's to have more than one red flag at the same time
			if(last.length() == 0) {
				ssbuilder = setErrorColor("Please enter last name"); 
				mLastName.setError(ssbuilder);
				mIsValid = false;
			}
			if(username.length() == 0) {
				ssbuilder = setErrorColor("Please enter username"); 
				mUsername.setError(ssbuilder);
				mIsValid = false;
			}
			if(password.length() == 0) {
				ssbuilder = setErrorColor("Please enter password"); 
				mPassword.setError(ssbuilder);
				mIsValid = false;
			}
			if(confirm.length() == 0) {
				ssbuilder = setErrorColor("Please confirm password"); 
				mConfirmPassword.setError(ssbuilder);
				mIsValid = false;
			}
			else if(!confirm.equals(password)) {
				ssbuilder = setErrorColor("Passwords do not match"); 
				mConfirmPassword.setError(ssbuilder);
				mIsValid = false;
			}
			if(phone.length() == 0) {
				ssbuilder = setErrorColor("Please enter phone number"); 
				mPhoneNumber.setError(ssbuilder);
				mIsValid = false;
			}
			if(email.length() == 0) {
				ssbuilder = setErrorColor("Please enter email"); 
				mEmailAddress.setError(ssbuilder);
				mIsValid = false;
			}
			if(doesUserExist(username) == false) {
				ssbuilder = setErrorColor("Username already exists"); 
				mUsername.setError(ssbuilder);
				mIsValid = false; 
			}

			// contrats, you've made it to the end
			if (mIsValid == true) {
				Uri mNewUri;

				ContentValues mNewValues = new ContentValues();

				// now insert the information into the database
				// get ready to be inserted
				mNewValues.put(MyCP.COLUMN_FIRSTNAME, first);
				mNewValues.put(MyCP.COLUMN_LASTNAME, last);
				mNewValues.put(MyCP.COLUMN_USERNAME, username);
				mNewValues.put(MyCP.COLUMN_PASSWORD, password);
				mNewValues.put(MyCP.COLUMN_PHONENUM, phone);
				mNewValues.put(MyCP.COLUMN_EMAILADDR, email);

				// insert here
				mNewUri = getContentResolver().insert(MyCP.CONTENT_URI, mNewValues);

				// say thanks
				Toast.makeText(RegisterActivity.this, "Thanks for registering!", Toast.LENGTH_SHORT).show();

				// start new activity
				Intent myIntent = new Intent(RegisterActivity.this, FirstCategories.class);
				startActivity(myIntent);
				mIsValid = false;
			}
		}
	}
	
	public SpannableStringBuilder setErrorColor(String estring) {
		int ecolor = -16777216; // whatever color you want
		ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
		SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
		ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
		return ssbuilder; 
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
