package com.cichon.writekananow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class SplashScreen extends Activity {
	
	private static final long SPLASH_DELAY = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		ActivityStarter starter = new ActivityStarter();
        starter.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}
	
	private class ActivityStarter extends Thread {

		@Override
		public void run() {
		    try {
		        Thread.sleep(SPLASH_DELAY);
		    } catch (Exception e) {
		        Log.e("SplashScreen", e.getMessage());
		    }
		    
		    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
		    SplashScreen.this.startActivity(intent);
		    SplashScreen.this.finish();
		}
	}

}
