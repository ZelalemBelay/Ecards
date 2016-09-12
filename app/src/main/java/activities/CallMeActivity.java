package activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import zeelo.ecard.Utility;
import zeelo.ecard.callMe;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.InputFilter;

/**
 * Created by Zeeloo on 8/11/2015.
 */

public class CallMeActivity extends Activity
{
   public static callMe c;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        activity = this;
        try
        {
            c = new callMe(this, null);
//            MainActivity.exitFlag = false;

        c.getD().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
          });
        }
        catch (Exception e)
            {
//            MainActivity.exitFlag = true;
                startActivity(new Intent(CallMeActivity.this, MainActivity.class));
                startActivity(new Intent(CallMeActivity.this, CallMeActivity.class));
                finish();
            }

        if(Utility.getLanguage(getSharedPreferences("settings",  MODE_MULTI_PROCESS)).equals("eng"))
            callMe.d.setTitle("Call me back request sender.");

        else
            callMe.d.setTitle(" መልሰው ይደውሉልኝ መላኪያ ");
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
                Cursor p = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, Phone.CONTACT_ID+" = "+ss,null,null);
                p.moveToFirst();
                String b = p.getString(p.getColumnIndex("data1")).replace(" ", "").replace("-","");

                String g = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if(MainActivity.getLanguage().equals("eng"))
                    callMe.d.setTitle("Call me back to "+g);

                else
                    callMe.d.setTitle("ለ "+g+" መልሰው ይደውሉልኝ ");

                int x = 10;
                if(b.charAt(0)=='+')
                    x=13;

                else if(b.charAt(0)=='2')
                    x=12;

                else if(b.charAt(0)=='0')
                    x=10;

                callMe.e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(x)});

                callMe.e.setText(b);

            }
        }
      }

    }
}
