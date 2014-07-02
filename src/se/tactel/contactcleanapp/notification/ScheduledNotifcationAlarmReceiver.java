// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.notification;

import android.content.*;

// Referenced classes of package se.tactel.contactcleanapp.notification:
//            NotificationFactory

public class ScheduledNotifcationAlarmReceiver extends BroadcastReceiver
{

    public ScheduledNotifcationAlarmReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        NotificationFactory.showRateMeNotification(context);
    }
}
