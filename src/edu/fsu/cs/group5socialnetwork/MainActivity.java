package edu.fsu.cs.group5socialnetwork;
import java.util.HashMap;
import java.util.Vector;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.mobdb.android.GetRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

public class MainActivity extends Activity {
	final String APP_KEY = "66TP6D-1Ss-00L7SKWoWLlKpaduIiUiUMIR-BLUuIiZxZpPSCIAeua";
	final String TABLE_NAME = "users";
	
	EditText mUserName; EditText mPassword;
	String mPass, mUser; Boolean mBooly; 
	CheckBox mCheckBox; 

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

		mBooly = true;
	}

	public void myLoginHandler(View v){
		//Logs the person in if they have a valid username, if not then
		//they are asked to register, we can test this with a query to the database
		//we set up with the username's and passwords.
		mUser = mUserName.getText().toString();
		mPass = mPassword.getText().toString();
		invalid(mUserName);
		invalid(mPassword);

		if (mBooly == true) {
			//Where we check the two against one another to make sure they work
			//Then proceed to the categories page
			GetRowData data = new GetRowData(TABLE_NAME);
			data.whereEqualsTo("username", mUser);
			data.andEqualsTo("password", mPass);

			MobDB.getInstance().execute(APP_KEY, null, data, null, false, new MobDBResponseListener() {
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
				mBooly = false; 
			}
			else if (str.substring(0).equals("NULL")) {
				edit.setError("must not contain NULL");
				mBooly = false;
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
