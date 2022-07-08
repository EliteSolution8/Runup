package com.socard.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import com.socard.R;
import com.socard.Util.Typefaces;


/**
 * Created by victory on 8/20/2016.
 */
public class CustomEditText extends EditText {

    public CustomEditText(Context context) {

        super(context);

        init(context, null, 0);
    }

    public CustomEditText(Context context, AttributeSet attrs) {

        super(context, attrs);

        init(context, attrs, 0);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {

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
