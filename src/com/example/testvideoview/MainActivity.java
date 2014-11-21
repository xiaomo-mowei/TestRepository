package com.example.testvideoview;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnClickListener {

	private VideoView videoView;
	private Button startButton;
	private ProgressBar progressBar;
	private static final String URL = "http://flv.bn.netease.com/videolib3/1411/20/ybhtL6466/SD/ybhtL6466-mobile.mp4";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		videoView = (VideoView) findViewById(R.id.videoView);
		videoView.setOnClickListener(this);
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(this);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		
		
		
		
		
		
		
		
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.startButton) {
			playOnline();
		}
		else {
			if (videoView.isPlaying()) {
				videoView.pause();
			}
			else {
				videoView.start();
			}
		}
	}
	
	private void playLocal() {
		videoView.setVideoPath("");
	}

	private void playOnline() {
		progressBar.setVisibility(View.VISIBLE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
        videoView.setVideoURI(Uri.parse(URL));
        videoView.requestFocus();              
        videoView.setOnPreparedListener(new OnPreparedListener() {

             public void onPrepared(MediaPlayer mp) {                  
            	 	progressBar.setVisibility(View.GONE);
                 videoView.start();
             }
         });
        videoView.setOnErrorListener(new OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				progressBar.setVisibility(View.GONE);
				Log.e("onError", "onError");
				return false;
			}
		});
	}

}
