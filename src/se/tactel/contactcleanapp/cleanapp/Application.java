// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp;

import java.util.HashMap;

public class Application
{

    private Application()
    {
        mActivityStates = new HashMap();
    }

    public static Application get()
    {
        if(sInstance == null)
            sInstance = new Application();
        return sInstance;
    }

    public boolean isApplicationRunning()
    {
        return mActivityStates.containsValue(Boolean.valueOf(true));
    }

    public void setRunning(String s, boolean flag)
    {
        mActivityStates.put(s, Boolean.valueOf(flag));
    }

    private static Application sInstance;
    private HashMap mActivityStates;
}
