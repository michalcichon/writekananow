package com.cichon.writekananow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class BrushView extends View {
	private Paint brush = new Paint();
	private Path path = new Path();
	public LayoutParams params;

	public BrushView(Context context, AttributeSet attrs) {
		super(context, attrs);
		brush.setAntiAlias(true);
		brush.setColor(Color.BLACK);
		brush.setStyle(Paint.Style.STROKE);
		brush.setStrokeJoin(Paint.Join.ROUND);
		brush.setStrokeWidth(25f);	
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float pointX = event.getX();
		float pointY = event.getY();

		// Checks for the event that occurs
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(pointX, pointY);
			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(pointX, pointY);
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
			return false;
		}
		// Force a view to draw.
		postInvalidate();
		return false;

	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, brush);
	}
	
	public void reset() {
		// reset the path
		path.reset();
		// invalidate the view
		postInvalidate();

	}
}
