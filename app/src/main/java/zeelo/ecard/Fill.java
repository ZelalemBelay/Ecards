package zeelo.ecard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zeelo.ecard.R;

import activities.MainActivity;

/**
 * Created by Zeeloo on 7/31/2015.
 */

public class Fill extends Intent
{
    public static Dialog d;
    public static EditText e;

    public Fill(final Activity packageContext, Class<?> cls)
    {
        d  = new Dialog(packageContext);
        View v = packageContext.getLayoutInflater().inflate(R.layout.fill, null);

        e = (EditText) v.findViewById(R.id.editText);

        if(MainActivity.getLanguage().equals("eng")) {
            e.setHint("Enter Numbers Here");
            e.setTypeface(new TypeFaces().getTypefaceNormal(packageContext));

        }

            else
        {
            e.setHint("የካርድ ቁጥሩን አስገባ");
            e.setTypeface(new TypeFaces().getTypefaceAmharic(packageContext));
        }

        e.setTextSize(20);
        e.setMaxEms(14);

        d.setContentView(v);

        d.show();

        e.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e.getText().length()==14)
                {
                    String card = e.getText().toString();
                    String ussdCode = "*" + "805" + "*" +card+ Uri.encode("#");
                    packageContext.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
                    e.setTextColor(Color.BLACK);
                    e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                    d.setTitle("Wait!");
                    e.setEnabled(false);

                }

                else
                {
                    if(MainActivity.getLanguage().equals("eng"))
                        d.setTitle((14-e.getText().length())+" Remaining");
                    else
                    {
                        d.setTitle((14-e.getText().length())+" ቀሪ ቁጥሮች");
                    }

                    e.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

    }

    public Dialog getD()
    {
        return d;
    }
}
