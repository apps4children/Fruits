package com.example.paint2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Level5 extends Activity {
	
	paintView2 paintview2;
	
	private int width;
	private int length;
	private int cwidth;
	private int hspace;
	private int vspace;
	private int dpHeight;
	private int dpWidth;

	private int max_image=14;//no of images
	private int image=0;
	
	private QAPair[] questions = new QAPair[14];
	//List<Integer> ques2 = new ArrayList<Integer>(3);
	List<Integer> ans_choice = new ArrayList<Integer>(3);
	int result;	
	Integer[] mThumbIds = new Integer[4]; 
	
	String user_name;
    int level_no;
    int clang;
    Animation myFade;
    Toast toast;
	View layout;
	ImageView img_toast;
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

//for paintview2 to work
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
//for dimension
		Display display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics outMetrics = new DisplayMetrics ();
	    display.getMetrics(outMetrics);

//Reading Intent
	    
	    Intent intent = getIntent();
	    user_name = intent.getStringExtra("user_name");
	    level_no = intent.getIntExtra("level_no", -1);
	    clang = intent.getIntExtra("lang", -1);
	    
	    Log.v("intent data","details "+user_name+" "+level_no+" "+clang);
	    	    
	    
//Setting dimensions
	    setdimensions(outMetrics);	    	     	    

//Loading images
	    
	    loadImage();
	    set_ans_choice();
	    
//Initialise paintView
	    paintview2 = new paintView2(this,cwidth,vspace,length,hspace,width,user_name,level_no,clang);
	    paintview2.setOrientation(LinearLayout.HORIZONTAL);
	    paintview2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,dpHeight-2*vspace-5));
	    paintview2.setId(999998);
		paintview2.setBackgroundColor(Color.WHITE);
		paintview2.requestFocus();
		paintview2.setres(result);
		
		//parameter for images with margins
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, length);
		lp.setMargins(0, vspace, 0, 0);

		//grayscale
		 //ColorMatrix matrix = new ColorMatrix();
		 //matrix.setSaturation(0);

		 //ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
		    
        //left side
		LinearLayout llayout1 = new LinearLayout(this);
		llayout1.setOrientation(LinearLayout.VERTICAL);
		llayout1.setLayoutParams(new LayoutParams(cwidth,dpHeight-2*vspace-5));
		llayout1.setGravity(Gravity.CENTER);
		
		ImageView imques = new ImageView(this);
		imques.setLayoutParams(lp);
		if(level_no==6) imques.setScaleType(ImageView.ScaleType.FIT_XY);
    	else if(level_no==5) imques.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imques.setPadding(6, 6, 6, 6);
		imques.setBackgroundColor(Color.LTGRAY);
		imques.setId(0);
		imques.setImageResource(questions[image].qget());
		//imques.setColorFilter(filter);
		
		mThumbIds[0]=questions[image].qget();
		
		((LinearLayout) llayout1).addView(imques);
		
//right side
		LinearLayout llayout2 = new LinearLayout(this);
		llayout2.setOrientation(LinearLayout.VERTICAL);
		llayout2.setLayoutParams(new LayoutParams(cwidth,dpHeight-2*vspace-5));
		llayout2.setGravity(Gravity.CENTER);
		
		ImageView imans1 = new ImageView(this);
		imans1.setLayoutParams(lp);
		if(level_no==5) imans1.setScaleType(ImageView.ScaleType.FIT_XY);
    	else if(level_no==6) imans1.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imans1.setPadding(6, 6, 6, 6);
		imans1.setBackgroundColor(Color.LTGRAY);
		imans1.setId(1);
		imans1.setImageResource(ans_choice.get(0));
		
		ImageView imans2 = new ImageView(this);
		imans2.setLayoutParams(lp);
		if(level_no==5) imans2.setScaleType(ImageView.ScaleType.FIT_XY);
    	else if(level_no==6) imans2.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imans2.setPadding(6, 6, 6, 6);
		imans2.setBackgroundColor(Color.LTGRAY);
		imans2.setId(2);
		imans2.setImageResource(ans_choice.get(1));
		
		ImageView imans3 = new ImageView(this);
		imans3.setLayoutParams(lp);
		if(level_no==5) imans3.setScaleType(ImageView.ScaleType.FIT_XY);
    	else if(level_no==6) imans3.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imans3.setPadding(6, 6, 6, 6);
		imans3.setBackgroundColor(Color.LTGRAY);
		imans3.setId(3);
		imans3.setImageResource(ans_choice.get(2));
			int i;
		for(i=1;i<4;i++)
		{
			mThumbIds[i]=ans_choice.get(i-1);
		}
		
		paintview2.setThumbIds(mThumbIds);
		((LinearLayout) llayout2).addView(imans1);
		((LinearLayout) llayout2).addView(imans2);
		((LinearLayout) llayout2).addView(imans3);
		
