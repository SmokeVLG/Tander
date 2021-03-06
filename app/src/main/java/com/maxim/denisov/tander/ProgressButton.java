package com.maxim.denisov.tander;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by Максим on 09.08.2017.
 */

public class ProgressButton extends AppCompatButton {
    private float mRatio;
    private int mColor = Color.GREEN;

    public ProgressButton(Context context) {
        super(context);
        init();
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){

    }

    public void setRatio (float ratio){
        mRatio = ratio;
        invalidate();
    }

    public float getRatio (){
        return mRatio ;
    }

    public void setColor (int color){
        mColor = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable fill = getResources().getDrawable(R.drawable.abc_btn_default_mtrl_shape);
        fill.setColorFilter( mColor, PorterDuff.Mode.MULTIPLY);
        fill.setAlpha(128);
        fill.setBounds(0, 0, (int) (getWidth()*mRatio),  getHeight());
        fill.draw(canvas);
        super.onDraw(canvas);
    }
}