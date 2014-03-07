package com.example.paint2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
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
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity {
	

	
	PaintView paintView;
	LinearLayout ll;
	int image=1;
	int n =3;
	int width;
	int length;
	int cwidth;
	int hspace;
	int vspace = 50;
	int click =0;
	int dpHeight;
	int dpWidth;
	int max_image=14;//no of images
	int[] result = new int[3];
	QAPair[] questions = new QAPair[max_image];
    List<Integer> ques2 = new ArrayList<Integer>(3);
    List<Integer> ques3 = new ArrayList<Integer>(3);
    List<Integer> ans = new ArrayList<Integer>(6);
    int first_screen=1;
    public Integer[] mThumbIds = new Integer[6];
    ImageAdapter ia; 
    Bundle b;
    Animation myFade;
    Toast toast;
	View layout;
	ImageView img_toast;
    String user_name;
    int level_no;
    int clang;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);	    

//Reading Intent
	    
	    Intent intent = getIntent();
	    user_name = intent.getStringExtra("user_name");
	    level_no = intent.getIntExtra("level_no", -1);
	    clang = intent.getIntExtra("lang", -1);
	    
	    Log.v("intent data","details "+user_name+" "+level_no+" "+clang);

		ia = new ImageAdapter(this); 
		b=savedInstanceState;
		ia.notifyDataSetChanged();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Display display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics outMetrics = new DisplayMetrics ();
	    display.getMetrics(outMetrics);


//Setting dimensions
	    
	    dpHeight = (int)(outMetrics.heightPixels);
	    dpWidth  = (int)(outMetrics.widthPixels);
        
	    cwidth = dpWidth/2;
	    width = (int)(0.6 * cwidth);
	    
	    vspace = (int)(dpHeight/15);
	    
	    length = (int)(3*dpHeight/15);
	    
	    hspace = dpWidth - 2*cwidth; 
	    		
	    Log.v("screen size",""+dpHeight+"X"+dpWidth);

//Initialise paintView
	    
		paintView = new PaintView(this,cwidth,vspace,length,hspace,width,user_name,level_no,clang);
		paintView.setId(999999);
		paintView.setBackgroundColor(Color.WHITE);
		paintView.setNumColumns(2);
		paintView.setColumnWidth(cwidth);
		paintView.setVerticalSpacing(vspace);
		paintView.setHorizontalSpacing(hspace);
		paintView.setStretchMode(GridView.NO_STRETCH);
		paintView.setGravity(Gravity.CENTER);
		paintView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,dpHeight-2*vspace-5));
		paintView.setPadding(0, vspace, 0, 0);
		paintView.setAdapter(ia);
		paintView.requestFocus();
		paintView.setres(result);
		paintView.setThumbIds(mThumbIds);
		
//Setting Content View
		
		setContentView(R.layout.activity_main);
		View linearLayout =  findViewById(R.id.info);

//TextView
		
        TextView valueTV = new TextView(this);
        valueTV.setText("Match The Following Fruits");	//""+dpHeight+"X"+dpWidth+" d"+density
        valueTV.setHeight(vspace);
        valueTV.setTextSize(vspace/4);
        valueTV.setGravity(Gravity.CENTER);
        
//Setting next button
        
        Button b = new Button(this);
        b.setText("next");
        b.setGravity(Gravity.CENTER);
        b.setPadding(vspace/10, vspace/10, vspace/10, vspace/10);
        
//Appending Child View
        ((LinearLayout) linearLayout).addView(valueTV);
        ((LinearLayout) linearLayout).addView(paintView);
        ((LinearLayout) linearLayout).addView(b);
