package widget;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.zeelo.ecard.R;

import activities.MainActivity;

/**
 * Foreground service. It creates a head view.
 * The pending intent allows to go back to the settings activity.
 */

public class NotService extends Service {

    private final static int FOREGROUND_ID = 999;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(this)
                    .setContentTitle("TeleCard Running..")
                  .setContentText("ካርድ ለመሙላት ለማወቅና ለመላክ ይህንን ይጫኑ!!")
//                    .setContentText("Card recharge, Tansfer and Banance Inquiry")
                    .setSmallIcon(R.drawable.tele)
                    .setContentIntent(pendingIntent)
                    .build();
        }

        startForeground(FOREGROUND_ID, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
    }

}
