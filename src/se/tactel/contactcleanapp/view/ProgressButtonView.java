// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.Button;

public class ProgressButtonView extends Button
{

    public ProgressButtonView(Context context)
    {
        super(context);
    }

    public ProgressButtonView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public ProgressButtonView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    private RectF getOval(Canvas canvas)
    {
        if(mOval == null)
            mOval = new RectF(canvas.getClipBounds());
        return mOval;
    }

    private Paint getPainter()
    {
        if(mPaint == null)
        {
            mPaint = new Paint(1);
            mPaint.setColor(0xff529acd);
        }
        return mPaint;
    }

    protected void onDraw(Canvas canvas)
    {
        canvas.drawArc(getOval(canvas), -90F, mCurrentAngle, true, getPainter());
        super.onDraw(canvas);
    }

    public void setCurrentAngle(float f)
    {
        mCurrentAngle = f;
    }

    private float mCurrentAngle;
    private RectF mOval;
    private Paint mPaint;
}
