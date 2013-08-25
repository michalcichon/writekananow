package com.cichon.writekananow;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class LearnActivity extends Activity implements OnClickListener {
	
	private String kanaString = "";
	private KatakanaFactory katakanaFactory = new KatakanaFactory();

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.pull_in_from_bottom, R.anim.hold);
		setContentView(R.layout.activity_learn);
		
		ImageView image = (ImageView) findViewById(R.id.learnImage);
		image.setAlpha(30);
		
		findViewById(R.id.button_learn_next).setOnClickListener(this);
        findViewById(R.id.button_learn_validate).setOnClickListener(this);
        nextKana();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.learn, menu);
		return true;
	}
	
	@Override
    protected void onPause() {
        overridePendingTransition(R.anim.hold, R.anim.push_out_to_bottom);
        super.onPause();
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
    	case R.id.button_learn_next:
    		nextKana();
    		break;
    									
    	case R.id.button_learn_validate: 
    		validateKana();
    		break;						
    	default:
    	}
		
	}

	private void validateKana() {
		
		//get Bitmap from brushView
		BrushView brushView = (BrushView) findViewById(R.id.learnCanvas);
		Bitmap brushViewBitmap = loadBitmapFromView(brushView);
		
		//get Bitmap from kana image
		int imgId = getResources().getIdentifier(kanaString, "drawable", getPackageName());
		Bitmap resourceBitmap = BitmapFactory.decodeResource(getResources(), imgId);
		Bitmap resizedBitmap = Bitmap.createScaledBitmap(resourceBitmap, brushViewBitmap.getWidth(), brushViewBitmap.getHeight(), false);
		
		int blackPoints = 0, notBlackPoints = 0;
		int blackPointsValidated = 0, notBlackPointsValidated = 0;
		for(int i=0, width = resizedBitmap.getWidth(); i<width; ++i) {
			for(int j=0, height = resizedBitmap.getHeight(); j<height; ++j) {
				int pixel = resizedBitmap.getPixel(i, j);
				if(pixel == Color.BLACK) {
					blackPoints++;
					if(brushViewBitmap.getPixel(i, j) == Color.BLACK) {
						blackPointsValidated++;
					}
				} else {
					notBlackPoints++;
					if(brushViewBitmap.getPixel(i, j) != Color.BLACK) {
						notBlackPointsValidated++;
					}
				}
			}
		}
		
		float score = 0.5f * ((float)blackPointsValidated / (float)blackPoints) + 0.5f * ((float)notBlackPointsValidated / (float)notBlackPoints);
		
		setMyResult(score);
		
	}

	private void nextKana() {
		resetValidateButton();
		BrushView brushView = (BrushView) findViewById(R.id.learnCanvas);
		brushView.reset();
		
		kanaString = katakanaFactory.getRandomElementUnlike(kanaString);
        ImageView imgView = (ImageView)findViewById(R.id.learnImage);
		int imgId = getResources().getIdentifier(kanaString, "drawable", getPackageName());
		imgView.setImageResource(imgId);
	}
	
	public static Bitmap loadBitmapFromView(View v) {
	     Bitmap b = Bitmap.createBitmap( v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);                
	     Canvas c = new Canvas(b);
	     v.layout(0, 0, v.getWidth(), v.getHeight());
	     v.draw(c);
	     return b;
	}
	
	public void setMyResult(float result) {
		String str = getString(R.string.try_again);
		int buttonBackground = R.drawable.button_red;
		if(result > 0.8) {
			str = getString(R.string.good);
			buttonBackground = R.drawable.button_green;
		} else if(result > 0.7){
			str = getString(R.string.ok);
			buttonBackground = R.drawable.button_yellow;
		}
		
		Button validateButton = (Button) findViewById(R.id.button_learn_validate);
		validateButton.setText(str);
		validateButton.setBackgroundResource(buttonBackground);
		validateButton.setEnabled(false);
	}
	
	public void resetValidateButton() {
		Button validateButton = (Button) findViewById(R.id.button_learn_validate);
		validateButton.setBackgroundResource(R.drawable.button_red);
		validateButton.setText(getString(R.string.validate));
		validateButton.setEnabled(true);
	}

}
