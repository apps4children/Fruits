package com.example.paint2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Level6 extends Activity {
	
	MediaPlayer mp = new MediaPlayer();
	
	View viewmoving;
	
	int[][] point= new int[5][4];
	final int numberoffruits = 13;
	List<Integer> questions = new ArrayList<Integer>(numberoffruits);
	Integer[] image2= new Integer[numberoffruits];
	Integer[] ids=new Integer[5];
	float scale;
	int pointflag=0;
	
	ImageView blackImageView;
	Bitmap sample;
	int done=0;	
	int next_lev=1;
	ImageView img1;
	ImageView img2;
	ImageView img3;
	ImageView img4;
	ImageView img5;
	View linearLayout;
	Button b;
	
	ImageView img;
	Toast toast;
	View layout;
	Toast toast2;
	View layout2;
	Animation myFade;
	int width;
	
	LinearLayout.LayoutParams params;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    setContentView(R.layout.level6_new);
	    
	    Display display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics outMetrics = new DisplayMetrics ();
	    display.getMetrics(outMetrics);
	    
	    int dpHeight = (int)(outMetrics.heightPixels);
	    int dpWidth  = (int)(outMetrics.widthPixels);
	    
	    if(dpWidth<dpHeight)
	    {
	    	width= dpWidth/3;
	    }
	    else
	    {
	    	width = dpHeight/3; 
	    }
	    
	    RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(width, width);
	    RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(width, width);
	    RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(width, width);
	    RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(width, width);
	    		
	    ImageView imgp = (ImageView)findViewById(R.id.bask);
	    imgp.getLayoutParams().height=dpWidth;
	    imgp.getLayoutParams().width=dpWidth;
	    
	    
	    imgp = (ImageView)findViewById(R.id.myimagea1);
	    imgp.getLayoutParams().height=width;
	    imgp.getLayoutParams().width=width;
	    
	    imgp = (ImageView)findViewById(R.id.myimagea2);
	    lp2.addRule(RelativeLayout.ALIGN_RIGHT,R.id.myimagea1);
	    lp2.addRule(RelativeLayout.CENTER_VERTICAL);
	    lp2.setMargins(0, 0, 3*width/4, 0);
	    imgp.setLayoutParams(lp2);
	    //imgp.getLayoutParams().height=width;
	    //imgp.getLayoutParams().width=width;

	    imgp = (ImageView)findViewById(R.id.myimagea3);
	    lp3.setMargins(3*width/4, 0, 0, 0);
	    lp3.addRule(RelativeLayout.ALIGN_LEFT,R.id.myimagea1);
	    lp3.addRule(RelativeLayout.CENTER_VERTICAL);
	    imgp.setLayoutParams(lp3);
	    //imgp.getLayoutParams().height=width;
	    //imgp.getLayoutParams().width=width;
	    
	    imgp = (ImageView)findViewById(R.id.myimagea4);
	    lp4.addRule(RelativeLayout.ALIGN_LEFT,R.id.myimagea1);
	    lp4.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.myimagea1);
	    lp4.setMargins(width/3, 0, 0,3*width/4 );
	    imgp.setLayoutParams(lp4);
	    
	    //lp.setMargins(left, top, right, bottom)
	    
	    //imgp.getLayoutParams().height=width;
	    //imgp.getLayoutParams().width=width;
	    
	    imgp = (ImageView)findViewById(R.id.myimagea5);
	    lp5.addRule(RelativeLayout.ALIGN_RIGHT,R.id.myimagea1);
	    lp5.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.myimagea1);
	    lp5.setMargins(0, 0, 2*width/3,width/2 );
	    imgp.setLayoutParams(lp5);
	    //imgp.getLayoutParams().height=width;
	    //imgp.getLayoutParams().width=width;
