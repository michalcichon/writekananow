package com.cichon.writekananow;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class LearnActivity extends Activity implements OnClickListener {
	
	private String kanaString = "";
	private KatakanaFactory katakanaFactory = new KatakanaFactory();

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
		// TODO Auto-generated method stub
		
	}

	private void nextKana() {
		CanvasView canvasView = (CanvasView) findViewById(R.id.learnCanvas);
		
		if(!kanaString.equals(""))
			canvasView.clearCanvas();
		
		kanaString = katakanaFactory.getRandomElementUnlike(kanaString);
        ImageView imgView = (ImageView)findViewById(R.id.learnImage);
		int imgId = getResources().getIdentifier(kanaString, "drawable", getPackageName());
		imgView.setImageResource(imgId);
	}

}
