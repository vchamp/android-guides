package com.vm.guides.bestpractui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * http://developer.android.com/training/custom-views/making-interactive.html
 */
public class InteractiveView extends View {

    private static final int SCROLL_SCALE = 6;

    private Paint paint;

    private RectF contentBounds = new RectF();

    private GestureDetector gestureDetector;
    private Scroller scroller;

    private int currentX = 0;
    private int currentY = 0;
    private int minX = 0;
    private int minY = 0;
    private int maxX;
    private int maxY;

    private int arcRotation = 0;

    public InteractiveView(Context context) {
        super(context);
        init(null, 0);
    }

    public InteractiveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint();
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            //            Always implement an onDown() method that returns true. This step is necessary because all
            // gestures begin with an onDown() message. If you return false from onDown(), as GestureDetector
            // .SimpleOnGestureListener does, the system assumes that you want to ignore the rest of the gesture, and
            // the other methods of GestureDetector.OnGestureListener never get called.
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("TEMP", "");
                scroller.fling(currentX, currentY, (int) (velocityX / SCROLL_SCALE), (int) (velocityY / SCROLL_SCALE),
                        minX, minY, maxX, maxY);
                postInvalidate();
                return false;
            }
        });
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        contentBounds.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
        maxX = w;
        maxY = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!scroller.isFinished()) {
            scroller.computeScrollOffset();
            setArcRotation(scroller.getCurrY());
        }

        paint.setColor(0xffff0000);
        canvas.drawArc(contentBounds, arcRotation, 90, true, paint);
        paint.setColor(0xff00ff00);
        canvas.drawArc(contentBounds, arcRotation + 90, 90, true, paint);
        paint.setColor(0xff0000ff);
        canvas.drawArc(contentBounds, arcRotation + 180, 90, true, paint);
        paint.setColor(0xffffff00);
        canvas.drawArc(contentBounds, arcRotation + 270, 90, true, paint);
    }

    private void setArcRotation(int angle) {
        arcRotation = angle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = gestureDetector.onTouchEvent(event);
        if (!result) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                stopScrolling();
                result = true;
            }
        }
        return result;
    }

    private void stopScrolling() {

    }
}
