// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import android.content.*;
import android.database.Cursor;
import android.os.RemoteException;
import android.util.Log;
import java.util.*;

// Referenced classes of package se.tactel.contactcleanapp.model:
//            Contact

public class AggregationModel
{
    private class Aggregation
    {

        public long getFirst()
        {
            return mId1;
        }

        public long getSecond()
        {
            return mId2;
        }

        private long mId1;
        private long mId2;
//        final AggregationModel this$0;

        public Aggregation(long l, long l1)
        {
//            this$0 = AggregationModel.this;
//            super();
            mId1 = l;
            mId2 = l1;
        }
    }

    private class ContactsComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            return compare((Contact)obj, (Contact)obj1);
        }

        public int compare(Contact contact, Contact contact1)
        {
            return (int)(contact.getRawId() - contact1.getRawId());
        }

//        final AggregationModel this$0;

        private ContactsComparator()
        {
//            this$0 = AggregationModel.this;
            super();
        }

        ContactsComparator(ContactsComparator contactscomparator)
        {
            this();
        }
    }


    public AggregationModel(Context context)
    {
        mContext = context;
    }

    private void aggregate(long l, long l1, ArrayList arraylist)
    {
        if(!aggregationExists(l, l1))
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("type", Integer.valueOf(1));
            contentvalues.put("raw_contact_id1", Long.valueOf(l));
            contentvalues.put("raw_contact_id2", Long.valueOf(l1));
            arraylist.add(ContentProviderOperation.newUpdate(android.provider.ContactsContract.AggregationExceptions.CONTENT_URI).withValues(contentvalues).build());
            mAggregations.add(new Aggregation(l, l1));
            Log.d(TAG, (new StringBuilder("merged contacts with id1 = ")).append(l).append(" and id2 ").append(l1).toString());
        }
    }

    private void loadAggregations()
    {
        Cursor cursor;
        mAggregations = new ArrayList();
        ContentResolver contentresolver = mContext.getContentResolver();
        android.net.Uri uri = android.provider.ContactsContract.AggregationExceptions.CONTENT_URI;
        String as[] = AGGREGATION_PROJECTION;
        String as1[] = new String[1];
        as1[0] = "1";
        cursor = contentresolver.query(uri, as, "type=?", as1, null);
//        if(cursor == null) goto _L2; else goto _L1
//_L1:
//        boolean flag = cursor.moveToNext();
//        if(flag)
//            break MISSING_BLOCK_LABEL_84;
//        if(cursor != null)
//            cursor.close();
//_L2:
//        return;
//        mAggregations.add(new Aggregation(cursor.getInt(0), cursor.getInt(1)));
//          goto _L1
//        Exception exception;
//        exception;
//        if(cursor != null)
//            cursor.close();
//        throw exception;
        
        if(cursor != null ) {
        	for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
        	mAggregations.add(new Aggregation(cursor.getInt(0), cursor.getInt(1)));
        	}
        }
        
      if(cursor != null)
    	  cursor.close();
    }

    public void aggregate(List list)
    {
        ArrayList arraylist;
        int i;
        Collections.sort(list, new ContactsComparator(null));
        arraylist = new ArrayList();
        i = 0;
//_L3:
//        if(i < list.size()) goto _L2; else goto _L1
//_L1:
//        mContext.getContentResolver().applyBatch("com.android.contacts", arraylist);
//_L5:
//        return;
//_L2:
//        Contact contact;
//        int j;
//        contact = (Contact)list.get(i);
//        j = i + 1;
//_L4:
//label0:
//        {
//            if(j < list.size())
//                break label0;
//            i++;
//        }
//          goto _L3
//        Contact contact1 = (Contact)list.get(j);
//        aggregate(contact.getRawId(), contact1.getRawId(), arraylist);
//        j++;
//          goto _L4
//        RemoteException remoteexception;
//        remoteexception;
//        remoteexception.printStackTrace();
//          goto _L5
//        OperationApplicationException operationapplicationexception;
//        operationapplicationexception;
//        operationapplicationexception.printStackTrace();
//          goto _L5
        
        
        try {
			
			
			for(i=0;i < list.size();i++) {
		        Contact contact;
		        int j;
		        contact = (Contact)list.get(i);
		        j = i + 1;
		        for(j=i+1;j<list.size();j++) {
		          Contact contact1 = (Contact)list.get(j);
		          aggregate(contact.getRawId(), contact1.getRawId(), arraylist);
		        	
		        }
			}
			
			mContext.getContentResolver().applyBatch("com.android.contacts", arraylist);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public boolean aggregationExists(long l, long l1)
    {
        Iterator iterator;
        if(mAggregations == null)
            loadAggregations();
        iterator = mAggregations.iterator();
//_L2:
//        boolean flag;
//        if(!iterator.hasNext())
//        {
//            flag = false;
//        } else
//        {
//            Aggregation aggregation = (Aggregation)iterator.next();
//            if(aggregation.getFirst() != l || aggregation.getSecond() != l1)
//                continue; /* Loop/switch isn't completed */
//            flag = true;
//        }
//        return flag;
//        if(true) goto _L2; else goto _L1
//_L1:
        
        boolean flag = false;
        
        while(iterator.hasNext()) {
          Aggregation aggregation = (Aggregation)iterator.next();
          if(aggregation.getFirst() != l || aggregation.getSecond() != l1) {
              continue; /* Loop/switch isn't completed */
          }
              else {
            	  flag = true;
            	  
            	  break;
              }
        }
        
        
        return flag;
    }

    public boolean aggregationExists(List list)
    {
        int i;
        Collections.sort(list, new ContactsComparator(null));
//        i = 0;
//_L3:
//        if(i < list.size()) goto _L2; else goto _L1
//_L1:
//        boolean flag = true;
//_L4:
//        return flag;
//_L2:
//label0:
//        {
//            Contact contact = (Contact)list.get(i);
//            Contact contact1;
//            for(int j = i + 1; j < list.size(); j++)
//                break label0;
//
//            i++;
//        }
//          goto _L3
//label1:
//        {
//            contact1 = (Contact)list.get(j);
//            if(aggregationExists(contact.getRawId(), contact1.getRawId()))
//                break label1;
//            flag = false;
//        }
//          goto _L4
        
        boolean flag = false;
        
        for(i = 0;i < list.size();i++) {
          Contact contact = (Contact)list.get(i);
          Contact contact1;
          for(int j = i + 1; j < list.size(); j++) {
            contact1 = (Contact)list.get(j);
            if(aggregationExists(contact.getRawId(), contact1.getRawId())) {
            	flag = true;
            	return flag;
//            	break;
            } else {
            	flag = false;
            }
//                break label1;
//            flag = false;
          }
        }
         
        return flag;
    }

    public void reload()
    {
        loadAggregations();
    }

    private static final String AGGREGATION_PROJECTION[];
    private static final String AGGREGATION_SELECTION = "type=?";
    private static final String TAG = AggregationModel.class.getSimpleName();
    private ArrayList mAggregations;
    private Context mContext;

    static 
    {
        String as[] = new String[2];
        as[0] = "raw_contact_id1";
        as[1] = "raw_contact_id2";
        AGGREGATION_PROJECTION = as;
    }
}
