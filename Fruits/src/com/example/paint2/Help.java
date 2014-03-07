package com.example.paint2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Help extends Activity{

	VideoView videoView ;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        videoView =(VideoView)findViewById(R.id.videoView1);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);        
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.helpvideo);        
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);        
        videoView.requestFocus();
        videoView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	View placeholder = (View) findViewById(R.id.placeholder);
                placeholder.setVisibility(View.GONE);
                videoView.start();
                return false;
            }
        });
    }
	
	@Override
	protected void onResume() {
		videoView.resume();
	    super.onResume();
	}

	@Override
	protected void onPause() {
		videoView.suspend();
	    super.onPause();
	}

	@Override
	protected void onDestroy() {
		videoView.stopPlayback();
	    super.onDestroy();
	}
}