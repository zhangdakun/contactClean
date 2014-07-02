// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp.match;

import java.util.List;

public interface ContactMatcher
{
    public static interface ContactMatcherListener
    {

        public abstract void onFinished();

        public abstract void onProgress(int i);

        public abstract void onSearchStarted();
    }


    public abstract List getContactMatches(List list);

    public abstract void setContactMatcherListener(ContactMatcherListener contactmatcherlistener);
}
