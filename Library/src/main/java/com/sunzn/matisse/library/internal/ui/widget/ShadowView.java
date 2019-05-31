package com.sunzn.matisse.library.internal.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;

import com.sunzn.matisse.library.R;

public class ShadowView extends View implements OnTouchListener {

    public interface TouchListener {
        void onTouch();
    }

    private TouchListener mTouchListener;

    public ShadowView(Context context) {
        super(context);
        init();
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setBackgroundColor(Color.parseColor("#55000000"));
        setOnTouchListener(this);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            this.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha_show));
        } else if (visibility == GONE) {
            this.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha_hide));
        }
    }

    public void setOnTouchListener(TouchListener listener) {
        mTouchListener = listener;
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mTouchListener != null) {
                mTouchListener.onTouch();
            }
        }
        return true;
    }

}
