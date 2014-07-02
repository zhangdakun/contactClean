// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp.purge;

import java.util.List;

public interface Purger
{
    public static interface PurgeListener
    {

        public abstract void onError();

        public abstract void onFinished();

        public abstract void onProgress(int i);

        public abstract void onPurged();

        public abstract void onStarted();
    }


    public abstract void purge(List list);

    public abstract void purgeAll();

    public abstract void setPurgeListener(PurgeListener purgelistener);
}
