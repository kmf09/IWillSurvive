package edu.fsu.cs.group5socialnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Profile extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String textInfo = readTextFile("desert.txt"); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile, menu);
        return true;
    }
    
    private String readTextFile(String fileName) {
        BufferedReader in = null;
        
        try {
        	String line;
            in = new BufferedReader(new InputStreamReader(this.getAssets().open(fileName)));
            final StringBuilder buffer = new StringBuilder();
            while ((line = in.readLine()) != null)
                buffer.append(line).append(System.getProperty("line.separator"));
            return buffer.toString();
        }
        catch (final IOException e) {
            return "";
        }
        finally
        {
            try {in.close();}
            catch (IOException e) {
                // ignore //
            }
        }
    }
}
