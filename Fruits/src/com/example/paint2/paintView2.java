package com.example.paint2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class paintView2 extends LinearLayout implements OnTouchListener 
{
	private static final String TAG = "PaintView2";
	
	MediaPlayer mp = new MediaPlayer();
	
	static int n =3;
	static int width;
	static int length;
	static int cwidth;
	static int hspace;
	static int vspace = 50;
	static float[] xarray = new float[4];
	static float[] yarray = new float[6];
		
	List<Points> points = new ArrayList<Points>();
	List<Points> points2 = new ArrayList<Points>();
	Paint paint = new Paint();
	Points pfirst = new Points();
	Points psec = new Points();
	int flagpoint = 0;
	
	static int[] result = new int[2];
	static int fail;	
	Context scontext;
	ImageView img1=(ImageView) findViewById(0);//for hint
	ImageView img2=(ImageView) findViewById(0);
	ImageView img3=(ImageView) findViewById(0);//for lock
	ImageView img4=(ImageView) findViewById(0);
	int back=-1;
	
	private String user_name;
	private Integer level_no;
	
	private Integer[] paintThumbIds = new Integer[4];
	private Integer clang;
	private String date_time;
	
	/////////////////////////////////////////////////////////////////////////////
	Toast toast2_anim;											/////////////
	View layout2_anim;											/////////////
	/////////////////////////////////////////////////////////////////////////////
	
	Animation myFade;
	
	
	public paintView2(Context context,int cw, int vs, int l, int hs,int w,String uname,int level,int lang) {
		super(context);
		scontext=context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//toast2
		LayoutInflater inflater;																						////////////		
		inflater = LayoutInflater.from(scontext);																		////////////
		layout2_anim = inflater.inflate(R.layout.toast_layout_2,(ViewGroup) findViewById(R.id.toast_layout_root_2));	////////////
		
		toast2_anim = new Toast(scontext);																				////////////
		toast2_anim.setGravity(Gravity.FILL, 0, 0);																		////////////
		toast2_anim.setDuration(Toast.LENGTH_SHORT);																	////////////
		toast2_anim.setView(layout2_anim);																				////////////				
//toast2 over
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		myFade = AnimationUtils.loadAnimation(scontext, R.anim.tween);
		user_name = uname;
		level_no = level;
		clang=lang;
		paintThumbIds[0]=0;paintThumbIds[1]=1;paintThumbIds[2]=2;paintThumbIds[3]=3;
		width = w;
		length = l;
		cwidth = cw;
		hspace = hs;
		vspace = vs;
		xarray[0] = (cw - w)/2;
		xarray[1] = (cw + w)/2;
		xarray[2] = (3*cw - w)/2;
		xarray[3] =	(3*cw + w)/2; 
		yarray[0] = vs;
		yarray[1] = 4*vs;
		yarray[2] = 5*vs;
		yarray[3] =	8*vs;
		yarray[4] = 9*vs;
		yarray[5] = 12*vs;
		points.clear();
		points2.clear();
		
		result[0]=0;
		result[1]=0;
		
		fail=0;
		Log.d("array","xarray->"+xarray[0]+" "+xarray[1]+" y->"+yarray[0]+" "+yarray[1]);
		
		this.setOnTouchListener(this);
		
		paint.setColor(Color.BLACK);
		paint.setAlpha(100);
		paint.setStrokeCap(Cap.ROUND);
		paint.setStrokeWidth(10);
		paint.setAntiAlias(true);
		
		pfirst.x = -1;pfirst.x = -1;
		psec.x = -1;psec.y = -1;
		
		Calendar cal=Calendar.getInstance();
		date_time=String.format("%1$te %1$tB %1$tY,%1$tI:%1$tM:%1$tS %1$Tp",cal);
		//"23 January 2010,12:30:15 pm"
	}
	
	@Override
	public void onDraw(Canvas canvas) 
	{
		int c = points.size();
		for(int i=0;i<c-1;i+=2)
		{
			Points point1=points.get(i);
			Points point2 = points.get(i+1);
			canvas.drawLine(point1.x,point1.y,point2.x,point2.y, paint);
		}
		
		int c2 = points2.size();
		for(int i=0;i<c2-1;i+=1)
		{
			Points point1=points2.get(i);
			Points point2 = points2.get(i+1);
			canvas.drawLine(point1.x,point1.y,point2.x,point2.y, paint);
		}
	}
	
	public boolean onTouch(View view, MotionEvent event) 
	{
		Points point = new Points();
		Points add_point = new Points();
		int eventaction = event.getAction();
		switch (eventaction)
		{
			case MotionEvent.ACTION_DOWN: 
	            // finger touches the screen
				
				point.x = event.getX();
				point.y = event.getY();
				if(back>=0)
				{
					back=-1;
					img1.clearAnimation();
					img2.clearAnimation();
				}
				if((point.x >= xarray[0] && point.x <=xarray[1]))
				{
					Log.d("hello","bye");											
					if((point.y>=yarray[2] && point.y<=yarray[3] && result[1]==0))
					{
						add_point.x=xarray[1]-10;
						add_point.y=(yarray[3]+yarray[2])/2;
						points.add(add_point);
						flagpoint = 1;						
					}					
					else
						flagpoint = 0;				
				}
				else flagpoint = 0;
				
	            break;
	
	        case MotionEvent.ACTION_MOVE:
	            // finger moves on the screen
	        	point.x = event.getX();
	        	point.y = event.getY();
	        	points2.add(point);
	            break;
	
	        case MotionEvent.ACTION_UP:   
	            // finger leaves the screen
	        	points2.clear();
	        	if(flagpoint == 1)
	        	{
	        		Log.d("hello2","bye2");
	        		point.x = event.getX();
		        	point.y = event.getY();
		        	if((point.x >= xarray[2] && point.x <=xarray[3]))
		        	{
		        		if(point.y>=yarray[0] && point.y<=yarray[1])
						{	
		        			img3= (ImageView) findViewById(0);
		        			img4= (ImageView) findViewById(1);
		        			
		        			if(result[0]==0)
		        			{
				        		img3.setBackgroundColor(Color.GREEN);		        		
				        		img4.setBackgroundColor(Color.GREEN);
				        		Log.d("","first col ");
		        				add_point.x=xarray[2]+10;
								add_point.y=(yarray[0]+yarray[1])/2;
		        				points.add(add_point);
		        				Log.d("","row 1");
		        				
		        				////////////////////////////////////////
		        				if(done(0)==1)				////////////
		        				{							////////////
		        					toast2_anim.show();		////////////
		        					playmusic(2);
		        				}							////////////
		        				////////////////////////////////////////
		        				else playmusic(1);
		      
		        				result[1]=1;
		        			}
		        			else
		        			{
		        				fail++;
		        				//////////////////////////////////////////
		        				playmusic(0);				//////////////
		        				//////////////////////////////////////////
		        				
		        				points.remove(points.size()-1);
		        				Log.d("mistake","he has made a mistake today.");
		       // retrieve names 
		        				Resources rr = img3.getResources();    		
					        	int idh = img3.getId();
					        	
					        	String iname = rr.getResourceEntryName(paintThumbIds[idh]);
					       
					        	idh = img4.getId();
					        	
					        	String iname2 = rr.getResourceEntryName(paintThumbIds[idh]);
					       
				// entry in database        	
					        	if(level_no == 5){
					        		iname = iname.substring(0,iname.length() - 2);
					        	}
					        	else if(level_no == 6){
					        		iname2 = iname2.substring(0,iname2.length() - 2);
					        	}
		        				String name = user_name;
		        				String level = level_no.toString();
		        				String mistake = "matched "+ iname + " to " + iname2;
		        				String cclang = clang.toString();
		        				Mistakes entry = new Mistakes(scontext);
		        				entry.open();
		        				entry.createEntry(name, level, mistake,cclang,date_time);
		        				entry.close();
		        			}
						}
						else if(point.y>=yarray[2] && point.y<=yarray[3])
						{
							img3= (ImageView) findViewById(0);
							img4= (ImageView) findViewById(2);
							if(result[0]==1)
		        			{
				        		img3.setBackgroundColor(Color.GREEN);		        		
				        		img4.setBackgroundColor(Color.GREEN);
				        	
								add_point.x=xarray[2]+10;
								add_point.y=(yarray[3]+yarray[2])/2;
		        				points.add(add_point);
		        				Log.d("","row 2");
		        				result[1]=1;
		        				/////////////////////////////////////////
		        				if(done(0)==1)				/////////////
		        				{							/////////////
		        					toast2_anim.show();		/////////////
		        					playmusic(2);
		      					}							/////////////
		        				/////////////////////////////////////////
		        				else playmusic(1);
		        				
		        			}
		        			else
		        			{
		        				fail++;
		        				//////////////////////////////////////////
		        				playmusic(0);				//////////////
		        				//////////////////////////////////////////

		        				points.remove(points.size()-1);
		        // retrieve names 
		        				Resources rr = img3.getResources();    		
					        	int idh = img3.getId();
					        	
					        	String iname = rr.getResourceEntryName(paintThumbIds[idh]);
					        	idh = img4.getId();
					        	
					        	String iname2 = rr.getResourceEntryName(paintThumbIds[idh]);

				// entry in database
					        	if(level_no == 5){
					        		iname = iname.substring(0,iname.length() - 2);
					        	}
					        	else if(level_no == 6){
					        		iname2 = iname2.substring(0,iname2.length() - 2);
					        	}
		        				String name = user_name;
		        				String level = level_no.toString();
		        				String mistake = "matched "+ iname + " to " + iname2;
		        				String cclang = clang.toString();
		        				Mistakes entry = new Mistakes(scontext);
		        				entry.open();
		        				entry.createEntry(name, level, mistake,cclang,date_time);
		        				entry.close();
		        			}
						}
						else if(point.y>=yarray[4] && point.y<=yarray[5]) 
						{
							img3= (ImageView) findViewById(0);
							img4= (ImageView) findViewById(3);
							if(result[0]==2)
		        			{
								
				        		img3.setBackgroundColor(Color.GREEN);		        		
				        		
				        		img4.setBackgroundColor(Color.GREEN);
				        	
								add_point.x=xarray[2]+10;
								add_point.y=(yarray[4]+yarray[5])/2;
								Log.d("","row 3");
		        				points.add(add_point);
		        				result[1]=1;
		        				/////////////////////////////////////////
		        				if(done(0)==1)				/////////////
		        				{							/////////////
		        					toast2_anim.show();		/////////////
		        					playmusic(2);
		      					}							/////////////
		        				/////////////////////////////////////////
		        				else playmusic(1);
		        			}
		        			else
		        			{
		        				fail++;
		        				//////////////////////////////////////////
		        				playmusic(0);				//////////////
		        				//////////////////////////////////////////
		        				
		        				points.remove(points.size()-1);
		         // retrieve names 
		        				Resources rr = img3.getResources();    		
					        	int idh = img3.getId();
					        	
					        	String iname = rr.getResourceEntryName(paintThumbIds[idh]);
					        		
					        	idh = img4.getId();
					        	
					        	String iname2 = rr.getResourceEntryName(paintThumbIds[idh]);
					        	
				// entry in database
					        	if(level_no == 5){
					        		iname = iname.substring(0,iname.length() - 2);
					        	}
					        	else if(level_no == 6){
					        		iname2 = iname2.substring(0,iname2.length() - 2);
					        	}
		        				String name = user_name;
		        				String level = level_no.toString();
		        				String mistake = "matched "+ iname + " to " + iname2;
		        				Mistakes entry = new Mistakes(scontext);
		        				String cclang = clang.toString();
		        				entry.open();
		        				entry.createEntry(name, level, mistake,cclang,date_time);
		        				entry.close();

		        			}							
						}
						else
						{
							points.remove(points.size()-1);						
						}
		        		Log.d("res match ","res match "+result[0]);
		        	}
		        	else points.remove(points.size()-1);
		        	if(fail==3)
		        	{
		        		img1= (ImageView) findViewById(0);
		        		img1.setBackgroundColor(Color.BLUE);		        		
		        		img2= (ImageView) findViewById(result[0]+1);
		        		img2.setBackgroundColor(Color.BLUE);
		        		back=1;
		        		img1.startAnimation(myFade);
		        		img2.startAnimation(myFade);
		        		fail=0;
		        	}		        	
	        	}
	        	break;
		}
		//pfirst.x=pfirst.y=psec.x=psec.y=0;
		invalidate();
		Log.d(TAG, "point: " + point);
		return true;
	}

	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	private void playmusic(int i) {
	// i==0	error
	// i==1 success for a single fruit
	// i==2 success for all three fruits
	mp.reset();
	if(i==0)
	{
	mp = MediaPlayer.create(scontext, R.raw.err);
	}
	else if(i==1)
	{
	mp = MediaPlayer.create(scontext, R.raw.succ1);
	}
	else if(i==2)
	{
	mp = MediaPlayer.create(scontext, R.raw.succ2);
	}
	mp.start();
	}
	
	public void playerstop() {
	// TODO Auto-generated method stub
	if (mp != null) mp.release();
	
	}
	public void playerreset() {
		// TODO Auto-generated method stub
		if (mp != null) mp.reset();
		
		}
	
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////

	public int done(int i)
	{
		if(result[1]==1)
    	{
			Log.d("donne2","doneee2");
    		//Start again
			if(i==1)
			{
				result[1]=0;
				fail=0;
				img3.setBackgroundColor(Color.LTGRAY);
				img4.setBackgroundColor(Color.LTGRAY);
			}
			return 1;
    	}
		else return 0;
	}
	public void setres(int result2) {
		result[0]=result2;		
	}

	public void setThumbIds(Integer[] mThumbIds) {
		// TODO Auto-generated method stub
		paintThumbIds = mThumbIds;
	}
}

class Points {
	float x, y;
	
	@Override
	public String toString() {
		return x + ", " + y;
	}
}