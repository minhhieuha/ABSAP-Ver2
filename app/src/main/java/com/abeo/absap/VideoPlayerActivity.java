package com.abeo.absap;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_player);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		 VideoView video = (VideoView) findViewById(R.id.video);
		 MediaController mediaController = new MediaController(this);
			mediaController.setAnchorView(video);
			video.setMediaController(mediaController);
		   Uri video1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hs);
		   video.setVideoURI(video1);
		   video.setOnPreparedListener(new 
					MediaPlayer.OnPreparedListener()  {
	            @Override
	            public void onPrepared(MediaPlayer mp) {                         
	            }
	        });	
		   video.start();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
			return super.onOptionsItemSelected(item);
	}
}
