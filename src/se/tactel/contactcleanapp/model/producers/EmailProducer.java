// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model.producers;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import se.tactel.contactcleanapp.model.EmailAddress;

// Referenced classes of package se.tactel.contactcleanapp.model.producers:
//            DeletingDataRowProducer

public class EmailProducer extends DeletingDataRowProducer
{

    public EmailProducer(long l, ArrayList arraylist)
    {
        super("vnd.android.cursor.item/email_v2", l);
        mAddresses = arraylist;
    }

    public void produce(ArrayList arraylist)
    {
        super.produce(arraylist);
//        if(mAddresses == null || mAddresses.size() <= 0) goto _L2; else goto _L1
//_L1:
//        ContentValues contentvalues;
//        Iterator iterator;
//        contentvalues = new ContentValues();
//        iterator = mAddresses.iterator();
//_L5:
//        if(iterator.hasNext()) goto _L3; else goto _L2
//_L2:
//        return;
//_L3:
//        EmailAddress emailaddress = (EmailAddress)iterator.next();
//        contentvalues.clear();
//        contentvalues.put("data1", emailaddress.getEmailAddress());
//        contentvalues.put("data2", Integer.valueOf(emailaddress.getType()));
//        contentvalues.put("data4", emailaddress.getDisplayName());
//        contentvalues.put("mimetype", "vnd.android.cursor.item/email_v2");
//        contentvalues.put("raw_contact_id", Long.valueOf(mId));
//        Log.d(TAG, (new StringBuilder("adding new insert of emailaddress = ")).append(emailaddress.getEmailAddress()).toString());
//        arraylist.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI).withValues(contentvalues).build());
//        if(true) goto _L5; else goto _L4
//_L4:
        
        if(mAddresses == null || mAddresses.size() <= 0) {
        	return ;
        }
        
      ContentValues contentvalues;
      Iterator iterator;
      contentvalues = new ContentValues();
      iterator = mAddresses.iterator();
      
      while (iterator.hasNext()) {
    	  EmailAddress emailaddress = (EmailAddress)iterator.next();
        contentvalues.clear();
        contentvalues.put("data1", emailaddress.getEmailAddress());
        contentvalues.put("data2", Integer.valueOf(emailaddress.getType()));
        contentvalues.put("data4", emailaddress.getDisplayName());
        contentvalues.put("mimetype", "vnd.android.cursor.item/email_v2");
        contentvalues.put("raw_contact_id", Long.valueOf(mId));
        Log.d(TAG, (new StringBuilder("adding new insert of emailaddress = ")).append(emailaddress.getEmailAddress()).toString());
        arraylist.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI).withValues(contentvalues).build());
      }
      
    }

    private static final String TAG =  EmailProducer.class.getSimpleName();
    private ArrayList mAddresses;

}
