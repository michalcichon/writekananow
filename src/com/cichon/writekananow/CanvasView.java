package com.cichon.writekananow;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

	private class DrawPoint {
		public boolean start = false;
		public Point p;

		DrawPoint(int x, int y) {
			p = new Point(x, y);
		}
	}
	
	public Boolean clear = false;

	private ArrayList<DrawPoint> points = new ArrayList<DrawPoint>();

	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void addPoint(DrawPoint p) {
		points.add(p);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(clear) {
			canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
			clear = false;
			this.destroyDrawingCache();
			System.out.println("CLEAR!");
		}

		Path path = new Path();

		for (int i = 0; i < points.size(); i++) {
			DrawPoint currentPoint = points.get(i);
			if (currentPoint.start == true)
				path.moveTo(currentPoint.p.x, currentPoint.p.y);
			else
				path.lineTo(currentPoint.p.x, currentPoint.p.y);
		}

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(25);
		paint.setColor(Color.BLACK);
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		DrawPoint p = new DrawPoint((int) event.getX(), (int) event.getY());
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			p.start = true;
		}
		
		points.add(p);
		invalidate();
		return true;
	}
	
	public void clearCanvas() {
		clear = true;
	}
}
