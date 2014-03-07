package com.example.paint2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

public class Level1 extends Activity{
	
	String user_name;
    int level_no;
    int clang;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.level1);
		
//Reading Intent
	    
		Intent intent = getIntent();
	    user_name = intent.getStringExtra("user_name");
	    level_no = intent.getIntExtra("level_no", -1);
	    clang = intent.getIntExtra("lang", -1);
	    
	    Log.v("intent data","details "+user_name+" "+level_no+" "+clang);
	    
//Set PagerView
	    
		 ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		 ImageAdapter adapter = new ImageAdapter(this,clang);
		 viewPager.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
