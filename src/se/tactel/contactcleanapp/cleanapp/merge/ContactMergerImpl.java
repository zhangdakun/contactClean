// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp.merge;

import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import se.tactel.contactcleanapp.model.*;

// Referenced classes of package se.tactel.contactcleanapp.cleanapp.merge:
//            ContactMerger

public class ContactMergerImpl
    implements ContactMerger
{

    public ContactMergerImpl(ContactModelAPI contactmodelapi)
    {
        mContactModel = contactmodelapi;
    }

    public void merge(List list)
    {
        ContactData contactdata = new ContactData();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                merge(list, contactdata);
                return;
            }
            contactdata.mergeWith(((Contact)iterator.next()).getData());
        } while(true);
    }

    public void merge(List list, ContactData contactdata)
    {
//        if(list != null && list.size() > 0) goto _L2; else goto _L1
//_L1:
//        return;
//_L2:
//        if(contactdata != null)
//            try
//            {
//                mContactModel.updateContacts(list, contactdata);
//            }
//            catch(RemoteException remoteexception)
//            {
//                Log.d(TAG, "could not update contact");
//                remoteexception.printStackTrace();
//            }
//            catch(OperationApplicationException operationapplicationexception)
//            {
//                Log.d(TAG, "could not update contact");
//                operationapplicationexception.printStackTrace();
//            }
//            catch(UnsupportedOperationException unsupportedoperationexception)
//            {
//                Log.d(TAG, "could not update contact");
//                unsupportedoperationexception.printStackTrace();
//            }
//        mContactModel.merge(list);
//        if(true) goto _L1; else goto _L3
//_L3:
    	
    	if(list != null && list.size() > 0) {
          if(contactdata != null)
          try
          {
              mContactModel.updateContacts(list, contactdata);
          }
          catch(RemoteException remoteexception)
          {
              Log.d(TAG, "could not update contact");
              remoteexception.printStackTrace();
          }
          catch(OperationApplicationException operationapplicationexception)
          {
              Log.d(TAG, "could not update contact");
              operationapplicationexception.printStackTrace();
          }
          catch(UnsupportedOperationException unsupportedoperationexception)
          {
              Log.d(TAG, "could not update contact");
              unsupportedoperationexception.printStackTrace();
          }
          mContactModel.merge(list);
    	}
    }

    private static final String TAG =  ContactMergerImpl.class.getSimpleName();
    private ContactModelAPI mContactModel;

}
