package zeelo.ecard;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;

/**
 * Created by Zeeloo on 10/11/2015.
 */
public class TFs extends MetricAffectingSpan
{
      private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(12);
    private Typeface mTypeface;
    public TFs(Context context)
    {
//        mTypeface = sTypefaceCache.get("gg");
        if (mTypeface == null)
        {
            mTypeface = new TypeFaces().getTypefaceNormal(context);
//             sTypefaceCache.put("gg", mTypeface);
        }
    }
    @Override public void updateMeasureState(TextPaint p)
    {
        p.setTypeface(mTypeface);
          p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
    @Override public void updateDrawState(TextPaint tp)
    {
        tp.setTypeface(mTypeface);
          tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}