//toast
	    
	    myFade = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tween2);
	    LayoutInflater inflater = getLayoutInflater();
    	layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_layout_root));
    	img_toast = (ImageView) layout.findViewById(R.id.timage);
    	
    	
    	toast = new Toast(MainActivity.this);
		toast.setGravity(Gravity.FILL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
//toast over

        
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				while(paintView.done(1)==1)
				{
					paintView.playerreset();
	
					Log.d("doneee","doneee");
					click=1;
					paintView.points.clear();
					paintView.points2.clear();
					paintView.fail[0]=0;
					paintView.fail[1]=0;
					paintView.fail[2]=0;
					
					if(image<(max_image-2))
					{
						for(int p=image;p<image+3;p++)
						{
							ques2.set(p-image,questions[p].qget());
							ques3.set(p-image,questions[p].aget());
						}
					}
					else if(image==(max_image-2))
					{
						for(int p=image;p<image+2;p++)
						{
							ques2.set(p-image,questions[p].qget());
							ques3.set(p-image,questions[p].aget());
						}

						ques2.set(2,questions[0].qget());
						ques3.set(2,questions[0].aget());
					}
					else if(image==(max_image-1)){
						img_toast.startAnimation(myFade);
			    		toast.show();
						finish();
					}

					image++;
					
					List<Integer> ques5=new ArrayList<Integer>(3);
			        ques5.addAll(ques3);
			        
			        Log.d("ques2b","ques3"+ques3.get(0));
		        	Log.d("ques2b","ques3"+ques3.get(1));
		        	Log.d("ques2b","ques3"+ques3.get(2));
		        	
					Collections.shuffle(ques3);
					
					int quesid=ques5.get(0);
		        	result[0]=ques3.indexOf(quesid);
		        	quesid=ques5.get(1);
		        	result[1]=ques3.indexOf(quesid);
		        	quesid=ques5.get(2);
		        	result[2]=ques3.indexOf(quesid);
		        	
		        	paintView.setres(result);
					paintView.setAdapter(ia);

					int i;
					for(i=0;i<n;i++)
			        {
			        		mThumbIds[2*i]=ques2.get(i);
			        		mThumbIds[2*i+1]=ques3.get(i);
			        }
					paintView.setThumbIds(mThumbIds);
					ia.notifyDataSetChanged();
				}
			}
		});
                
	}
	
	//ONSTOP ACTIVITY STARTS
			protected void onStop() 
			{
			    super.onStop();
			    paintView.playerstop();
			}
	//ONSTOP ACTIVITY ENDS

	//IMAGE ADAPTER STARTS
		public class ImageAdapter extends BaseAdapter {
			
			 // references to our images
		    
		    private int n = 3;
		    
		    private Context mContext;

		    public ImageAdapter(Context c) {
		        mContext = c;
		        int i;
		        Random rn = new Random();	        	
	        	
		        if(level_no==2)
		        {
			        questions[0]= new QAPair(R.drawable.apple ,R.drawable.apple);
			        questions[1]= new QAPair(R.drawable.banana ,R.drawable.banana);
			        questions[2]= new QAPair(R.drawable.grapes,R.drawable.grapes);
			        questions[3]= new QAPair(R.drawable.pomegranate,R.drawable.pomegranate);
			        questions[4]= new QAPair(R.drawable.guava,R.drawable.guava);
			        questions[5]= new QAPair(R.drawable.lychee,R.drawable.lychee);
			        questions[6]= new QAPair(R.drawable.melon,R.drawable.melon);
			        questions[7]= new QAPair(R.drawable.orange,R.drawable.orange);
			        questions[8]= new QAPair(R.drawable.papaya,R.drawable.papaya);
			        questions[9]= new QAPair(R.drawable.pineapple,R.drawable.pineapple);
			        questions[10]= new QAPair(R.drawable.sapodilla,R.drawable.sapodilla);
			        questions[11]= new QAPair(R.drawable.strawberry,R.drawable.strawberry);
			        questions[12]= new QAPair(R.drawable.custardapple,R.drawable.custardapple);
			        questions[13]= new QAPair(R.drawable.watermelon,R.drawable.watermelon);
		        }
		        else if(level_no==3)
		        {
		        	if(clang == 0)
		        	{
			        	questions[0]= new QAPair(R.drawable.apple_2,R.drawable.apple_2);
				        questions[1]= new QAPair(R.drawable.banana_2 ,R.drawable.banana_2);
				        questions[2]= new QAPair(R.drawable.grapes_2,R.drawable.grapes_2);
				        questions[3]= new QAPair(R.drawable.sapodilla_2,R.drawable.sapodilla_2);
				        questions[4]= new QAPair(R.drawable.guava_2,R.drawable.guava_2);
				        questions[5]= new QAPair(R.drawable.lychee_2,R.drawable.lychee_2);
				        questions[6]= new QAPair(R.drawable.melon_2,R.drawable.melon_2);
				        questions[7]= new QAPair(R.drawable.orange_2,R.drawable.orange_2);
				        questions[8]= new QAPair(R.drawable.papaya_2,R.drawable.papaya_2);
				        questions[9]= new QAPair(R.drawable.pineapple_2,R.drawable.pineapple_2);
				        questions[10]= new QAPair(R.drawable.pomegranate_2,R.drawable.pomegranate_2);
				        questions[11]= new QAPair(R.drawable.strawberry_2,R.drawable.strawberry_2);
				        questions[12]= new QAPair(R.drawable.custardapple_2,R.drawable.custardapple_2);
				        questions[13]= new QAPair(R.drawable.watermelon_2,R.drawable.watermelon_2);
		        	}
		        	else if(clang==1)
		        	{
		        		questions[0]= new QAPair(R.drawable.apple_1,R.drawable.apple_1);
				        questions[1]= new QAPair(R.drawable.banana_1 ,R.drawable.banana_1);
				        questions[2]= new QAPair(R.drawable.grapes_1,R.drawable.grapes_1);
				        questions[3]= new QAPair(R.drawable.sapodilla_1,R.drawable.sapodilla_1);
				        questions[4]= new QAPair(R.drawable.guava_1,R.drawable.guava_1);
				        questions[5]= new QAPair(R.drawable.lychee_1,R.drawable.lychee_1);
				        questions[6]= new QAPair(R.drawable.muskmelon_1,R.drawable.muskmelon_1);
				        questions[7]= new QAPair(R.drawable.orange_1,R.drawable.orange_1);
				        questions[8]= new QAPair(R.drawable.papaya_1,R.drawable.papaya_1);
				        questions[9]= new QAPair(R.drawable.pineapple_1,R.drawable.pineapple_1);
				        questions[10]= new QAPair(R.drawable.pomegranate_1,R.drawable.pomegranate_1);
				        questions[11]= new QAPair(R.drawable.strawberry_1,R.drawable.strawberry_1);
				        questions[12]= new QAPair(R.drawable.custardapple_1,R.drawable.custardapple_1);
				        questions[13]= new QAPair(R.drawable.watermelon_1,R.drawable.watermelon_1);
		        	}
		        	
		        }
		        
		        Collections.shuffle(Arrays.asList(questions));		        
		        
		        for(int p=0;p<=2;p++)
		        {
		        	ques2.add(questions[p].qget());
		        	ques3.add(questions[p].aget());
		        }
	        	
		        List<Integer> ques4=new ArrayList<Integer>(3);
		        ques4.addAll(ques3);

	        	Collections.shuffle(ques3, rn);
	        	int quesid=ques4.get(0);
	        	result[0]=ques3.indexOf(quesid);
	        	quesid=ques4.get(1);
	        	result[1]=ques3.indexOf(quesid);
	        	quesid=ques4.get(2);
	        	result[2]=ques3.indexOf(quesid);
	        		        	
	        	for(i=0;i<n;i++)
		        {
		        		mThumbIds[2*i]=ques2.get(i);
		        		mThumbIds[2*i+1]=ques3.get(i);
		        }
	        	
		    }

		    public int getCount() {
		        return mThumbIds.length;
		    }

		    public Object getItem(int position) {
		        return null;
		    }

		    public long getItemId(int position) {
		        return 0;
		    }

		 // create a new ImageView for each item referenced by the Adapter
			public View getView(int position, View convertView, ViewGroup parent) {
		        ImageView imageView;
		        if (convertView == null) {  // if it's not recycled, initialise some attributes
		        	 imageView = new ImageView(mContext);
		            if(position != 1 && position!=0)
		            {
		            	imageView.setLayoutParams(new GridView.LayoutParams(width, length));
		            	if(level_no==2) imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		            	else if(level_no==3) imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		            	imageView.setPadding(6, 6, 6, 6);
		            	imageView.setBackgroundColor(Color.LTGRAY);
		            	imageView.setId(position);
		            }
		            else
		            {
		            	imageView.setLayoutParams(new GridView.LayoutParams(width, length));
		            	if(level_no==2) imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		            	else if(level_no==3) imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		            	imageView.setPadding(6, 6, 6, 6);
		            	imageView.setBackgroundColor(Color.LTGRAY);
		            	imageView.setId(position);
		            }
		            
		        } else {
		            imageView = (ImageView) convertView;
		        }
		        Log.d("pos", "pos"+mThumbIds[position]);
		        imageView.setImageResource(mThumbIds[position]);
		       
		        return imageView;
		    }
		}
	//IMAGE ADAPTER ENDS
}
class QAPair {
    private int question;
    private int answer;

    public QAPair(int question, int answer) 
    {
        this.question = question;
        this.answer = answer;
    }
    public int qget(){
    	return question;
    }
    public int aget(){
    	return answer;
    }
}