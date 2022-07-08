package com.socard.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.socard.R;
import com.socard.Util.Typefaces;


/**
 * Created by victory on 8/19/2016.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {

        super(context);

        init(context, null, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs) {

        super(context, attrs);

        init(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomWidget, 0, 0);

        try {

            String fontInAssets = ta.getString(R.styleable.CustomWidget_customFont);
            setTypeface(Typefaces.get(context, "fonts/"+ fontInAssets));

        } finally {

            ta.recycle();
        }
    }
}
