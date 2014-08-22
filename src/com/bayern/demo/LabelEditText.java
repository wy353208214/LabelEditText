package com.bayern.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.widget.EditText;

import com.bayern.myedittext.R;

public class LabelEditText extends EditText {
    
    private float labelBaseLine;
    private String label;
    private Paint labelPaint;
    int defaultPadding = 0;

    public LabelEditText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context, null);
    }

    public LabelEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context, attrs);
    }

    public LabelEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.labelEdittext);
        label = ta.getString(R.styleable.labelEdittext_label_text);
        ta.recycle();
        defaultPadding = (int) context.getResources().getDimension(R.dimen.padding);

        int paddindLeft = (int) (label.length() * getTextSize());
        setPadding(paddindLeft + defaultPadding + defaultPadding, 0, 0, 0);
        labelPaint = new Paint();
        labelPaint.setTextSize(getTextSize());
        labelPaint.setColor(getCurrentTextColor());

        setSingleLine();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (labelBaseLine == 0) {
            FontMetrics metrics = labelPaint.getFontMetrics();
            labelBaseLine = getMeasuredHeight() / 2 + (metrics.bottom - metrics.top) / 2 - metrics.bottom;
        }
        canvas.drawText(label, 0 + defaultPadding + getScrollX(), labelBaseLine, labelPaint);
        super.onDraw(canvas);
    }

}
