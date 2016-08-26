package org.haxe.extension.youtubeplayer;


import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerFragment;


public class YoutubePlayerActivity extends Activity implements YouTubePlayer.OnFullscreenListener{

	private static String DEVELOPER_KEY = "";
	private static boolean initialized = false;
	private static YouTubePlayerFragment youTubePlayerFragment;
	private static YouTubePlayer player;
	private static boolean fullscreen = false;
	public static boolean fullscreenForced = false;
	public static String videoID;
	
	public static void setDevKey(String developerKey){
		DEVELOPER_KEY = developerKey;
	}
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment);
		YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
		youTubePlayerFragment.initialize(DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener(){
			@Override
			public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
				Log.i("youtubeplayer", "youtubePlayer inited successfully");
				YoutubePlayerActivity.youTubePlayerFragment = (YouTubePlayerFragment) provider;
				YoutubePlayerActivity.player = player;
				YoutubePlayerActivity.player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
				YoutubePlayerActivity.player.setOnFullscreenListener(new YoutubePlayerActivity() );
				YoutubePlayerActivity.initialized = true;
				if(fullscreenForced) {
					player.setFullscreen(true);
					player.setShowFullscreenButton(false);
				} else {
					player.setFullscreen(false);
					fullscreen = false;
					player.setShowFullscreenButton(true);
				}
				if(!wasRestored) player.loadVideo(videoID);
				else player.play();
			}

			@Override
			public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
				Log.i("youtubeplayer", "Initialization failure!");
				YoutubePlayerActivity.initialized = false;
				YoutubePlayerActivity.youTubePlayerFragment = null;
				YoutubePlayerActivity.player = null;
				// setResult(Result.OK);
				finish();
			}
		});	
	}
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		Log.i("youtubeplayer", "onDestroy called");
		YoutubePlayerActivity.initialized = false;
		YoutubePlayerActivity.youTubePlayerFragment = null;
		YoutubePlayerActivity.player = null;
		super.onDestroy();
	}
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {	
		super.onPause();
	}
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		super.onRestart();
	}
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		super.onResume();
	}
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		super.onStart();
	}
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {	
		super.onStop();
	}

	public void onBackPressed (){
		if(initialized){
			if(!fullscreen || fullscreenForced){
				Log.i("youtubeplayer", "back sin fullscreen");
				setResult(0);
				finish();
			} else {
				Log.i("youtubeplayer", "back en fullscreen");
				YoutubePlayerActivity.player.setFullscreen(false);
			}
		}
		Log.i("youtubeplayer", "onBackPressed!!!");
		// return false;
	}

	@Override
	public void onFullscreen(boolean isFullscreen) {
		Log.i("youtubeplayer", "onFullscreen");
    	fullscreen = isFullscreen;
  	}		
	
}