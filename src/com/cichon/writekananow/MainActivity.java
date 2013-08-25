package com.cichon.writekananow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //bind menu listeners
        findViewById(R.id.button_learn).setOnClickListener(this);
        findViewById(R.id.button_quiz).setOnClickListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		switch(v.getId()) {
    	case R.id.button_learn:			startActivity(new Intent(this, LearnActivity.class));
    									break;
    									
    	case R.id.button_quiz: 			startActivity(new Intent(this, QuizActivity.class));
    									break;
    									
    	default:
    	}
	}
	
	public boolean onOptionsItemSelected(MenuItem element){
    	switch(element.getItemId()){
    	case R.id.action_settings: 		startActivity(new Intent(this, Settings.class));  
    									return true;
    	case R.id.learn_menu:             startActivity(new Intent(this, LearnActivity.class));  
										return true;								
    	case R.id.quiz_menu:     		startActivity(new Intent(this, QuizActivity.class));  
										return true;
								
    	}
    	return false;
    }
    
}
