package com.example.paint2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SQLView extends Activity {
	
	String user_name;
	int level_no;
	int clang;
	Mistakes info = new Mistakes(this);
	
	 private ListView mainListView ;  
	 private ArrayAdapter<String> listAdapter ;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview1);

//Intent
		Intent intent = getIntent();
	    user_name = intent.getStringExtra("user_name");
	    level_no = intent.getIntExtra("level_no", -1);
	    clang = intent.getIntExtra("lang", -1);
	    
	    createListView();
	}
	
	private void createListView() {
		// Find the ListView resource.   
	    mainListView = (ListView) findViewById( R.id.mainListView );  
	  
// Create and populate a List of planet names.
	    
	  //  String[] records = getrecords();    
	    
	    ArrayList<String> recordList = new ArrayList<String>();
	    recordList = getrecords();
	    //recordList.addAll( Arrays.asList(records) );  
	      
// Create ArrayAdapter using the planet list.  
	    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, recordList);
	    
	    
// Set the ArrayAdapter as the ListView's adapter.  
	    mainListView.setAdapter( listAdapter );   
		
	}

	private ArrayList<String> getrecords() {
		
		//TextView tv = (TextView)findViewById(R.id.textView1);
		info.setname(user_name);
		info.setlevel(level_no);
		info.setlang(clang);
		info.open();
		//String[] data = info.getData();
		ArrayList<String> data = info.getData();
		info.close();
		//tv.setText(data);
		return data;
	}
	
	public void clearlog(View v){
		info.open();
		int t = info.clearLog();
		info.close();
		Toast.makeText(getApplicationContext(), "Deleted " + t + " rows", Toast.LENGTH_LONG).show();
		createListView();
		//info.clearLog();
	}
}