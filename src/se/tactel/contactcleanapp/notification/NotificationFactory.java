// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import se.tactel.contactcleanapp.controller.MainActivity;

public class NotificationFactory
{

    public NotificationFactory()
    {
    }

    public static void showBackgroundScanNoCandidatesNotification(Context context, String s, String s1)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        showNotification(context, s, s1, PendingIntent.getActivity(context, 0, intent, 0x10000000), 1);
    }

    public static void showBackgroundScanNotification(Context context, String s, String s1)
    {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(0x10000000);
        intent.putExtra("notification_start", true);
        showNotification(context, s, s1, PendingIntent.getActivity(context, 0, intent, 0x10000000), 1);
    }

    public static void showNotification(Context context, String s, String s1, PendingIntent pendingintent, int i)
    {
        android.support.v4.app.NotificationCompat.Builder builder = (new android.support.v4.app.NotificationCompat.Builder(context)).setAutoCancel(true).setSmallIcon(0x7f02001e).setLargeIcon(((BitmapDrawable)context.getResources().getDrawable(0x7f020001)).getBitmap()).setContentTitle(s).setContentText(s1);
        if(pendingintent != null)
            builder.setContentIntent(pendingintent);
        ((NotificationManager)context.getSystemService("notification")).notify(i, builder.getNotification());
    }

    public static void showRateMeNotification(Context context)
    {
        String s = context.getResources().getString(0x7f050021);
        String s1 = context.getResources().getString(0x7f050022);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=se.tactel.contactcleanapp"));
        showNotification(context, s, s1, PendingIntent.getActivity(context, 0, intent, 0x10000000), 0);
    }

    private static final String AMAZON_APP_URI = "http://www.amazon.com/gp/mas/dl/android?p=se.tactel.contactcleanapp";
    private static final String ANDROID_APP_URI = "market://details?id=se.tactel.contactcleanapp";
    private static final int NOTIFICATION_PERIODIC = 1;
    private static final int NOTIFICATION_RATEME = 1;//set to 1
}
