// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp.merge;

import java.util.List;
import se.tactel.contactcleanapp.model.ContactData;

public interface ContactMerger
{

    public abstract void merge(List list);

    public abstract void merge(List list, ContactData contactdata);
}
