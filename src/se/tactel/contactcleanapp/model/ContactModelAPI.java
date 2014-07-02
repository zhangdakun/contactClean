// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import android.content.OperationApplicationException;
import android.os.RemoteException;
import java.util.*;

// Referenced classes of package se.tactel.contactcleanapp.model:
//            Contact, ContactData

public interface ContactModelAPI
{

    public abstract boolean aggregationExists(List list);

    public abstract boolean aggregationExists(List list, Contact contact);

    public abstract boolean aggregationExists(Contact contact, Contact contact1);

    public abstract void delete(Contact contact);

    public abstract void delete(Contact contact, ArrayList arraylist);

    public abstract List getAggregatedContacts(List list);

    public abstract int getAggregatedNbrContacts();

    public abstract ArrayList getContactsWithMatchInfo();

    public abstract HashSet getGooglePlusContacts();

    public abstract void getPhotosForContacts(List list);

    public abstract boolean isGooglePlusContact(Contact contact);

    public abstract boolean isSameSuperId(List list);

    public abstract void merge(List list);

    public abstract void move(Contact contact, Contact contact1)
        throws RemoteException, OperationApplicationException;

    public abstract void move(Contact contact, Contact contact1, ArrayList arraylist)
        throws RemoteException, OperationApplicationException;

    public abstract ArrayList refreshData(List list);

    public abstract void removeMatchData(Contact contact)
        throws RemoteException, OperationApplicationException;

    public abstract void updateContact(long l, ContactData contactdata)
        throws RemoteException, OperationApplicationException;

    public abstract void updateContact(long l, ContactData contactdata, ArrayList arraylist);

    public abstract void updateContacts(List list, ContactData contactdata)
        throws RemoteException, OperationApplicationException;
}
