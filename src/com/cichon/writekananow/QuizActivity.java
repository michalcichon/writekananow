package com.cichon.writekananow;

import java.util.Locale;
import java.util.Random;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends Activity implements OnClickListener {
	
	final Random rand = new Random();
	private SparseArray<String> answers;
	private int correctAnswerPosition;
	private String kanaString;
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
        findViewById(R.id.button_quiz_next).setOnClickListener(this);
        
		generateQuestion();
		
	}
	
	/**
	 * @note: indexOfValue(E value) probably tests by ==, and not equals()
	 * TODO: check if it's a good explanation
	 */
	private boolean elementExists(String element, SparseArray<String> array) {
		for(int i=0; i<=array.size(); ++i) {
			if(element.equals(array.get(i)))
				return true;
		}
		return false;
	}
	
	private void disableButtons() {
		for(int i=0; i<=3; ++i) {
			int id = getResources().getIdentifier("button_answer_" + i, "id", getPackageName());
			Button temp = (Button)findViewById(id);
			temp.setEnabled(false);
		}
	}
	
	private void enableButtons() {
		for(int i=0; i<=3; ++i) {
			int id = getResources().getIdentifier("button_answer_" + i, "id", getPackageName());
			Button temp = (Button)findViewById(id);
			temp.setEnabled(true);
		}
	}
	
	private void showNextButton() {
		Button nextButton = (Button) findViewById(R.id.button_quiz_next);
		nextButton.setVisibility(View.VISIBLE);
	}
	
	private void hideNextButton() {
		Button nextButton = (Button) findViewById(R.id.button_quiz_next);
		nextButton.setVisibility(View.INVISIBLE);
	}
	
	private String getLabelFromKanaString(String kana) {
		return kana.toUpperCase(Locale.ENGLISH);
	}
	
	private void generateQuestion() {
		hideNextButton();
		if(kanaString == null)
			kanaString = katakanaFactory.getRandomElement();
		else
			kanaString = katakanaFactory.getRandomElementUnlike(kanaString);
		
		ImageView imgView = (ImageView)findViewById(R.id.quizImage);
		int imgId = getResources().getIdentifier(kanaString, "drawable", getPackageName());
		imgView.setImageResource(imgId);
		
		int max = 3, min = 0;
		correctAnswerPosition = rand.nextInt(max - min + 1) + min;
		answers = new SparseArray<String>();
		
		answers.put(correctAnswerPosition, getLabelFromKanaString(kanaString));
		
		for(int i=min; i<=max; ++i) {
			if(i==correctAnswerPosition)
				continue;

			String label = getLabelFromKanaString(katakanaFactory.getRandomElement());
			
			while(elementExists(label, answers)) {
				label = getLabelFromKanaString(katakanaFactory.getRandomElement());
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
	public void onClick(View button) {
		
		if(button.getId() == R.id.button_quiz_next)
			nextButtonClicked();
		else {
			int correctId = getResources().getIdentifier("button_answer_" + correctAnswerPosition, "id", getPackageName());
			int color = Color.RED;
			if(button.getId() == correctId) {
				color = Color.GREEN;
			}
			
			TextView text = (TextView) findViewById(R.id.quizText);
			text.setText(answers.get(correctAnswerPosition));
			text.setTextColor(color);
			disableButtons();
			showNextButton();
		}
	}

	private void nextButtonClicked() {
		enableButtons();
		generateQuestion();
	}

}
