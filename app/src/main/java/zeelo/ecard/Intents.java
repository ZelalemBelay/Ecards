package zeelo.ecard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Zeeloo on 8/9/2015.
 */

public class Intents
{

    public Intents()
    {}

    public Intent getKnowIntent()
    {
        String ussdCode = "*" + "804" + Uri.encode("#");
        return new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode));
    }

    public Intent getTeleCallIntent()
    {
        return new Intent("android.intent.action.CALL", Uri.parse("tel:" + "994"));
    }

}