//toast
	    
	    myFade = AnimationUtils.loadAnimation(Level6.this, R.anim.tween2);
	    LayoutInflater inflater = getLayoutInflater();
    	layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_layout_root));
    	img = (ImageView) layout.findViewById(R.id.timage);
    	
    	
    	toast = new Toast(Level6.this);
		toast.setGravity(Gravity.FILL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
//toast over
//toast2
		
    	layout2 = inflater.inflate(R.layout.toast_layout_2,(ViewGroup) findViewById(R.id.toast_layout_root_2));
    	
    	toast2 = new Toast(Level6.this);
		toast2.setGravity(Gravity.FILL, 0, 0);
		toast2.setDuration(Toast.LENGTH_LONG);
		toast2.setView(layout2);
//toast2 over
		
	    scale = getBaseContext().getResources().getDisplayMetrics().density;
	    Log.d("enter","enter 2");
	    
	    List<Integer> shufindex = new ArrayList<Integer>(5);
	    
	    Log.d("enter","enter 1");
	    
	    /*Log.d("x","point fix x: "+point[0][0]);
	    Log.d("y","point fix y: "+point[0][1]);
	    Log.d("x","point fix x: "+point[1][0]);
	    Log.d("y","point fix y: "+point[1][1]);
	    Log.d("x","point fix x: "+point[2][0]);
	    Log.d("y","point fix y: "+point[2][1]);*/
	    questions.add(R.drawable.apple_t);
        questions.add(R.drawable.banana_t);                
        questions.add(R.drawable.guava_t);
        questions.add(R.drawable.lychee_t);
        questions.add(R.drawable.melon_t);
        questions.add(R.drawable.orange_t);
        questions.add(R.drawable.papaya_t);
        questions.add(R.drawable.pineapple_t);
        questions.add(R.drawable.greengrapes_t);
        questions.add(R.drawable.strawberry_t);
        questions.add(R.drawable.custardapple_t);
        questions.add(R.drawable.watermelon_t);
        questions.add(R.drawable.pomegranate_t);
        questions.add(R.drawable.sapodilla_t);
        Log.d("","hey");	    
	    
        Collections.shuffle(questions);
        
	    linearLayout =  findViewById(R.id.info);	    	   
	    img1=new ImageView(this);
		img2=new ImageView(this);
		img3=new ImageView(this);
		img4=new ImageView(this);
		img5=new ImageView(this);

		img1.setImageResource(questions.get(0));
		img2.setImageResource(questions.get(1));
		img3.setImageResource(questions.get(2));
		img4.setImageResource(questions.get(3));
		img5.setImageResource(questions.get(4));
			    
	    
	    blackImageView = (ImageView) findViewById(R.id.myimagea1);
        sample = BitmapFactory.decodeResource(getResources(),questions.get(0));        
        
        sample=convertColorIntoBlackAndWhiteImage(sample);
        blackImageView.setImageBitmap(sample);
        
        blackImageView = (ImageView) findViewById(R.id.myimagea2);
        sample = BitmapFactory.decodeResource(getResources(),questions.get(1));        
        sample=convertColorIntoBlackAndWhiteImage(sample);
        blackImageView.setImageBitmap(sample);
        blackImageView = (ImageView) findViewById(R.id.myimagea3);
        sample = BitmapFactory.decodeResource(getResources(),questions.get(2));        
        sample=convertColorIntoBlackAndWhiteImage(sample);
        blackImageView.setImageBitmap(sample);
        blackImageView = (ImageView) findViewById(R.id.myimagea4);
        sample = BitmapFactory.decodeResource(getResources(),questions.get(3));        
        sample=convertColorIntoBlackAndWhiteImage(sample);
        blackImageView.setImageBitmap(sample);
        blackImageView = (ImageView) findViewById(R.id.myimagea5);
        sample = BitmapFactory.decodeResource(getResources(),questions.get(4));        
        sample=convertColorIntoBlackAndWhiteImage(sample);
        blackImageView.setImageBitmap(sample);
        params = new LinearLayout.LayoutParams(width,width);
    	img1.setLayoutParams(params);
    	img2.setLayoutParams(params);
    	img3.setLayoutParams(params);
    	img4.setLayoutParams(params);
    	img5.setLayoutParams(params);
    	img1.setId(0);
    	img2.setId(1);
    	img3.setId(2);
    	img4.setId(3);
    	img5.setId(4);
    	ids[0]=R.id.myimagea1;
    	ids[1]=R.id.myimagea2;
    	ids[2]=R.id.myimagea3;
    	ids[3]=R.id.myimagea4;
    	ids[4]=R.id.myimagea5;
    	
    	shufindex.add(0);
    	shufindex.add(1);
    	shufindex.add(2);
    	shufindex.add(3);
    	shufindex.add(4);
    	Collections.shuffle(shufindex);
    	int i,j;
    	for(i=0;i<5;i++)
    	{
    		j=shufindex.get(i);
    		if(j==0)
    			((LinearLayout) linearLayout).addView(img1);
    		if(j==1)
    			((LinearLayout) linearLayout).addView(img2);
    		if(j==2)
    			((LinearLayout) linearLayout).addView(img3);
    		if(j==3)
    			((LinearLayout) linearLayout).addView(img4);
    		if(j==4)
    			((LinearLayout) linearLayout).addView(img5);
    	}
    	
    	findViewById(R.id.top).setOnDragListener(new MyDragListener());
    	img1.setOnTouchListener(new MyTouchListener());
    	img2.setOnTouchListener(new MyTouchListener());
    	img3.setOnTouchListener(new MyTouchListener());
    	img4.setOnTouchListener(new MyTouchListener());
    	img5.setOnTouchListener(new MyTouchListener());  	
    	
	  }