//combine to paintview2
		((paintView2) paintview2).addView(llayout1);
		((paintView2) paintview2).addView(llayout2);
		
		
//Setting Content View
	    setContentView(R.layout.level5);//R.layout.level5
		View linearLayout =  findViewById(R.id.level5_dad);
		
//TextView
		
        TextView valueTV = new TextView(this);
        valueTV.setText("Match The Following Fruits");	//""+dpHeight+"X"+dpWidth+" d"+density
        valueTV.setHeight(vspace);
        valueTV.setTextSize(vspace/4);
        valueTV.setGravity(Gravity.CENTER);
        
//Setting next button
        
        Button b = new Button(this);
        b.setText("Next");
        b.setGravity(Gravity.CENTER);
        b.setPadding(vspace/10, vspace/10, vspace/10, vspace/10);
        b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				while(paintview2.done(1)==1)
				{
					paintview2.playerreset();
					Log.d("","row button");
					image+=1;
					ans_choice.clear();
					if(image==max_image)
					{
						img_toast.startAnimation(myFade);
			    		toast.show();
						finish();
					}
					else
					{
						set_ans_choice();
						 
						ImageView imgq = (ImageView) findViewById(0);
						imgq.setImageResource(questions[image].qget());
						
						imgq = (ImageView) findViewById(1);
						imgq.setImageResource(ans_choice.get(0));
						
						imgq = (ImageView) findViewById(2);
						imgq.setImageResource(ans_choice.get(1));
						
						imgq = (ImageView) findViewById(3);
						imgq.setImageResource(ans_choice.get(2));
						paintview2.points.clear();
						paintview2.points2.clear();
						paintview2.setres(result);
						int i;
						mThumbIds[0]=questions[image].qget();
						for(i=1;i<4;i++)
						{
							mThumbIds[i]=ans_choice.get(i-1);
						}
						paintview2.setThumbIds(mThumbIds);
						Log.d("","row button");
						
					}
				}
			}
		});
        
//Appending Child View
        ((LinearLayout) linearLayout).addView(valueTV);
        ((LinearLayout) linearLayout).addView(paintview2);
        ((LinearLayout) linearLayout).addView(b);
        
