package com.fms.fmsindia.plugins;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by krishna on 26/2/16.
 */
public class CustomButton extends Button {
    public CustomButton(Context context) {
        this(context, null);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/calibri.otf");
            setTypeface(tf);
        }
    }
}