public void but_clicked(View v)
{			
	if(done==5)
	{
		done=0;
		Log.d("","check b inv");
		if (mp != null) mp.reset();
		
		next_lev++;
		if(next_lev==6)
		{
			img.startAnimation(myFade);
    		toast.show();
    		
    		Handler finishact = new Handler() {
    			@Override
                public void handleMessage(Message msg) {
    					finish();
                        super.handleMessage(msg);
                }
    		};
    		Message msg = new Message();
    		Log.d("toast","taost check :"+toast.getDuration());
    		finishact.sendMessageDelayed(msg, toast.getDuration()*3000);
    		
			
		}
		else
		{
			
			Collections.shuffle(questions);
			
			toast2.cancel();
			
			img1.setImageResource(questions.get(0));
			img2.setImageResource(questions.get(1));
			img3.setImageResource(questions.get(2));
			img4.setImageResource(questions.get(3));
			img5.setImageResource(questions.get(4));
			img1.setVisibility(View.VISIBLE);
			img2.setVisibility(View.VISIBLE);
			img3.setVisibility(View.VISIBLE);
			img4.setVisibility(View.VISIBLE);
			img5.setVisibility(View.VISIBLE);
	
			img1.setLayoutParams(params);
	    	img2.setLayoutParams(params);
	    	img3.setLayoutParams(params);
	    	img4.setLayoutParams(params);
	    	img5.setLayoutParams(params);
	Log.d("","check img set");
	
			blackImageView = (ImageView) findViewById(R.id.myimagea1);
			//blackImageView.setImageResource)
	        sample = BitmapFactory.decodeResource(getResources(),
	                questions.get(0));        
	                sample=convertColorIntoBlackAndWhiteImage(sample);
	                blackImageView.setImageBitmap(sample);
	        
	        blackImageView = (ImageView) findViewById(R.id.myimagea2);
	        sample = BitmapFactory.decodeResource(getResources(),
	                questions.get(1));        
	        sample=convertColorIntoBlackAndWhiteImage(sample);
	        blackImageView.setImageBitmap(sample);
	        blackImageView = (ImageView) findViewById(R.id.myimagea3);
	        sample = BitmapFactory.decodeResource(getResources(),
	                questions.get(2));        
	        sample=convertColorIntoBlackAndWhiteImage(sample);
	        blackImageView.setImageBitmap(sample);
	        blackImageView = (ImageView) findViewById(R.id.myimagea4);
	        sample = BitmapFactory.decodeResource(getResources(),
	                questions.get(3));        
	        sample=convertColorIntoBlackAndWhiteImage(sample);
	        blackImageView.setImageBitmap(sample);
	        blackImageView = (ImageView) findViewById(R.id.myimagea5);
	        sample = BitmapFactory.decodeResource(getResources(),
	                questions.get(4));        
	        sample=convertColorIntoBlackAndWhiteImage(sample);
	        blackImageView.setImageBitmap(sample);
	        Log.d("","check black img set");
	        List<Integer> shufindex = new ArrayList<Integer>(5);	        
	        		    	
	    	shufindex.add(0);
	    	shufindex.add(1);
	    	shufindex.add(2);
	    	shufindex.add(3);
	    	shufindex.add(4);
	    	Collections.shuffle(shufindex);
	    	Log.d("","check shuf");
	    	int i,j;
	    	linearLayout =  findViewById(R.id.info);
	    	for(i=0;i<5;i++)
	    	{
	    		j=shufindex.get(i);
	    		if(j==0)
	    			{
	    				((LinearLayout) linearLayout).addView(img1);
	    		
	    			}
	    		if(j==1)
	    			{
	    				((LinearLayout) linearLayout).addView(img2);
	    			}
	    		if(j==2)
	    			{
	    				((LinearLayout) linearLayout).addView(img3);
	    			}
	    		if(j==3)
	    			{
	    				((LinearLayout) linearLayout).addView(img4);
	    			}
	    		if(j==4)
	    			{
	    				((LinearLayout) linearLayout).addView(img5);
	    			}
	    	}
	    	findViewById(R.id.top).setOnDragListener(new MyDragListener());
	    	img1.setOnTouchListener(new MyTouchListener());
	    	img2.setOnTouchListener(new MyTouchListener());
	    	img3.setOnTouchListener(new MyTouchListener());
	    	img4.setOnTouchListener(new MyTouchListener());
	    	img5.setOnTouchListener(new MyTouchListener());
	    	Log.d("","check add imgg");
		}
	}
}    	

