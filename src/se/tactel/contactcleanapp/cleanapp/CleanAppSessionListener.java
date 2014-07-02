// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp;

import java.util.List;

public interface CleanAppSessionListener
{

    public abstract void onFinished(List list);

    public abstract void onMerged();

    public abstract void onProgress(int i);

    public abstract void onPurged();

    public abstract void onSessionAborted();

    public abstract void onStarted();
}
