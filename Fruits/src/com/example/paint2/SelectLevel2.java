package com.example.paint2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SelectLevel2 extends Activity {

	//private ListView mainListView ;  
	//private ArrayAdapter<String> listAdapter ;  
	
	private String name;
	private int clang=1;	//1 for english and 0 for hindi
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels2);
	    
        readname(0);
    }

    private void readname(int a) {
		// TODO Auto-generated method stub
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
    	alert.setCancelable(false);
    	if(a == 1)
    		alert.setTitle("Student Name should not be blank");
    	else
    		alert.setTitle("Student Name");
		

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			
			String value = input.getText().toString();
			if(value.equals("")){
				Log.d("input", "null input");
				readname(1);
			}
			else{
				setname(value);
				Log.d("input success", "input successed " + value );
			}	
		}
		});
		alert.show();
	}

    private void setname(String value) {
		name = value;
		TextView nm = (TextView) findViewById(R.id.user_name2);
        nm.setText("Welcome " + name);
		Log.d("dialog","dialog " + name);
	}
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void l2(View v) {
	    // Do something in response to button
    	Intent intent;
    	intent = new Intent(this, SQLView.class);
		
		String message = name;
    	int level_no = 2;
    	intent.putExtra("user_name", message);
    	intent.putExtra("level_no", level_no);
    	intent.putExtra("lang", clang);
	    startActivity(intent);
	}
    
    public void l3(View v) {
    	Intent intent;
    	intent = new Intent(this, SQLView.class);
		
		String message = name;
    	int level_no = 3;
    	intent.putExtra("user_name", message);
    	intent.putExtra("level_no", level_no);
    	intent.putExtra("lang", clang);
	    startActivity(intent);
	}
    
    public void l5(View v) {
	    // Do something in response to button
    	Intent intent;
    	intent = new Intent(this, SQLView.class);
    	
    	String message = name;
    	int level_no = 5;
    	intent.putExtra("user_name", message);
    	intent.putExtra("level_no", level_no);
    	intent.putExtra("lang", clang);
	    startActivity(intent);
    	//Toast.makeText(getApplicationContext(),"level 5 not ready",Toast.LENGTH_SHORT).show();
	}
    
    public void l6(View v) {
	    // Do something in response to button
    	Intent intent;
    	
    	intent = new Intent(this, SQLView.class);
    	
    	String message = name;
    	int level_no = 6;
    	intent.putExtra("user_name", message);
    	intent.putExtra("level_no", level_no);
    	intent.putExtra("lang", clang);
	    startActivity(intent);
	}
    
    public void set_eng(View v) {
    	clang = 1;
    }
    
    public void set_hind(View v) {
    	clang = 0;
    }
}