//toast
	    
	    myFade = AnimationUtils.loadAnimation(Level5.this, R.anim.tween2);
	    LayoutInflater inflater = getLayoutInflater();
    	layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_layout_root));
    	img_toast = (ImageView) layout.findViewById(R.id.timage);
    	
    	
    	toast = new Toast(Level5.this);
		toast.setGravity(Gravity.FILL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
//toast over
        
		
	}

	//ONSTOP ACTIVITY STARTS
	protected void onStop() {
	    super.onStop();
	    paintview2.playerstop();
	}
	//ONSTOP ACTIVITY ENDS

	
	private void set_ans_choice() {
		Log.d("iWasHere", "i was here 1 and image = " + image);
		Log.d("iWasHere", "size" + questions[image].aget());
		ans_choice.add(questions[image].aget());
		Log.d("worked", "i was here 2");
		ans_choice.add(questions[(image+1)%max_image].aget());
		ans_choice.add(questions[(image+2)%max_image].aget());
		
		int quesid=ans_choice.get(0);
		
		Collections.shuffle(ans_choice);
		
		result=ans_choice.indexOf(quesid);
	}

	private void loadImage() {
		
		Log.d("loadImage", "loadImage enter");
		
		if(level_no==5)
		{
			if(clang==1)
			{
				questions[0]= new QAPair(R.drawable.apple_1 ,R.drawable.apple);
		        questions[1]= new QAPair(R.drawable.banana_1 ,R.drawable.banana);
		           // changes to be made
		        questions[2]= new QAPair(R.drawable.grapes_1,R.drawable.grapes);
		        
		        questions[3]= new QAPair(R.drawable.guava_1,R.drawable.guava);
		        	//changes to be made
		        questions[4]= new QAPair(R.drawable.pomegranate_1,R.drawable.pomegranate);
		        
		        questions[5]= new QAPair(R.drawable.lychee_1,R.drawable.lychee);
		        questions[6]= new QAPair(R.drawable.muskmelon_1,R.drawable.melon);
		        questions[7]= new QAPair(R.drawable.orange_1,R.drawable.orange);
		        questions[8]= new QAPair(R.drawable.papaya_1,R.drawable.papaya);
		        questions[9]= new QAPair(R.drawable.pineapple_1,R.drawable.pineapple);
		        questions[10]= new QAPair(R.drawable.sapodilla_1,R.drawable.sapodilla);
		        questions[11]= new QAPair(R.drawable.strawberry_1,R.drawable.strawberry);
		        questions[12]= new QAPair(R.drawable.custardapple_1,R.drawable.custardapple);
		        questions[13]= new QAPair(R.drawable.watermelon_1,R.drawable.watermelon);
			}
			else if(clang==0)
			{
				questions[0]= new QAPair(R.drawable.apple_2 ,R.drawable.apple);
		        questions[1]= new QAPair(R.drawable.banana_2 ,R.drawable.banana);
		        questions[2]= new QAPair(R.drawable.grapes_2,R.drawable.grapes);
		        questions[3]= new QAPair(R.drawable.guava_2,R.drawable.guava);
		        questions[4]= new QAPair(R.drawable.pomegranate_2,R.drawable.pomegranate);
		        questions[5]= new QAPair(R.drawable.lychee_2,R.drawable.lychee);
		        questions[6]= new QAPair(R.drawable.melon_2,R.drawable.melon);
		        questions[7]= new QAPair(R.drawable.orange_2,R.drawable.orange);
		        questions[8]= new QAPair(R.drawable.papaya_2,R.drawable.papaya);
		        questions[9]= new QAPair(R.drawable.pineapple_2,R.drawable.pineapple);
		        questions[10]= new QAPair(R.drawable.sapodilla_2,R.drawable.sapodilla);
		        questions[11]= new QAPair(R.drawable.strawberry_2,R.drawable.strawberry);
		        questions[12]= new QAPair(R.drawable.custardapple_2,R.drawable.custardapple);
		        questions[13]= new QAPair(R.drawable.watermelon_2,R.drawable.watermelon);
			}
		}
		else if(level_no==6)
		{
			if(clang==1)
			{
				questions[0]= new QAPair(R.drawable.apple ,R.drawable.apple_1);
		        questions[1]= new QAPair(R.drawable.banana ,R.drawable.banana_1);
		        questions[2]= new QAPair(R.drawable.grapes,R.drawable.grapes_1);
		        questions[3]= new QAPair(R.drawable.guava,R.drawable.guava_1);
		        questions[4]= new QAPair(R.drawable.pomegranate,R.drawable.pomegranate_1);
		        questions[5]= new QAPair(R.drawable.lychee,R.drawable.lychee_1);
		        questions[6]= new QAPair(R.drawable.melon,R.drawable.muskmelon_1);
		        questions[7]= new QAPair(R.drawable.orange,R.drawable.orange_1);
		        questions[8]= new QAPair(R.drawable.papaya,R.drawable.papaya_1);
		        questions[9]= new QAPair(R.drawable.pineapple,R.drawable.pineapple_1);
		        questions[10]= new QAPair(R.drawable.sapodilla,R.drawable.sapodilla_1);
		        questions[11]= new QAPair(R.drawable.strawberry,R.drawable.strawberry_1);
		        questions[12]= new QAPair(R.drawable.custardapple,R.drawable.custardapple_1);
		        questions[13]= new QAPair(R.drawable.watermelon,R.drawable.watermelon_1);
			}
			else if(clang==0)
			{
				questions[0]= new QAPair(R.drawable.apple ,R.drawable.apple_2);
		        questions[1]= new QAPair(R.drawable.banana ,R.drawable.banana_2);
		        questions[2]= new QAPair(R.drawable.grapes,R.drawable.grapes_2);
		        questions[3]= new QAPair(R.drawable.guava,R.drawable.guava_2);
		        questions[4]= new QAPair(R.drawable.pomegranate,R.drawable.pomegranate_2);
		        questions[5]= new QAPair(R.drawable.lychee,R.drawable.lychee_2);
		        questions[6]= new QAPair(R.drawable.melon,R.drawable.melon_2);
		        questions[7]= new QAPair(R.drawable.orange,R.drawable.orange_2);
		        questions[8]= new QAPair(R.drawable.papaya,R.drawable.papaya_2);
		        questions[9]= new QAPair(R.drawable.pineapple,R.drawable.pineapple_2);
		        questions[10]= new QAPair(R.drawable.sapodilla,R.drawable.sapodilla_2);
		        questions[11]= new QAPair(R.drawable.strawberry,R.drawable.strawberry_1);
		        questions[12]= new QAPair(R.drawable.custardapple,R.drawable.custardapple_2);
		        questions[13]= new QAPair(R.drawable.watermelon,R.drawable.watermelon_2);
			}
		}
      //  Log.d("loadImage2", "loadImage worked2!!");
        
        Collections.shuffle(Arrays.asList(questions));
	}

	//sets the dimension
	private void setdimensions(DisplayMetrics outMetrics) {
		dpHeight = (int)(outMetrics.heightPixels);
	    dpWidth  = (int)(outMetrics.widthPixels);
        
	    cwidth = dpWidth/2;
	    width = (int)(0.6 * cwidth);
	    
	    vspace = (int)(dpHeight/15);
	    
	    length = (int)(3*dpHeight/15);
	    
	    hspace = dpWidth - 2*cwidth; 
	    		
	    Log.v("screen size",""+dpHeight+"X"+dpWidth);
	}
}