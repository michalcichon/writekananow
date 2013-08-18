package com.cichon.writekananow;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class QuizActivity extends Activity {
	
	private KatakanaFactory katakanaFactory = new KatakanaFactory();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.pull_in_from_bottom, R.anim.hold);
		setContentView(R.layout.activity_quiz);
		
		Tuple randomElement = katakanaFactory.getRandomElement();
		Tuple rand2 = katakanaFactory.getRandomElementUnlike(randomElement);
		
		TextView tuple1 = (TextView) findViewById(R.id.tuple1);
		TextView tuple2 = (TextView) findViewById(R.id.tuple2);
		
		if(tuple1 != null)
			tuple1.setText(randomElement.getName());
		
		if(tuple2 != null)
			tuple2.setText(rand2.getName());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
	
	@Override
    protected void onPause() {
        overridePendingTransition(R.anim.hold, R.anim.push_out_to_bottom);
        super.onPause();
    }

}
