package com.fms.fmsindia.plugins;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by krishna on 26/2/16.
 */
public class CustomEditext extends EditText {
    public CustomEditext(Context context) {
        this(context, null);
        init();
    }

    public CustomEditext(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CustomEditext(Context context, AttributeSet attrs, int defStyle) {
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
