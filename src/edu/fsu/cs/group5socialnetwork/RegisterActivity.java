package edu.fsu.cs.group5socialnetwork;

import java.util.HashMap;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
	final String APP_KEY = "66TP6D-1Ss-00L7SKWoWLlKpaduIiUiUMIR-BLUuIiZxZpPSCIAeua";
	final String TABLE_NAME = "users";

	EditText mFirstName, mLastName, mUsername, mPassword, mConfirmPassword, mPhoneNumber, mEmailAddress;
	Boolean booly;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		booly = true;
	}

	public void myRegisterSubmitHandler(View v){
		GetRowData data = new GetRowData(TABLE_NAME);

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

		if (booly == true) {
			//Will submit the data that the user submitted to the database that we
			//have setup so we can use that data later. Should work similar to logging in
			//after the user has created a valid username.
			data.whereEqualsTo("username",  username);

			MobDB.getInstance().execute(APP_KEY, data, null, false, new MobDBResponseListener() {
				public void mobDBSuccessResponse() {
					//Toast.makeText(RegisterActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
				}
				public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {
					if (result.size() > 0)
						Toast.makeText(RegisterActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
					else {
						InsertRowData insertRowData = new InsertRowData(TABLE_NAME);
						insertRowData.setValue("firstname", first);
						insertRowData.setValue("lastname",  last);
						insertRowData.setValue("username",  username);
						insertRowData.setValue("password",  password);
						insertRowData.setValue("phonenum",  phone);
						insertRowData.setValue("emailaddr", email);
						MobDB.getInstance().execute(APP_KEY, insertRowData, null, false, new MobDBResponseListener() {
							public void mobDBSuccessResponse() {}
							public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}
							public void mobDBResponse(String jsonObj) {}
							public void mobDBFileResponse(String fileName, byte[] fileData) {}
							public void mobDBErrorResponse(Integer errValue, String errMsg) {}
						});

						Toast.makeText(RegisterActivity.this, "Thanks for registering!", Toast.LENGTH_SHORT).show();

						Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
						startActivity(myIntent);
					}
				}
				public void mobDBResponse(String jsonObj) {}
				public void mobDBFileResponse(String fileName, byte[] fileData) {}
				public void mobDBErrorResponse(Integer errValue, String errMsg) {}
			});

			if(first.length() == 0)
				mFirstName.setError("Please fill in your first name");
			if(last.length() == 0)
				mLastName.setError("Please fill in your last name");
			if(username.length() == 0)
				mUsername.setError("Please fill in your username");
			if(password.length() == 0)
				mPassword.setError("Please fill in your password");
			if(confirm.length() == 0)
				mConfirmPassword.setError("Please confirm your password");
			else if(!confirm.equals(password))
				mConfirmPassword.setError("Passwords do not match");
			if(phone.length() == 0)
				mPhoneNumber.setError("Please fill in your phone number");
			if(email.length() == 0)
				mEmailAddress.setError("Please fill in your email");
		}
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
				booly = false; 
			}
			else if (str.substring(0).equals("NULL")) {
				edit.setError("must not contain NULL");
				booly = false;
			}
		}
	}

	public void myRegisterClearHandler(View v){
		setContentView(R.layout.register_layout);
	}
}
