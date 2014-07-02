// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import java.util.List;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatch;
import se.tactel.contactcleanapp.notification.NotificationFactory;

// Referenced classes of package se.tactel.contactcleanapp.cleanapp:
//            CleanAppManager, CleanAppAdapter

public class CleanAppBackgroundService extends Service
{

    public CleanAppBackgroundService()
    {
    }

    private void showNotification(int i, int j)
    {
        if(j > 0)
            NotificationFactory.showBackgroundScanNotification(this, getResources().getString(0x7f050025), getResources().getString(0x7f050027));
        else
        if(i > 0)
        {
            String s = getResources().getString(0x7f050025);
            Resources resources = getResources();
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            NotificationFactory.showBackgroundScanNoCandidatesNotification(this, s, resources.getString(0x7f050028, aobj));
        } else
        {
            NotificationFactory.showBackgroundScanNoCandidatesNotification(this, getResources().getString(0x7f050026), getResources().getString(0x7f050029));
        }
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        CleanAppManager cleanappmanager = CleanAppManager.get(this);
        cleanappmanager.startSearch(this, new CleanAppAdapter());
        int k = cleanappmanager.getNbrAutoMerged();
        List list = cleanappmanager.getLastStoredSkipped(this);
        ContactMatch contactmatch = cleanappmanager.getCurrentMatch();
        int l = 0;
        do
        {
            if(contactmatch == null)
            {
                cleanappmanager.goToFirst();
                showNotification(k, l);
                return 2;
            }
            if(!list.contains(contactmatch.getIds()))
                l++;
            contactmatch = cleanappmanager.getNextMatch();
        } while(true);
    }
}
