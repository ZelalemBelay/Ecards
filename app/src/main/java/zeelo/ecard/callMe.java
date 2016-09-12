package zeelo.ecard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.zeelo.ecard.R;

import activities.CallMeActivity;
import activities.MainActivity;

public class callMe extends Intent
{
    public static Dialog d;
    public static EditText e;
    String ussdCode;

    public static boolean fromWidget=false;

    public callMe(final Activity context, Class<?> cls)
    {
        d = new Dialog(context);

        View v = context.getLayoutInflater().inflate(R.layout.call_me,null);

        ImageButton ib = (ImageButton) v.findViewById(R.id.imageButton);

         e = (EditText) v.findViewById(R.id.editText);

                if (MainActivity.getLanguage().equals("eng"))
                {
                    e.setHint("Enter Phone Number Here");
                    e.setTypeface(new TypeFaces().getTypefaceNormal(context));
                }

                else
                {
                    e.setHint("የስልክ ቁጥር አስገባ");
                    e.setTypeface(new TypeFaces().getTypefaceAmharic(context));
                }
        final Button b = (Button) v.findViewById(R.id.button2);

        if(MainActivity.getLanguage().equals("eng"))
            b.setText("   Send   ");

        else
        {
            b.setText("   ላክ   ");
            b.setTypeface(new TypeFaces().getTypefaceAmharic(context));
        }

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                CallMeActivity.activity.startActivityForResult(i,1);

            }
        });

        d.setContentView(v);
        d.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                {
                    x=13;
                    e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(x)});
                }

                else if(e.getText().charAt(0)=='2')
                {
                    if(e.getText().charAt(1)=='5')

                        if(e.getText().charAt(2)=='1')
                            if(e.getText().charAt(3)=='9')
                            {
                                x=12;
                                e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(x)});
                            }
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
                    {
                        x = 10;
                        e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(x)});
                    }
                        else
                            forE(e, context);

                }
                if(e.getText().toString().replace(" ","").replace(" ","").length() == x)
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
                    e.setHint("የተሳሳተ ግቤት");

                e.setHintTextColor(Color.RED);
            }

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
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
