package org.haxe.extension.youtubeplayer;


import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.util.Log;

import org.haxe.extension.Extension;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerFragment;


public class YoutubePlayer extends Extension /*implements YouTubePlayer.OnFullscreenListener*/{

	private static Intent intent;

	public static void initialize(String developerKey){
		YoutubePlayerActivity.setDevKey(developerKey);
		intent = new Intent(Extension.mainActivity, YoutubePlayerActivity.class);

	}
	
	
	public static void loadVideo(final String videoID, final boolean forceFullscreen) {
		Log.i("youtubeplayer", "load video called");
		Extension.mainActivity.runOnUiThread(new Runnable() {
			public void run() {
				YoutubePlayerActivity.videoID = videoID;
				YoutubePlayerActivity.fullscreenForced = forceFullscreen;
				Intent intent = new Intent(Extension.mainActivity, YoutubePlayerActivity.class);
				Extension.mainActivity.startActivityForResult(intent, 0);
			}
		});
		
	}
	// "nCgQDjiotG0"
	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		Log.i("youtubeplayer", "onActivityResult called");
		return true;
		
	}
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
	}
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {	
	}
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
	}
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
	}
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
	}
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {	
	}
	
	
	
}