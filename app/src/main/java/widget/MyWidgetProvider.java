package widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.zeelo.ecard.R;

import activities.CallMeActivity;
import activities.FillActivity;
import activities.FillForActivity;
import activities.MainActivity;
import zeelo.ecard.Intents;
import zeelo.ecard.Utility;

import java.util.Random;

public class MyWidgetProvider extends AppWidgetProvider {

  @SuppressLint("NewApi")
@Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) 
  {
	  final int N = appWidgetIds.length;

	  for(int i=0;i<N;i++)
	  {
		  int appWidgetId = appWidgetIds[i];

	      Intent ask = new Intents().getKnowIntent();

          PendingIntent pendingintent_ask = PendingIntent.getActivity(context, 0, ask, 0);

           RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
           R.layout.widget_layout);

           remoteViews.setOnClickPendingIntent(R.id.buttonT, PendingIntent.getActivity(context, 0,
                   new Intent(context, FillForActivity.class), 0));
          remoteViews.setOnClickPendingIntent(R.id.buttonC,
                  PendingIntent.getActivity(context, 0, new Intent(context, CallMeActivity.class), 0));
          remoteViews.setOnClickPendingIntent(R.id.buttonR, pendingintent_ask);
          remoteViews.setOnClickPendingIntent(R.id.buttonF,
                  PendingIntent.getActivity(context, 0, new Intent(context, FillActivity.class), 0));

          remoteViews.setOnClickPendingIntent(R.id.tel_lin,
                  PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0));

           appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

	   }

         ComponentName thisWidget = new ComponentName(context,
        MyWidgetProvider.class);
    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
    for (int  widgetId : allWidgetIds) {
      // create some random data
      int number = (new Random().nextInt(100));

    }

  }
}