package zeelo.ecard;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;

public class TypeFaces {

    static Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public Typeface getTypefaceNormal(Context c) {
        String pathn = "fonts/exo_medium.ttf";
        synchronized (cache) {
            if (!cache.containsKey(pathn)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(), pathn);
                    cache.put(pathn, t);
                } catch (Exception e) {
                    Log.e("TypeFace", "could not get typeface because :" + e.getMessage());
                }
                return null;
            }
        }
        return cache.get(pathn);
    }


    public Typeface getTypefaceAmharic(Context c) {
        String pathb = "fonts/nyala.ttf";

        synchronized (cache) {
            if (!cache.containsKey(pathb)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(), pathb);
                    cache.put(pathb, t);
                } catch (Exception e) {
                    Log.e("TypeFace", "could not get typeface because :" + e.getMessage());
                }
                return null;
            }
        }
        return cache.get(pathb);
    }
}
