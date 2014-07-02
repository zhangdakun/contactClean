// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.notification;

import android.content.*;
import android.util.Log;
import se.tactel.contactcleanapp.cleanapp.Application;
import se.tactel.contactcleanapp.cleanapp.CleanAppBackgroundService;

// Referenced classes of package se.tactel.contactcleanapp.notification:
//            ScheduledNotifcationAlarmReceiver

public class ScheduledPeriodicScanReceiver extends BroadcastReceiver
{

    public ScheduledPeriodicScanReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        if(!Application.get().isApplicationRunning())
            context.startService(new Intent(context, CleanAppBackgroundService.class));
        else
            Log.d(TAG, "application already running, not starting background service");
    }

    private static final String TAG = ScheduledNotifcationAlarmReceiver.class.getSimpleName();

}
