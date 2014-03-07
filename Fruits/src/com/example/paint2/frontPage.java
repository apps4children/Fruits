package com.example.paint2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class frontPage extends Activity{

	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.frontpage);
	}
	
	/** Called when the user clicks the Play button */
	public void levels(View v) {
	    // Do something in response to button
		Intent intent = new Intent(this, SelectLevel.class);
	    startActivity(intent);

	}
	
	/** Called when the user clicks the Record button */
	public void showrecord(View v) {
	    // Do something in response to button
		Intent intent = new Intent(this, SelectLevel2.class);
	    startActivity(intent);
		Log.d("record","clicked");
	}
	
	/** Called when the user clicks the Help button */
	public void help(View v) {
	    // Do something in response to button
		Intent intent = new Intent(this, Help.class);
	    startActivity(intent);

	}
}

