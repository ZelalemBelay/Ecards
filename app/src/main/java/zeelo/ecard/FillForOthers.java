package zeelo.ecard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zeelo.ecard.R;

import activities.FillForActivity;
import activities.MainActivity;

/**
 * Created by Zeeloo on 7/31/2015.
 */

public class FillForOthers extends Intent
{
    public static Dialog d;
    String ussdCode;

    public static EditText e;

    public FillForOthers(final Activity context, Class<?> cls)
    {

        d = new Dialog(context);
        View v = context.getLayoutInflater().inflate(R.layout.transfer,null);

        ImageButton ib = (ImageButton) v.findViewById(R.id.imageButton2);

            e = (EditText) v.findViewById(R.id.editText2);
        final EditText e2 = (EditText) v.findViewById(R.id.editText);

        if(MainActivity.getLanguage().equals("eng"))
        {
            e.setHint("Enter Phone Number Here");
            e2.setHint("Enter Money to Transfer");
            e.setTypeface(new TypeFaces().getTypefaceNormal(context));
            e2.setTypeface(new TypeFaces().getTypefaceNormal(context));
        }

        else
        {
            e.setHint("የስልክ ቁጥር አስገባ");
            e2.setHint("የሚላከው ካርድ መጠን");
            e.setTypeface(new TypeFaces().getTypefaceAmharic(context));
            e2.setTypeface(new TypeFaces().getTypefaceAmharic(context));

        }

        final Button b = (Button) v.findViewById(R.id.button2);

        if(MainActivity.getLanguage().equals("eng"))
            b.setText("   Send   ");

        else
        {
            b.setText("   ላክ   ");
            b.setTypeface(new TypeFaces().getTypefaceAmharic(context));
        }

        ib.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                FillForActivity.activity.startActivityForResult(i,0);
            }
        });

        e.setTextSize(20);

        e2.setTextSize(20);

        e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});
        e2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        d.setContentView(v);

        d.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num;
                num = e.getText().toString().replace("+251","0").replace("-","");
                String card = e2.getText().toString();
                ussdCode = "*" + "806" + "*" +num+ "*"+card+Uri.encode("#");
                context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
            }
        });

        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e.getText().length()>0)
                {

                    int x = 10;
                    if(e.getText().charAt(0)=='+')
                        x=13;

                    else if(e.getText().charAt(0)=='2')
                    {
                        if(e.getText().length() == 2)
                           if(e.getText().charAt(1) == '5')
                            if(e.getText().length() == 3)
                            if(e.getText().charAt(2)=='1')
                                if(e.getText().length() == 4)
                                if(e.getText().charAt(3)=='9')
                                    x=12;
                                else
                                    forE(e,context);
                            else
                                forE(e,context);

                        else
                            forE(e,context);
                    }


                    else if(e.getText().charAt(0)=='0')
                    {
                        if (e.getText().length() == 2)
                            if (e.getText().charAt(1) == '9')
                                x = 10;
                            else
                                forE(e, context);

                    }
                    if(e.getText().toString().replace(" ","").replace("-","").length() == x)
                    {
                        String card = e.getText().toString();
                        ussdCode = "*" + "807" + "*" +card+ Uri.encode("#");
                        e.setTextColor(Color.WHITE);
                        b.setEnabled(true);
                    }

                    else
                    {
                        e.setTextColor(Color.RED);
                        b.setEnabled(false);
                    }

                    if(e.getText().length()>14)
                        e.setText("");

                    if(MainActivity.getLanguage().equals("eng"))
                        e.setHint("WRONG INPUT!");

                    else
                    {
                        e.setHint("የተሳሳተ ግቤት");
                        e.setTypeface(new TypeFaces().getTypefaceAmharic(context));
                    }

                    e.setHintTextColor(Color.RED);
                }

            }

            @Override
            public void afterTextChanged(Editable s)
            {}

        });

        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e2.getText().length()>0)
                {
                    b.setEnabled(true);
                }

                else
                {
                    b.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {}

        });


    }

    private void forE(EditText e, Activity context) {
        e.setText("");

        if(MainActivity.getLanguage().equals("eng"))
            e.setHint("WRONG INPUT!");

        else
        {
            e.setHint("የተሳሳተ ግቤት");
            e.setTypeface(new TypeFaces().getTypefaceAmharic(context));
        }
    }

    public Dialog getD()
    {
        return d;
    }

}