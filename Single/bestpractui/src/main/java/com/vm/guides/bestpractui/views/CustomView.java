package com.vm.guides.bestpractui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.vm.guides.bestpractui.R;

/**
 * http://developer.android.com/training/custom-views/create-view.html
 * <p/>
 * Taking some time to carefully define your view's interface reduces future maintenance costs. A good rule to follow
 * is to always expose any property that affects the visible appearance or behavior of your custom view.
 */
public class CustomView extends View {

    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;

    private int contentWidth;
    private int contentHeight;

    public CustomView(Context context) {

        super(context);
        init(null, 0);
    }

    //    To allow the Android Developer Tools to interact with your view, at a minimum you must provide a
    // constructor that takes a Context and an AttributeSet object as parameters.
    public CustomView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        //        Resource references within attribute values are resolved
        //        Styles are applied
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView, defStyle, 0);
        try {
            mExampleString = a.getString(R.styleable.CustomView_exampleString);
            mExampleColor = a.getColor(R.styleable.CustomView_exampleColor, mExampleColor);
            // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
            // values that should fall on pixel boundaries.
            mExampleDimension = a.getDimension(R.styleable.CustomView_exampleDimension, mExampleDimension);

            if (a.hasValue(R.styleable.CustomView_exampleDrawable)) {
                mExampleDrawable = a.getDrawable(R.styleable.CustomView_exampleDrawable);
                mExampleDrawable.setCallback(this);
            }
        } finally {
            a.recycle();
        }

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {

        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;

        // Notice calls invalidate() and requestLayout(). These calls are crucial to ensure that the
        // view behaves reliably. You have to invalidate the view after any change to its properties that might
        // change its appearance, so that the system knows that it needs to be redrawn. Likewise, you need to request
        // a new layout if a property changes that might affect the size or shape of the view. Forgetting these
        // method calls can cause hard-to-find bugs.
        // VM: required for views consisting of other views, not required for this view
        invalidate();
        requestLayout();
    }

    @Override
//    If you need finer control over your view's layout parameters, implement onMeasure().
// This method's parameters are View.MeasureSpec values that tell you how big your view's parent
// wants your view to be, and whether that size is a hard maximum or just a suggestion.
// As an optimization, these values are stored as packed integers, and you use the static methods
// of View.MeasureSpec to unpack the information stored in each integer.
    // VM: does nothing in this view
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
//        The helper method resolveSizeAndState() is used to create the final width and height values.
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int minh = MeasureSpec.getSize(w) - (int) mTextWidth + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(MeasureSpec.getSize(w) - (int) mTextWidth, heightMeasureSpec, 0);

//        The method communicates its results by calling setMeasuredDimension(). Calling this method is mandatory.
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();

        contentWidth = w - paddingLeft - paddingRight;
        contentHeight = h - paddingTop - paddingBottom;

        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop, paddingLeft + contentWidth, paddingTop + contentHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // Draw the text.
        canvas.drawText(mExampleString, paddingLeft + (contentWidth - mTextWidth) / 2, paddingTop + (contentHeight +
                mTextHeight) / 2, mTextPaint);

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.draw(canvas);
        }
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {

        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {

        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {

        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {

        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {

        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {

        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {

        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {

        mExampleDrawable = exampleDrawable;
    }
}