//ONSTOP ACTIVITY STARTS
protected void onStop() {
    super.onStop();
    if (mp != null) mp.release();
}
//ONSTOP ACTIVITY ENDS

	 
	  private Bitmap convertColorIntoBlackAndWhiteImage(Bitmap orginalBitmap) {
	        ColorMatrix colorMatrix = new ColorMatrix();
	        colorMatrix.setSaturation(0);

	        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
	                colorMatrix);

	        Bitmap blackAndWhiteBitmap = orginalBitmap.copy(
	                Bitmap.Config.ARGB_8888, true);

	        Paint paint = new Paint();
	        paint.setColorFilter(colorMatrixFilter);

	        Canvas canvas = new Canvas(blackAndWhiteBitmap);
	        canvas.drawBitmap(blackAndWhiteBitmap, 0, 0, paint);

	        return blackAndWhiteBitmap;
	    }

	public final class MyTouchListener implements OnTouchListener 
	{
		public boolean onTouch(View view, MotionEvent motionEvent) 
		{
	      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) 
	      {
	    	if(pointflag==0)
	    	{
	    		pointflag=1;
	    	/////////////////////////////////////////////////////////////
		    	ImageView imgpre = (ImageView) findViewById(R.id.myimagea1);
		  	    int top = imgpre.getTop();
		  	    int bot = imgpre.getBottom();
		  	    int rig = imgpre.getRight();
		  	    int lef = imgpre.getLeft();
		  	    
		  	    point[0][0]=top;
		  	    point[0][1]=bot;
		  	    point[0][2]=rig;
		  	    point[0][3]=lef;
		  	    
		  	    imgpre = (ImageView) findViewById(R.id.myimagea2);
		  	    top = imgpre.getTop();
		  	    bot = imgpre.getBottom();
		  	    rig = imgpre.getRight();
		  	    lef = imgpre.getLeft();
		  	    
		  	    point[1][0]=top;
		  	    point[1][1]=bot;
		  	    point[1][2]=rig;
		  	    point[1][3]=lef;
		  	    
		  	    imgpre = (ImageView) findViewById(R.id.myimagea3);
		  	    top = imgpre.getTop();
		  	    bot = imgpre.getBottom();
		  	    rig = imgpre.getRight();
		  	    lef = imgpre.getLeft();
		  	    
		  	    point[2][0]=top;
		  	    point[2][1]=bot;
		  	    point[2][2]=rig;
		  	    point[2][3]=lef;
		  	    
		  	    imgpre = (ImageView) findViewById(R.id.myimagea4);
		  	    top = imgpre.getTop();
		  	    bot = imgpre.getBottom();
		  	    rig = imgpre.getRight();
		  	    lef = imgpre.getLeft();
		  	    
		  	    point[3][0]=top;
		  	    point[3][1]=bot;
		  	    point[3][2]=rig;
		  	    point[3][3]=lef;
		  	    
		  	    imgpre = (ImageView) findViewById(R.id.myimagea5);
		  	    top = imgpre.getTop();
		  	    bot = imgpre.getBottom();
		  	    rig = imgpre.getRight();
		  	    lef = imgpre.getLeft();
		  	    
		  	    point[4][0]=top;
		  	    point[4][1]=bot;
		  	    point[4][2]=rig;
		  	    point[4][3]=lef;  
		  	    
		  	    Log.d("x","point fix x0: "+point[0][0]);
			    Log.d("y","point fix y0: "+point[0][1]);
			    Log.d("x","point fix x1: "+point[1][0]);
			    Log.d("y","point fix y1: "+point[1][1]);
			    Log.d("x","point fix x2: "+point[2][0]);
			    Log.d("y","point fix y2: "+point[2][1]);
	    	////////////////////////////////////////////////////////////
	    	}
	        
	  	    ClipData data = ClipData.newPlainText("", "");
	        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
	        view.startDrag(data, shadowBuilder, view, 0);
	        
	        //view.setVisibility(View.INVISIBLE);
	        viewmoving=view;	        
	        Log.d("touching","touching");
	        return true;
	      } 
	      else 
	      {
	        return false;
	      }
	    }
	  }
	class MyDragListener implements OnDragListener 
	{
	    Drawable enterShape = getResources().getDrawable(R.layout.shape_droptarget);
	    Drawable normalShape = getResources().getDrawable(R.layout.shape);

		@Override
	    public boolean onDrag(View v, DragEvent event) 
		{
	      Log.d("","dragg");
	      Log.d("x","point x: "+event.getX());
    	  Log.d("y","point y: "+event.getX());
	      switch (event.getAction()) 
	      {
	      case DragEvent.ACTION_DRAG_STARTED:
	        // Do nothing	    	  
	        break;
	      case DragEvent.ACTION_DROP:
	     	  
	    	  float x1=event.getX();
	    	  float y1=event.getY();
	    	  int id=viewmoving.getId();
	    	  Log.d("","enter x1 y1 "+x1+" "+y1);
	    	  Log.d("","id : "+id);
	    	  if(x1>=(point[id][3]-10) && x1<=(point[id][2]+10) && y1>=(point[id][0]-10) && y1<=(point[id][1]+10))
	    	  {
	    		  	Log.d("enter cum","enter cum");
	    		  	viewmoving.setVisibility(View.INVISIBLE);
	    		  	
	    		  	blackImageView = (ImageView) findViewById(ids[id]);	    		  	
	    		  	blackImageView.setImageResource(questions.get(id));
  	
	    		  	done++;
	    		  	
	    		  	if(done==5) appreciate();
	    		  	else playmusic(1);
	    		  	
	    		  	Log.d("","enter end");
	    		  		    		  	
			        View view = (View) event.getLocalState();
			        ViewGroup owner = (ViewGroup) view.getParent();
			        owner.removeView(view);
			  }
	    	  else playmusic(0);
	    	  
	        break;
	      default:
	        break;
	      }
	      return true;
	    }
		private void appreciate() 
		{
    		toast2.show();
    		playmusic(2);
		}
		private void playmusic(int i) 
		{
			mp.reset();
			if(i==0)
			{
				mp = MediaPlayer.create(Level6.this, R.raw.err);
			}
			else if(i==1)
			{
				mp = MediaPlayer.create(Level6.this, R.raw.succ1);
			}
			else if(i==2)
			{
				mp = MediaPlayer.create(Level6.this, R.raw.succ2);
			}
			mp.start();
		}
	  }
}