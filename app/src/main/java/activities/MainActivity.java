package activities;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zeelo.ecard.R;

import widget.NotService;
import zeelo.ecard.Fill;
import zeelo.ecard.FillForOthers;
import zeelo.ecard.FloatingActionButton;
import zeelo.ecard.Intents;
import zeelo.ecard.TypeFaces;
import zeelo.ecard.Utility;
import zeelo.ecard.callMe;

public class MainActivity extends Activity
{
    public static Activity activity;
    int togg,togg2;
    static SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        prefs  = getSharedPreferences("settings",  MODE_MULTI_PROCESS);

        View ff = getLayoutInflater().inflate(R.layout.adder_floating,null);
        final FrameLayout.LayoutParams d  = new FrameLayout.LayoutParams(200,200);
        d.gravity = Gravity.BOTTOM | Gravity.RIGHT;

        addContentView(ff, d);

        Toast.makeText(this, "screen width = "+Utility.getScreenWidth(this)+", icon size = "+getResources().getDimension(R.dimen.icon_size),Toast.LENGTH_LONG).show();

        if(getLanguage().equals("eng"))
            new Utility().toEnglish(new int[]{R.id.tvs,R.id.tvh,R.id.tvr,R.id.tvc,R.id.tvf});
        else
            new Utility().toAmharic(new int[]{R.id.tvs, R.id.tvh, R.id.tvr, R.id.tvc, R.id.tvf});

        togg=1;
        togg2=1;

        final Intent notificationService = new Intent(this, NotService.class);
        new Utility().createHeadView(this);

        final View setting_page = getLayoutInflater().inflate(R.layout.frame_settings,null);
        final FrameLayout.LayoutParams dd  = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        dd.gravity = Gravity.BOTTOM | Gravity.CENTER;
        addContentView(setting_page,dd);
        setting_page.setVisibility(View.GONE);

        LinearLayout linn = (LinearLayout) findViewById(R.id.lin);
        linn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting_page.setVisibility(View.GONE);
            }
        });

        final Button infoB = (Button) setting_page.findViewById(R.id.button);
        final TextView infoTv = (TextView) setting_page.findViewById(R.id.textView6);

        final Switch switchFloatingB = (Switch) setting_page.findViewById(R.id.switch1);
        switchFloatingB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    new Utility().addFloatView();
                    updateFloatingPrefs(true);
                }
                else
                {
                    new Utility().RemoveFloatView();
                    updateFloatingPrefs(false);
                }
            }
        });

        final Switch switchNotificationB = (Switch) findViewById(R.id.switch2);
        switchNotificationB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    startService(notificationService);
                    updateNotificationPrefs(true);
                }
                else
                {
                    stopService(notificationService);
                    updateNotificationPrefs(false);
                }
            }
        });

        infoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(togg2==1)
                {
                    infoTv.setVisibility(View.VISIBLE);
                    togg2=0;
                }

                else
                {
                    infoTv.setVisibility(View.GONE);
                    togg2=1;
                }
            }
        });


        RadioGroup rg = (RadioGroup) setting_page.findViewById(R.id.rg);
        final RadioButton r1 = (RadioButton) setting_page.findViewById(R.id.radioButton);
        final RadioButton r2 = (RadioButton) setting_page.findViewById(R.id.radioButton2);
        r1.setTypeface(new TypeFaces().getTypefaceNormal(MainActivity.activity));
        r2.setTypeface(new TypeFaces().getTypefaceAmharic(MainActivity.activity));

        FloatingActionButton fb = (FloatingActionButton) ff.findViewById(R.id.fab_1);
        fb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(togg==1)
                {
                    setting_page.setVisibility(View.VISIBLE);
                    if(getLanguage().equals("eng"))
                        new Utility().toEnglishSetting(new int[]{R.id.textView6, R.id.textView4,R.id.switch2,R.id.button});

                    if(getLanguage().equals("amh"))
                        new Utility().toAmharicSetting(new int[]{R.id.textView6, R.id.textView4, R.id.switch2, R.id.button});

                    if(getFloating())
                        switchFloatingB.setChecked(true);

                    if(getNotification())
                        switchNotificationB.setChecked(true);

                    if(getLanguage().equals("eng"))
                        r1.setChecked(true);

                    else
                        r2.setChecked(true);
                    togg=0;
                }

                else
                {
                    setting_page.setVisibility(View.GONE);
                    togg=1;
                }

            }

        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton)
                {
                    new Utility().toEnglish(new int[]{R.id.tvs, R.id.tvh, R.id.tvr, R.id.tvc, R.id.tvf});
                    new Utility().toEnglishSetting(new int[]{R.id.textView6, R.id.textView4,R.id.switch2,R.id.button});
                    updateLanguagePrefs("eng");
                }
                else
                {
                    new Utility().toAmharic(new int[]{R.id.tvs,R.id.tvh,R.id.tvr,R.id.tvc,R.id.tvf});
                    new Utility().toAmharicSetting(new int[]{R.id.textView6, R.id.textView4, R.id.switch2, R.id.button});

                    updateLanguagePrefs("amh");
                }

            }
        });

    }

    public void updateLanguagePrefs(String language)
    {
        editor = prefs.edit();

        editor.putString("language", language);
        editor.apply();
        editor.commit();
    }

    public void updateNotificationPrefs(boolean notif)
    {
        editor = prefs.edit();

        editor.putBoolean("notif", notif);
        editor.apply();
        editor.commit();
    }

    public void updateFloatingPrefs(boolean floating)
    {
        editor = prefs.edit();

        editor.putBoolean("floating", floating);
        editor.apply();
        editor.commit();
    }

    public static String getLanguage()
    {
        return prefs.getString("language", "eng");
    }

    public boolean getFloating()
    {
        return prefs.getBoolean("floating", false);
    }

    public boolean getNotification()
    {
        return prefs.getBoolean("notif", true);
    }

    public void r(View v)
    {
        startActivity(new Intents().getKnowIntent());
    }

    public void tele(View v)
    {
        startActivity(new Intents().getTeleCallIntent());
    }

    public void ff(View v)
    {
        new FillForOthers(MainActivity.this, null);
    }

    public void f(View v)
    {
        new Fill(MainActivity.this, null);
    }

    public void c(View v)
    {
        new callMe(MainActivity.this, null);
    }

}
