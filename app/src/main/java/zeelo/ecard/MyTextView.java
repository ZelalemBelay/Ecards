package zeelo.ecard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zeelo.ecard.R;

public class MyTextView extends TextView {

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeAmh();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAmh();
    }

    public MyTextView(Context context)
    {
        super(context);
        initializeAmh();
    }

    private void initializeEng() {
        setTypeface(new TypeFaces().getTypefaceNormal(getContext()));
    }


    private void initializeAmh()
    {
        setTypeface(new TypeFaces().getTypefaceAmharic(getContext()));
    }

}
