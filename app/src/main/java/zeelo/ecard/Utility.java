package zeelo.ecard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zeelo.ecard.R;

import activities.MainActivity;

/**
 * Created by Zeeloo on 8/14/2015.
 */
public class Utility {

    public static int getScreenWidth(Activity activity)
    {
        Display disp = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();

        disp.getSize(size);

        int x = size.x;

        return x;

    }

    public static String getLanguage(SharedPreferences prefs)
    {
        return prefs.getString("language", "eng");
    }

    public static WindowManager windowManager;
    public static FrameLayout frameLayout;
    public static WindowManager.LayoutParams params;

    public void createHeadView(final Activity a)
    {
        final View t;
        ImageView image;

        params = new WindowManager.LayoutParams(
                70,
                70,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT;

        frameLayout = new FrameLayout(a);

        windowManager = (WindowManager) a.getSystemService(a.WINDOW_SERVICE);
        LayoutInflater mainView = (LayoutInflater) a.getSystemService(a.LAYOUT_INFLATER_SERVICE);
        // Here is the place where you can inject whatever layout you want.
        t = mainView.inflate(R.layout.head, frameLayout);
        image = (ImageView) t.findViewById(R.id.floatingImage);
        frameLayout.setClickable(true);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                a.startActivity(new Intent(a , MainActivity.class));
            }
        });

        image.setOnTouchListener(new View.OnTouchListener() {
        private int initialX;
        private int initialY;
        private float initialTouchX;
        private float initialTouchY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    initialX = params.x;
                    initialY = params.y;
                    initialTouchX = event.getRawX();
                    initialTouchY = event.getRawY();
                    return true;
                case MotionEvent.ACTION_UP:
                    return true;
                case MotionEvent.ACTION_MOVE:
                    params.x = initialX + (int) (event.getRawX() - initialTouchX);
                    params.y = initialY + (int) (event.getRawY() - initialTouchY);
                    windowManager.updateViewLayout(t, params);
                    return true;
            }
            return false;
        }
    });

    }

    public void addFloatView()
    {
        windowManager.addView(frameLayout, params);
    }

    public void RemoveFloatView()
    {
            windowManager.removeView(frameLayout);
    }

    public  void toEnglish(int [] tvsID) {

        for (int tvId : tvsID) {
            TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
            tv.setTypeface(new TypeFaces().getTypefaceNormal(MainActivity.activity));

            if (tv.getId() == R.id.tvc)
                tv.setText("Call me Back");

            if (tv.getId() == R.id.tvs)
                tv.setText("Transfer");

            if (tv.getId() == R.id.tvf)
                tv.setText("Recharge");

            if (tv.getId() == R.id.tvr)
                tv.setText("Balance??");

            if (tv.getId() == R.id.tvh)
                tv.setText("TeleCard");

        }
    }


    public  void toAmharic(int [] tvsID)
    {

        for(int tvId: tvsID)
        {
            TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
            tv.setTypeface(new TypeFaces().getTypefaceAmharic(MainActivity.activity));

            if(tv.getId()==R.id.tvc)
                    tv.setText(" ይደውሉልኝ");

           else if(tv.getId()==R.id.tvs)
                    tv.setText("ማስተላለፍ");

            else if(tv.getId()==R.id.tvf)
                    tv.setText("ካርድ መሙያ");

            else if(tv.getId()==R.id.tvr)
                    tv.setText("ቀሪ ሂሳብ");

            else if(tv.getId()==R.id.tvh)
                    tv.setText("ቴሌ ካርድ");

        }
    }




    public  void toAmharicSetting(int [] tvsID) {

        for (int tvId : tvsID)
        {
            if (tvId == R.id.textView4)
            {
                TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
                tv.setTypeface(new TypeFaces().getTypefaceAmharic(MainActivity.activity));
                tv.setText("አማራጮች");
            }

            else if (tvId == R.id.textView6)
            {
                TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
                tv.setTypeface(new TypeFaces().getTypefaceAmharic(MainActivity.activity));
                tv.setText(MainActivity.activity.getResources().getString(R.string.app_info_amh));
            }

            else if (tvId == R.id.switch2)
            {
                Switch s = (Switch) MainActivity.activity.findViewById(tvId);
                s.setTypeface(new TypeFaces().getTypefaceAmharic(MainActivity.activity));
                s.setText("ሁሌም የሚታይ የኖቲፊኬሽን ዘንግ   ");
                s.setTextOn("አሳይ");
                s.setTextOff("አጥፋ");
            }

            else if (tvId == R.id.button)
            {
                Button b = (Button) MainActivity.activity.findViewById(tvId);
                b.setText("    መረጃ?     ");
                b.setTypeface(new TypeFaces().getTypefaceAmharic(MainActivity.activity));
            }

            else if (tvId == R.id.textView2)
            {
                TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
                tv.setText("የመጠቀሚያ ቋንቋ");
                tv.setTypeface(new TypeFaces().getTypefaceAmharic(MainActivity.activity));
            }

        }
    }



    public  void toEnglishSetting(int [] tvsID) {

        for (int tvId : tvsID)
        {
            if (tvId == R.id.textView4)
            {
                TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
                tv.setTypeface(new TypeFaces().getTypefaceNormal(MainActivity.activity));
                tv.setText("Options");
            }

            else if (tvId == R.id.textView6)
            {
                TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
                tv.setTypeface(new TypeFaces().getTypefaceNormal(MainActivity.activity));
                tv.setText(MainActivity.activity.getResources().getString(R.string.app_info));
            }

            else if (tvId == R.id.switch2)
            {
                Switch s = (Switch) MainActivity.activity.findViewById(tvId);
                s.setText("     Notification Bar         ");
                s.setTextOn("ON");
                s.setTextOff("OFF");
                s.setTypeface(new TypeFaces().getTypefaceNormal(MainActivity.activity));
            }

            else if (tvId == R.id.button)
            {
                Button b = (Button) MainActivity.activity.findViewById(tvId);
                b.setText("    App infos?    ");
                b.setTypeface(new TypeFaces().getTypefaceNormal(MainActivity.activity));
            }

            else if (tvId == R.id.textView2)
            {
                TextView tv = (TextView) MainActivity.activity.findViewById(tvId);
                tv.setText("     Language");
                tv.setTypeface(new TypeFaces().getTypefaceNormal(MainActivity.activity));
            }

        }
    }


}
