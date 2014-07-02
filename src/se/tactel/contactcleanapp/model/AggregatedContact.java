// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package se.tactel.contactcleanapp.model:
//            Contact

public class AggregatedContact
{

    public AggregatedContact()
    {
        mContacts = new ArrayList();
    }

    public AggregatedContact(List list)
    {
        mContacts = list;
    }

    public void addAggregatedContact(Contact contact)
    {
        if(contact != null)
            mContacts.add(contact);
    }

    public List getContacts()
    {
        return mContacts;
    }

    public Contact getFirst()
    {
        Contact contact;
        if(mContacts == null || mContacts.size() <= 0)
            contact = null;
        else
            contact = (Contact)mContacts.get(0);
        return contact;
    }

    private List mContacts;
}
