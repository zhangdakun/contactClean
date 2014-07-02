// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package se.tactel.contactcleanapp.notification:
//            ScheduledPeriodicScanReceiver, ScheduledNotifcationAlarmReceiver

public class CleanAppNotificationManager
{

    public CleanAppNotificationManager(Context context)
    {
        mContext = context;
        mAlarm = (AlarmManager)context.getSystemService("alarm");
    }

    private void setPeriodicScan(long l, long l1)
    {
        PendingIntent pendingintent = PendingIntent.getBroadcast(mContext, 0, new Intent(mContext, ScheduledPeriodicScanReceiver.class), 0);
        mAlarm.setRepeating(0, l + System.currentTimeMillis(), l1, pendingintent);
    }

    public void cancelPeriodicScan()
    {
        PendingIntent pendingintent = PendingIntent.getBroadcast(mContext, 0, new Intent(mContext, ScheduledPeriodicScanReceiver.class), 0);
        mAlarm.cancel(pendingintent);
    }

    public void setNotificationAlarm(long l)
    {
        PendingIntent pendingintent = PendingIntent.getBroadcast(mContext, 0, new Intent(mContext, ScheduledNotifcationAlarmReceiver.class), 0);
        mAlarm.set(0, l + System.currentTimeMillis(), pendingintent);
    }

    public void setWeeklyScan()
    {
        setPeriodicScan(0x240c8400L, 0x240c8400L);
    }

    private AlarmManager mAlarm;
    private Context mContext;
}
