package activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import zeelo.ecard.Fill;
import zeelo.ecard.Utility;

/**
 * Created by Zeeloo on 8/11/2015.
 */
public class FillActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fill f =null;

        try
        {
            f= new Fill(this,null);
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
            startActivity(new Intent(FillActivity.this, MainActivity.class));
            startActivity(new Intent(FillActivity.this, FillActivity.class));
            finish();
        }

        if(Utility.getLanguage(getSharedPreferences("settings",  MODE_MULTI_PROCESS)).equals("eng"))
            Fill.d.setTitle("Balance Recharger");

        else
            Fill.d.setTitle("ካርድ መሙያ");
    }
}
