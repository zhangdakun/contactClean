// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.storage;

import java.util.List;

public interface Storage
{

    public abstract List getSkippedMatches();

    public abstract void storeSkippedMatches(List list);
}
