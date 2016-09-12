package activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputFilter;
import zeelo.ecard.FillForOthers;
import zeelo.ecard.Utility;

/**
 * Created by Zeeloo on 8/11/2015.
 */

public class FillForActivity extends Activity
{
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        FillForOthers f=null;

        try
        {
           f = new FillForOthers(this,null);
//            MainActivity.exitFlag = false;

            f.getD().setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
        }
        catch (Exception e)
        {
//            MainActivity.exitFlag = true;
            startActivity(new Intent(FillForActivity.this, MainActivity.class));
            startActivity(new Intent(FillForActivity.this, FillForActivity.class));
            finish();
        }

        if(Utility.getLanguage(getSharedPreferences("settings",  MODE_MULTI_PROCESS)).equals("eng"))
            FillForOthers.d.setTitle(" Card Transfer ");

        else
            FillForOthers.d.setTitle(" ካርድ ማስተላለፊያ ");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data!=null)
        {
            Uri cd = data.getData();
            Cursor c = managedQuery(cd,null,null,null,null);

            if(c.moveToFirst())
            {
                String ss= c.getString(c.getColumnIndexOrThrow
                        (ContactsContract.Contacts._ID));

                String hp = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if(hp.equalsIgnoreCase("1"))
                {
                    Cursor p = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = "+ss,null,null);
                    p.moveToFirst();
                    String b = p.getString(p.getColumnIndex("data1")).replace(" ", "").replace("-","");

                    String g = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    if(MainActivity.getLanguage().equals("eng"))
                        FillForOthers.d.setTitle("Transfer to "+g);

                    else
                        FillForOthers.d.setTitle("ለ "+g+" ካርድ አስተላልፍ ");

                    int x = 10;
                    if(b.charAt(0)=='+')
                        x=13;

                    else if(b.charAt(0)=='2')
                        x=12;

                    else if(b.charAt(0)=='0')
                        x=10;

                    FillForOthers.e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(x)});

                    FillForOthers.e.setText(b);

                }
            }
        }

    }
}
