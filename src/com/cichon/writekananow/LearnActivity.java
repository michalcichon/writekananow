package com.cichon.writekananow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LearnActivity extends Activity implements OnClickListener {
	
	private String kanaString = "";
	private KatakanaFactory katakanaFactory = new KatakanaFactory();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.pull_in_from_bottom, R.anim.hold);
		setContentView(R.layout.activity_learn);
		
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
		
		ImageView imageView = (ImageView) findViewById(R.id.learnImage);
		Bitmap imageViewBitmap = loadBitmapFromView(imageView);
		
		int blackPoints = 0, notBlackPoints = 0;
		int blackPointsValidated = 0, notBlackPointsValidated = 0;
		for(int i=0, width = imageViewBitmap.getWidth(), jump_i = 4; i<width; i += jump_i) {
			for(int j=0, height = imageViewBitmap.getHeight(), jump_j = 4; j<height; j += jump_j) {
				int pixel = imageViewBitmap.getPixel(i, j);
				if(pixel == Color.rgb(204, 204, 204)) {
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
		int imgId = getResources().getIdentifier(kanaString + "_shadow", "drawable", getPackageName());
		imgView.setImageResource(imgId);
		
		TextView text = (TextView) findViewById(R.id.learnText);
		text.setText(kanaString);
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
	
	public boolean onOptionsItemSelected(MenuItem element){
    	switch(element.getItemId()){
    	case R.id.action_settings: 		startActivity(new Intent(this, Settings.class));  
    									return true;
    									
    	case R.id.main_menu: 		startActivity(new Intent(this, MainActivity.class));  
										return true;
    	}
    	return false;
    }

}
