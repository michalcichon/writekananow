package com.cichon.writekananow;

import java.util.Locale;
import java.util.Random;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class QuizActivity extends Activity implements OnClickListener {
	
	final Random rand = new Random();
	private SparseArray<String> answers;
	private int correctAnswerPosition;
	
	private KatakanaFactory katakanaFactory = new KatakanaFactory();

	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.pull_in_from_bottom, R.anim.hold);
		setContentView(R.layout.activity_quiz);
		
		findViewById(R.id.button_answer_0).setOnClickListener(this);
        findViewById(R.id.button_answer_1).setOnClickListener(this);
        findViewById(R.id.button_answer_2).setOnClickListener(this);
        findViewById(R.id.button_answer_3).setOnClickListener(this);
		
		Tuple randomElement = katakanaFactory.getRandomElement();
		ImageView imgView = (ImageView)findViewById(R.id.quizImage);
		int imgId = getResources().getIdentifier(randomElement.getName(), "drawable", getPackageName());
		imgView.setImageResource(imgId);
		
		int max = 3, min = 0;
		correctAnswerPosition = rand.nextInt(max - min + 1) + min;
		answers = new SparseArray<String>();
		
		answers.put(correctAnswerPosition, getLabelFromTuple(randomElement));
		
		for(int i=min; i<=max; ++i) {
			if(i==correctAnswerPosition)
				continue;

			String label = getLabelFromTuple(katakanaFactory.getRandomElement());
			
			while(elementExists(label, answers)) {
				label = getLabelFromTuple(katakanaFactory.getRandomElement());
			}
			answers.put(i, label);
		}
		
		System.out.println("correctAnswerPosition: " + correctAnswerPosition);
		
		for(int i=min; i<=max; ++i) {
			int id = getResources().getIdentifier("button_answer_" + i, "id", getPackageName());
			Button temp = (Button)findViewById(id);
			temp.setText(answers.get(i));
		}
		
	}
	
	/**
	 * @note: indexOfValue(E value) probably tests by ==, and not equals()
	 */
	private boolean elementExists(String element, SparseArray<String> array) {
		for(int i=0; i<=array.size(); ++i) {
			if(element.equals(array.get(i)))
				return true;
		}
		return false;
	}
	
	private String getLabelFromTuple(Tuple tuple) {
		return tuple.getName().toString().toUpperCase(Locale.ENGLISH);
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

	@Override
	public void onClick(View arg0) {
		new AlertDialog.Builder(this)
	    .setTitle("Test")
	    .setMessage("Lorem ipsum...")
	    .show();
		
	}

}
