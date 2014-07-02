// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import android.content.*;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import se.tactel.contactcleanapp.model.producers.DataRowProducer;
import se.tactel.contactcleanapp.model.producers.EmailProducer;
import se.tactel.contactcleanapp.model.producers.PhoneNumberProducer;
import se.tactel.contactcleanapp.model.producers.PhotoProducer;
import se.tactel.contactcleanapp.model.producers.StructuredNameProducer;

// Referenced classes of package se.tactel.contactcleanapp.model:
//            ContactModelAPI, AggregationModel, Contact, ContactData, 
//            Name, AccountTypes, AggregatedContact

public class ContactModelImpl
    implements ContactModelAPI
{

    public ContactModelImpl(Context context)
    {
        mContext = context;
        mAggregationModel = new AggregationModel(context);
    }

    private HashSet getContactsWithGroup()
    {
        Cursor cursor;
        HashSet hashset;
        ContentResolver contentresolver = mContext.getContentResolver();
        Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
        String as[] = new String[2];
        as[0] = "_id";
        as[1] = "data2";
        String as1[] = new String[1];
        as1[0] = "com.google";
        cursor = contentresolver.query(uri, as, "deleted=0 AND account_type=? AND mimetype='vnd.android.cursor.item/group_membership'", as1, "_id ASC");
        hashset = new HashSet();
//        if(cursor == null) goto _L2; else goto _L1
//_L1:
//        if(cursor.getCount() <= 0) goto _L4; else goto _L3
//_L3:
//        long l = -1L;
//_L7:
//        boolean flag = cursor.moveToNext();
//        if(flag) goto _L5; else goto _L4
//_L4:
//        cursor.close();
//_L2:
//        return hashset;
//_L5:
//        long l1 = cursor.getLong(0);
//        if(l1 == l) goto _L7; else goto _L6
//_L6:
//        hashset.add(Long.valueOf(l1));
//        l = l1;
//          goto _L7
//        Exception exception;
//        exception;
//        cursor.close();
//        throw exception;
        
        if(cursor == null) {
        	 return hashset;
        } else {
        	 if(cursor.getCount() <= 0) {
        		 cursor.close();
        	 } else {
        		 long l = -1L;
        		 for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
        			 long l1 = cursor.getLong(0);
        			 if(l1 == l) {
        				 
        			 }else {
        				 hashset.add(Long.valueOf(l1));
        				 l = l1;
        			 }
        		 }
        	 }
        }
        
        return hashset;
    }

    private String getWhereIdInBase(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("(");
        int j = 0;
        do
        {
            if(j >= i)
            {
                stringbuilder.append(")");
                return stringbuilder.toString();
            }
            stringbuilder.append("?");
            if(j != i - 1)
                stringbuilder.append(",");
            j++;
        } while(true);
    }

    private ArrayList populateContacts(Cursor cursor)
    {
        ArrayList arraylist = new ArrayList();
//        if(cursor == null) goto _L2; else goto _L1
//_L1:
//        long l;
//        ContactData contactdata;
//        Contact contact1;
//        l = -1L;
//        contactdata = null;
//        contact1 = null;
//_L9:
//        boolean flag = cursor.moveToNext();
//        if(flag) goto _L4; else goto _L3
//_L3:
//        cursor.close();
//          goto _L2
//_L4:
//        Contact contact2;
//        l1 = cursor.getLong(0);
//        if(l1 <= l)
//            break MISSING_BLOCK_LABEL_601;
//        contact2 = new Contact(l1, cursor.getLong(1));
//        ContactData contactdata1 = new ContactData();
//        contactdata1.setAccountName(cursor.getString(8));
//        contactdata1.setAccountType(cursor.getString(9));
//        contact2.setData(contactdata1);
//        arraylist.add(contact2);
//        l = l1;
//_L16:
//        s = cursor.getString(2);
//        if(TextUtils.isEmpty(s)) goto _L6; else goto _L5
//_L5:
//        if(!s.equals("vnd.android.cursor.item/name")) goto _L8; else goto _L7
//_L7:
//        String s4 = trim(cursor.getString(3));
//        String s5 = trim(cursor.getString(4));
//        String s6 = trim(cursor.getString(5));
//        String s7 = trim(cursor.getString(7));
//        Name name = new Name(s4, s5, s6, s7);
//        contactdata1.setName(name);
//        contactdata = contactdata1;
//        contact1 = contact2;
//          goto _L9
//_L8:
//        if(!s.equals("vnd.android.cursor.item/phone_v2")) goto _L11; else goto _L10
//_L10:
//        s3 = trim(cursor.getString(3));
//        j = cursor.getInt(4);
//        if(TextUtils.isEmpty(s3)) goto _L6; else goto _L12
//_L12:
//        contactdata1.addPhoneNumber(s3, j);
//        contactdata = contactdata1;
//        contact1 = contact2;
//          goto _L9
//_L11:
//        if(s.equals("vnd.android.cursor.item/email_v2"))
//        {
//            String s1 = trim(cursor.getString(3));
//            int i = cursor.getInt(4);
//            String s2 = trim(cursor.getString(6));
//            if(!TextUtils.isEmpty(s1))
//                contactdata1.addEmail(s2, s1, i);
//        }
//_L6:
//        contactdata = contactdata1;
//        contact1 = contact2;
//          goto _L9
//        Exception exception1;
//        exception1;
//        contactdata;
//        contact1;
//_L15:
//        Log.w(TAG, (new StringBuilder("Querying contact data failed with reason, ")).append(exception1.getMessage()).toString());
//        exception1.printStackTrace();
//        cursor.close();
//          goto _L2
//        Exception exception;
//        exception;
//        contactdata;
//_L14:
//        cursor.close();
//        throw exception;
//_L2:
//        ArrayList arraylist1 = new ArrayList();
//        HashSet hashset = getGooglePlusContacts();
//        HashSet hashset1 = getContactsWithGroup();
//        Iterator iterator = arraylist.iterator();
//        do
//        {
//            long l1;
//            String s;
//            String s3;
//            int j;
//            if(!iterator.hasNext())
//                return arraylist1;
//            Contact contact = (Contact)iterator.next();
//            Long long1 = Long.valueOf(contact.getRawId());
//            if(hashset.contains(long1))
//                contact.getData().setGooglePlusContact(true);
//            if(validContact(contact) && (!contact.getData().getAccountType().equalsIgnoreCase("com.google") || contact.getData().isGooglePlusContact() || hashset1.contains(long1)))
//                arraylist1.add(contact);
//        } while(true);
//        exception;
//        if(true) goto _L14; else goto _L13
//_L13:
//        exception1;
//        contactdata;
//          goto _L15
//        exception1;
//          goto _L15
//        contactdata1 = contactdata;
//        contact2 = contact1;
//          goto _L16
        ArrayList arraylist1 = new ArrayList();
        if(cursor != null) {
          

//        } else {
//          long l;
          ContactData contactdata;
          Contact contact1;
          long l = -1L;
          contactdata = null;
          contact1 = null;
        	for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
//	              Contact contact2 = null;
//	              ContactData contactdata1 = null;
	              long  l1 = cursor.getLong(0);
	              
//	              contact2 = new Contact(l1, cursor.getLong(1));
//	              contactdata1 = new ContactData();
//	              contactdata1.setAccountName(cursor.getString(8));
//	              contactdata1.setAccountType(cursor.getString(9));
//	              contact2.setData(contactdata1);
//	              arraylist.add(contact2);
	              
	              if(l1 <= l) {
//	                  break MISSING_BLOCK_LABEL_601;
//	            	  continue;
//		              contact2 = new Contact(l1, cursor.getLong(1));
//		              contactdata1 = new ContactData();
//		              contactdata1.setAccountName(cursor.getString(8));
//		              contactdata1.setAccountType(cursor.getString(9));
//		              contact2.setData(contactdata1);
//		              arraylist.add(contact2);
//		              
//		              l = l1;
	              } 
	              else 
	              {
		              contact1 = new Contact(l1, cursor.getLong(1));
		              contactdata = new ContactData();
		              contactdata.setAccountName(cursor.getString(8));
		              contactdata.setAccountType(cursor.getString(9));
		              contact1.setData(contactdata);
		              arraylist.add(contact1);
		              
		              l = l1;
	              }
	              
	              if( null == contact1  || null == contactdata) {
		              contact1 = new Contact(l1, cursor.getLong(1));
		              contactdata = new ContactData();
		              contactdata.setAccountName(cursor.getString(8));
		              contactdata.setAccountType(cursor.getString(9));
		              contact1.setData(contactdata);
		              arraylist.add(contact1);
		              
		              l = l1;
	              }
//	              contact2 = new Contact(l1, cursor.getLong(1));
//	              ContactData contactdata1 = new ContactData();
//	              contactdata1.setAccountName(cursor.getString(8));
//	              contactdata1.setAccountType(cursor.getString(9));
//	              contact2.setData(contactdata1);
//	              arraylist.add(contact2);
//	              l = l1;
	              
		            String s = cursor.getString(2);
		            if(TextUtils.isEmpty(s)) {//goto _L6; else goto _L5
//			              contactdata = contactdata1;
//			              contact1 = contact2;
		            } else {
		            	if(s.equals("vnd.android.cursor.item/name")) {
			                  String s4 = trim(cursor.getString(3));
			                  String s5 = trim(cursor.getString(4));
			                  String s6 = trim(cursor.getString(5));
			                  String s7 = trim(cursor.getString(7));
			                  Name name = new Name(s4, s5, s6, s7);
			                  contactdata.setName(name);
//			                  contactdata = contactdata1;
//			                  contact1 = contact2;
			            } else if(s.equals("vnd.android.cursor.item/phone_v2")) {
			              String s3 = trim(cursor.getString(3));
			              int j = cursor.getInt(4);
			              if(TextUtils.isEmpty(s3)) {//goto _L6; else goto _L12
//				              contactdata = contactdata1;
//				              contact1 = contact2;
			              } else {
			                contactdata.addPhoneNumber(s3, j);
//			                contactdata = contactdata1;
//			                contact1 = contact2;
			              }
			            } else if(s.equals("vnd.android.cursor.item/email_v2")) {
			              String s1 = trim(cursor.getString(3));
			              int i = cursor.getInt(4);
			              String s2 = trim(cursor.getString(6));
			              if(!TextUtils.isEmpty(s1))
			                  contactdata.addEmail(s2, s1, i);
			              
//			              contactdata = contactdata1;
//			              contact1 = contact2;
			            }
		            	
//		                  contactdata = contactdata1;
//		                  contact1 = contact2;
		            	
		            }
	              
//	              }
        	}
        	
        	cursor.close();
        }
        HashSet hashset = getGooglePlusContacts();
        HashSet hashset1 = getContactsWithGroup();
        Iterator iterator = arraylist.iterator();
        do
        {
            long l1;
            String s;
            String s3;
            int j;
            if(!iterator.hasNext())
                return arraylist1;
            Contact contact = (Contact)iterator.next();
            Long long1 = Long.valueOf(contact.getRawId());
            if(hashset.contains(long1))
                contact.getData().setGooglePlusContact(true);
            if(validContact(contact) && (!contact.getData().getAccountType().equalsIgnoreCase("com.google") || contact.getData().isGooglePlusContact() || hashset1.contains(long1)))
                arraylist1.add(contact);
        } while(true);
        
//        return arraylist1;
    }

    private String trim(String s)
    {
        if(s != null)
            s = s.trim();
        return s;
    }

    private boolean validContact(Contact contact)
    {
        boolean flag;
        flag = false;
//        break MISSING_BLOCK_LABEL_2;
        if(contact != null && contact.getData().getName() != null)
        {
            Iterator iterator = AccountTypes.simTypes().iterator();
            do
            {
                if(iterator.hasNext())
                    continue;
                flag = true;
                if(true)
                    break;
            } while(!((String)iterator.next()).equals(contact.getData().getAccountType()));
        }
        return flag;
    }

    public boolean aggregationExists(List list)
    {
        return mAggregationModel.aggregationExists(list);
    }

    public boolean aggregationExists(List list, Contact contact)
    {
        ArrayList arraylist = new ArrayList(list);
        arraylist.add(contact);
        boolean flag;
        if(!isSameSuperId(arraylist) && !aggregationExists(((List) (arraylist))))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public boolean aggregationExists(Contact contact, Contact contact1)
    {
        boolean flag;
        if(contact.getAggregatedId() != contact1.getAggregatedId() && !mAggregationModel.aggregationExists(contact.getRawId(), contact1.getRawId()))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public void delete(Contact contact)
    {
        int i = mContext.getContentResolver().delete(Uri.withAppendedPath(android.provider.ContactsContract.RawContacts.CONTENT_URI, (new StringBuilder(String.valueOf(contact.getRawId()))).toString()), null, null);
        Log.d(TAG, (new StringBuilder("delete() deleted ")).append(i).append(" rows").toString());
    }

    public void delete(Contact contact, ArrayList arraylist)
    {
        arraylist.add(ContentProviderOperation.newDelete(Uri.withAppendedPath(android.provider.ContactsContract.RawContacts.CONTENT_URI, (new StringBuilder(String.valueOf(contact.getRawId()))).toString())).build());
    }

    public List getAggregatedContacts(List list)
    {
        ArrayList arraylist;
        Iterator iterator;
        arraylist = new ArrayList();
        iterator = list.iterator();
//_L3:
//        Contact contact;
//        boolean flag;
//        Iterator iterator1;
//        if(!iterator.hasNext())
//            return arraylist;
//        contact = (Contact)iterator.next();
//        flag = false;
//        iterator1 = arraylist.iterator();
//_L5:
//        if(iterator1.hasNext()) goto _L2; else goto _L1
//_L1:
//        if(!flag)
//        {
//            AggregatedContact aggregatedcontact1 = new AggregatedContact();
//            aggregatedcontact1.addAggregatedContact(contact);
//            arraylist.add(aggregatedcontact1);
//        }
//          goto _L3
//_L2:
//        AggregatedContact aggregatedcontact = (AggregatedContact)iterator1.next();
//        if(!aggregationExists(aggregatedcontact.getContacts(), contact)) goto _L5; else goto _L4
//_L4:
//        aggregatedcontact.addAggregatedContact(contact);
//        flag = true;
//          goto _L1
        
        
      Contact contact;
      boolean flag;
      Iterator iterator1;
      
      while(iterator.hasNext()) {
    	  contact = (Contact)iterator.next();
        flag = false;
        iterator1 = arraylist.iterator();
        
        while(iterator1.hasNext()) {
          AggregatedContact aggregatedcontact = (AggregatedContact)iterator1.next();
          if(aggregationExists(aggregatedcontact.getContacts(), contact)){// goto _L5; else goto _L4
            aggregatedcontact.addAggregatedContact(contact);
            flag = true;
            break;
          }
        }
        
	      if(!flag)
	      {
	          AggregatedContact aggregatedcontact1 = new AggregatedContact();
	          aggregatedcontact1.addAggregatedContact(contact);
	          arraylist.add(aggregatedcontact1);
	      }
      }
      
      
      return arraylist;
    }

    public int getAggregatedNbrContacts()
    {
        int i;
        Cursor cursor;
        i = 0;
        cursor = null;
        int j;
        cursor = mContext.getContentResolver().query(android.provider.ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if(cursor == null) {
//            break MISSING_BLOCK_LABEL_37;
        	return 0;
        }
        j = cursor.getCount();
        i = j;
        
        
        if(cursor != null)
            cursor.close();
        
        return i;
//        Exception exception;
//        exception;
//        if(cursor != null)
//            cursor.close();
//        throw exception;
    }

    public ArrayList getContactsWithMatchInfo()
    {
        mAggregationModel.reload();
        return populateContacts(mContext.getContentResolver().query(android.provider.ContactsContract.RawContactsEntity.CONTENT_URI, PROJECTION, "deleted=0 AND mimetype IN ('vnd.android.cursor.item/name','vnd.android.cursor.item/phone_v2','vnd.android.cursor.item/email_v2')", null, "_id ASC"));
    }

    public HashSet getGooglePlusContacts()
    {
        HashSet hashset = new HashSet();
//        if(android.os.Build.VERSION.SDK_INT < 14) goto _L2; else goto _L1
//_L1:
//        Cursor cursor = null;
//        ContentResolver contentresolver = mContext.getContentResolver();
//        Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
//        String as[] = new String[2];
//        as[0] = "_id";
//        as[1] = "data_set";
//        String as1[] = new String[2];
//        as1[0] = "plus";
//        as1[1] = "com.google";
//        cursor = contentresolver.query(uri, as, "deleted=0 AND data_set=? AND account_type=?", as1, "_id ASC");
//_L7:
//        if(cursor == null) goto _L4; else goto _L3
//_L3:
//        boolean flag = cursor.moveToNext();
//        if(flag) goto _L5; else goto _L4
//_L4:
//        if(cursor != null)
//            cursor.close();
//_L2:
//        return hashset;
//_L5:
//        if(!"plus".equalsIgnoreCase(cursor.getString(1))) goto _L7; else goto _L6
//_L6:
//        hashset.add(Long.valueOf(cursor.getLong(0)));
//          goto _L7
//        Exception exception;
//        exception;
//        if(cursor != null)
//            cursor.close();
//        throw exception;
        
        if(android.os.Build.VERSION.SDK_INT < 14) {
        	return hashset;
        } else {
          Cursor cursor = null;
          ContentResolver contentresolver = mContext.getContentResolver();
          Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
          String as[] = new String[2];
          as[0] = "_id";
          as[1] = "data_set";
          String as1[] = new String[2];
          as1[0] = "plus";
          as1[1] = "com.google";
          cursor = contentresolver.query(uri, as, "deleted=0 AND data_set=? AND account_type=?", as1, "_id ASC");
          if( null != cursor) {
          for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
        	  if("plus".equalsIgnoreCase(cursor.getString(1))) {
        		  hashset.add(Long.valueOf(cursor.getLong(0)));
        	  }
          }
          cursor.close();
          }
        }
        
        return hashset;
    }

    public void getPhotosForContacts(List list)
    {
        HashMap hashmap;
        int i;
        String as[];
        int j;
        hashmap = new HashMap();
        i = list.size();
        as = new String[i + 1];
        as[0] = "vnd.android.cursor.item/photo";
        j = 0;
//_L5:
//        if(j < list.size()) goto _L2; else goto _L1
//_L1:
//        Cursor cursor;
//        String s1 = (new StringBuilder("mimetype=? AND _id IN ")).append(getWhereIdInBase(i)).toString();
//        ContentResolver contentresolver = mContext.getContentResolver();
//        Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
//        String as1[] = new String[2];
//        as1[0] = "_id";
//        as1[1] = "data15";
//        cursor = contentresolver.query(uri, as1, s1, as, null);
//        if(cursor == null)
//            break MISSING_BLOCK_LABEL_262;
//_L6:
//        boolean flag = cursor.moveToNext();
//        if(flag) goto _L4; else goto _L3
//_L3:
//        if(cursor != null)
//            cursor.close();
//_L7:
//        return;
//_L2:
//        Contact contact = (Contact)list.get(j);
//        String s = (new StringBuilder(String.valueOf(contact.getRawId()))).toString();
//        hashmap.put(s, contact);
//        as[j + 1] = s;
//        j++;
//          goto _L5
//_L4:
//        Contact contact1 = (Contact)hashmap.get(cursor.getString(0));
//        if(contact1 != null)
//            contact1.getData().setPhoto(cursor.getBlob(1));
//          goto _L6
//        Exception exception;
//        exception;
//        if(cursor != null)
//            cursor.close();
//        throw exception;
//        Log.d(TAG, "couldn't find any images");
//          goto _L7
        
        for(j=0;j < list.size();j++)  {
          Contact contact = (Contact)list.get(j);
          String s = (new StringBuilder(String.valueOf(contact.getRawId()))).toString();
          hashmap.put(s, contact);
          as[j + 1] = s;
        }
        
      Cursor cursor;
      String s1 = (new StringBuilder("mimetype=? AND _id IN ")).append(getWhereIdInBase(i)).toString();
      ContentResolver contentresolver = mContext.getContentResolver();
      Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
      String as1[] = new String[2];
      as1[0] = "_id";
      as1[1] = "data15";
      cursor = contentresolver.query(uri, as1, s1, as, null);
//      if(cursor == null)
//          break MISSING_BLOCK_LABEL_262;
      if(null != cursor) {
    	  for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
            Contact contact1 = (Contact)hashmap.get(cursor.getString(0));
            if(contact1 != null)
                contact1.getData().setPhoto(cursor.getBlob(1));
    	  }
    	  
    	  
    	  cursor.close();
      }
    }

    public boolean isGooglePlusContact(Contact contact)
    {
//        if(contact != null) goto _L2; else goto _L1
//_L1:
//        boolean flag = false;
//_L4:
//        return flag;
//_L2:
//        Cursor cursor;
//        if(!contact.getData().getAccountType().equalsIgnoreCase("com.google") || android.os.Build.VERSION.SDK_INT < 14)
//            break MISSING_BLOCK_LABEL_176;
//        cursor = null;
//        boolean flag1;
//        ContentResolver contentresolver = mContext.getContentResolver();
//        Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
//        String as[] = new String[1];
//        as[0] = "data_set";
//        String as1[] = new String[1];
//        as1[0] = (new StringBuilder(String.valueOf(contact.getRawId()))).toString();
//        cursor = contentresolver.query(uri, as, "_id=?", as1, null);
//        if(cursor == null || !cursor.moveToFirst())
//            break MISSING_BLOCK_LABEL_166;
//        flag1 = "plus".equalsIgnoreCase(cursor.getString(0));
//        if(flag1)
//        {
//            if(cursor != null)
//                cursor.close();
//            flag = true;
//            continue; /* Loop/switch isn't completed */
//        }
//        break MISSING_BLOCK_LABEL_166;
//        Exception exception;
//        exception;
//        if(cursor != null)
//            cursor.close();
//        throw exception;
//        if(cursor != null)
//            cursor.close();
//        flag = false;
//        if(true) goto _L4; else goto _L3
//_L3:
    	
    	 boolean flag = false;
    	 
    	 if(contact != null) {
           Cursor cursor;
           if(!contact.getData().getAccountType().equalsIgnoreCase("com.google") 
        		   || android.os.Build.VERSION.SDK_INT < 14) {
//               break MISSING_BLOCK_LABEL_176;
           } else {
             ContentResolver contentresolver = mContext.getContentResolver();
             Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
             String as[] = new String[1];
             as[0] = "data_set";
             String as1[] = new String[1];
             as1[0] = (new StringBuilder(String.valueOf(contact.getRawId()))).toString();
             cursor = contentresolver.query(uri, as, "_id=?", as1, null);
             
             if(null != cursor) {
            	 for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
            		 if("plus".equalsIgnoreCase(cursor.getString(0))) {
            			 flag = true;
            			 break;
            		 }
            	 }
            	 
            	 cursor.close();
             }
           }
               
    	 }
    	 
    	 
    	 return flag;
    }

    public boolean isSameSuperId(List list)
    {
        long l;
        Iterator iterator;
        l = ((Contact)list.get(0)).getAggregatedId();
        iterator = list.iterator();
//_L2:
//        boolean flag;
//        if(iterator.hasNext())
//            continue; /* Loop/switch isn't completed */
//        flag = true;
//_L3:
//        return flag;
//        if(((Contact)iterator.next()).getAggregatedId() == l) goto _L2; else goto _L1
//_L1:
//        flag = false;
//          goto _L3
//        if(true) goto _L2; else goto _L4
//_L4:
        
        boolean flag = false;
        
        while(iterator.hasNext()) {
        	if(((Contact)iterator.next()).getAggregatedId() == l) {
        		flag = true;
        		break;
        	}
        }
        
        return flag;
    }

    public void merge(List list)
    {
        mAggregationModel.aggregate(list);
    }

    public void move(Contact contact, Contact contact1)
        throws RemoteException, OperationApplicationException
    {
        ArrayList arraylist = new ArrayList();
        move(contact, contact1, arraylist);
        android.content.ContentProviderResult acontentproviderresult[] = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist);
        Log.d(TAG, (new StringBuilder("move() updated ")).append(acontentproviderresult.length).append(" rows").toString());
    }

    public void move(Contact contact, Contact contact1, ArrayList arraylist)
        throws RemoteException, OperationApplicationException
    {
        ArrayList arraylist1;
        ContentProviderClient contentproviderclient;
        Cursor cursor;
        arraylist1 = new ArrayList();
        contentproviderclient = mContext.getContentResolver().acquireContentProviderClient("com.android.contacts");
        Uri uri = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI;
        String as[] = PROJECTION_ALL_DATA;
        String as1[] = new String[1];
        as1[0] = (new StringBuilder(String.valueOf(contact.getRawId()))).toString();
        cursor = contentproviderclient.query(uri, as, "_id=? AND mimetype NOT IN ('vnd.android.cursor.item/name','vnd.android.cursor.item/phone_v2','vnd.android.cursor.item/email_v2','vnd.android.cursor.item/photo')", as1, null);
//        if(cursor == null) goto _L2; else goto _L1
//_L1:
//        AbstractWindowedCursor abstractwindowedcursor = (AbstractWindowedCursor)cursor;
//_L8:
//        boolean flag = abstractwindowedcursor.moveToNext();
//        if(flag) goto _L4; else goto _L3
//_L3:
//        if(cursor != null)
//            cursor.close();
//          goto _L2
//_L4:
//        contentvalues1 = new ContentValues();
//        contentvalues1.put("mimetype", abstractwindowedcursor.getString(0));
//        contentvalues1.put("is_primary", Integer.valueOf(abstractwindowedcursor.getInt(1)));
//        contentvalues1.put("is_super_primary", Integer.valueOf(abstractwindowedcursor.getInt(2)));
//        if(!abstractwindowedcursor.isNull(3))
//            contentvalues1.put("group_sourceid", abstractwindowedcursor.getString(3));
//          goto _L5
//_L9:
//        int i;
//        if(i < DATA_KEYS.length) goto _L7; else goto _L6
//_L6:
//        arraylist1.add(contentvalues1);
//          goto _L8
//        exception1;
//        Log.d(TAG, (new StringBuilder("could not read data row, ")).append(exception1.getMessage()).toString());
//        if(cursor != null)
//            cursor.close();
//          goto _L2
//_L7:
//        j = i + 4;
//        s = DATA_KEYS[i];
//        if(abstractwindowedcursor.isNull(j))
//            break MISSING_BLOCK_LABEL_534;
//        if(abstractwindowedcursor.isLong(j))
//        {
//            contentvalues1.put(s, Long.valueOf(abstractwindowedcursor.getLong(j)));
//            break MISSING_BLOCK_LABEL_534;
//        }
//        if(abstractwindowedcursor.isFloat(j))
//        {
//            contentvalues1.put(s, Float.valueOf(abstractwindowedcursor.getFloat(j)));
//            break MISSING_BLOCK_LABEL_534;
//        }
//        break MISSING_BLOCK_LABEL_382;
//        exception;
//        if(cursor != null)
//            cursor.close();
//        throw exception;
//        if(abstractwindowedcursor.isString(j))
//            contentvalues1.put(s, abstractwindowedcursor.getString(j));
//        else
//        if(abstractwindowedcursor.isBlob(j))
//            contentvalues1.put(s, abstractwindowedcursor.getBlob(j));
//        break MISSING_BLOCK_LABEL_534;
//_L2:
//        contentproviderclient.release();
//        Iterator iterator = arraylist1.iterator();
//        do
//        {
//            Exception exception;
//            Exception exception1;
//            ContentValues contentvalues1;
//            int j;
//            String s;
//            if(!iterator.hasNext())
//                return;
//            ContentValues contentvalues = (ContentValues)iterator.next();
//            if(contentvalues.get("mimetype").equals("vnd.android.cursor.item/group_membership") && contentvalues.containsKey("data1") && contentvalues.containsKey("group_sourceid"))
//                contentvalues.remove("group_sourceid");
//            contentvalues.put("raw_contact_id", Long.valueOf(contact1.getRawId()));
//            arraylist.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI).withValues(contentvalues).build());
//        } while(true);
//_L5:
//        i = 0;
//          goto _L9
//        i++;
//          goto _L9
        
        if(cursor != null) {
        	AbstractWindowedCursor abstractwindowedcursor = (AbstractWindowedCursor)cursor;
        	
        	for(abstractwindowedcursor.moveToFirst();!abstractwindowedcursor.isAfterLast();abstractwindowedcursor.
        			moveToNext()) {
        	  ContentValues contentvalues1 = new ContentValues();
              contentvalues1.put("mimetype", abstractwindowedcursor.getString(0));
              contentvalues1.put("is_primary", Integer.valueOf(abstractwindowedcursor.getInt(1)));
              contentvalues1.put("is_super_primary", Integer.valueOf(abstractwindowedcursor.getInt(2)));
              if(!abstractwindowedcursor.isNull(3))
                  contentvalues1.put("group_sourceid", abstractwindowedcursor.getString(3));
              
              for(int i=0;i < DATA_KEYS.length;i++) {
               int j = i + 4;
               String s = DATA_KEYS[i];
                if(abstractwindowedcursor.isNull(j)) {
//                    break MISSING_BLOCK_LABEL_534;
                    continue;
                }
                if(abstractwindowedcursor.isLong(j))
                {
                    contentvalues1.put(s, Long.valueOf(abstractwindowedcursor.getLong(j)));
//                    break MISSING_BLOCK_LABEL_534;
                    continue;
                }
                if(abstractwindowedcursor.isFloat(j))
                {
                    contentvalues1.put(s, Float.valueOf(abstractwindowedcursor.getFloat(j)));
//                    break MISSING_BLOCK_LABEL_534;
                    continue;
                }
//                break MISSING_BLOCK_LABEL_382;
              }
              arraylist1.add(contentvalues1);
        	}
        	cursor.close();	
        }
        
        
      contentproviderclient.release();
      Iterator iterator = arraylist1.iterator();
      do
      {
          Exception exception;
          Exception exception1;
          ContentValues contentvalues1;
          int j;
          String s;
          if(!iterator.hasNext())
              return;
          ContentValues contentvalues = (ContentValues)iterator.next();
          if(contentvalues.get("mimetype").equals("vnd.android.cursor.item/group_membership") && contentvalues.containsKey("data1") && contentvalues.containsKey("group_sourceid"))
              contentvalues.remove("group_sourceid");
          contentvalues.put("raw_contact_id", Long.valueOf(contact1.getRawId()));
          arraylist.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI).withValues(contentvalues).build());
      } while(true);
      
    }

    public ArrayList refreshData(List list)
    {
        int i = list.size();
        String as[] = new String[i];
        int j = 0;
        do
        {
            if(j >= list.size())
            {
                String s = (new StringBuilder("deleted=0 AND mimetype IN ('vnd.android.cursor.item/name','vnd.android.cursor.item/phone_v2','vnd.android.cursor.item/email_v2') AND _id IN ")).append(getWhereIdInBase(i)).toString();
                return populateContacts(mContext.getContentResolver().query(android.provider.ContactsContract.RawContactsEntity.CONTENT_URI, PROJECTION, s, as, "_id ASC"));
            }
            as[j] = (new StringBuilder(String.valueOf(((Contact)list.get(j)).getRawId()))).toString();
            j++;
        } while(true);
    }

    public void removeMatchData(Contact contact)
        throws RemoteException, OperationApplicationException
    {
        updateContact(contact.getRawId(), new ContactData());
    }

    public void updateContact(long l, ContactData contactdata)
        throws RemoteException, OperationApplicationException
    {
        ArrayList arraylist = new ArrayList();
        updateContact(l, contactdata, arraylist);
        android.content.ContentProviderResult acontentproviderresult[] = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist);
        Log.d(TAG, (new StringBuilder("updateContact() updated ")).append(acontentproviderresult.length).append(" operations").toString());
    }

    public void updateContact(long l, ContactData contactdata, ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        arraylist1.add(new EmailProducer(l, contactdata.getEmailAddresses()));
        arraylist1.add(new PhoneNumberProducer(l, contactdata.getPhoneNumbers()));
        arraylist1.add(new StructuredNameProducer(l, contactdata.getName()));
        arraylist1.add(new PhotoProducer(l, contactdata.getPhoto()));
        Iterator iterator = arraylist1.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            ((DataRowProducer)iterator.next()).produce(arraylist);
        } while(true);
    }

    public void updateContacts(List list, ContactData contactdata)
        throws RemoteException, OperationApplicationException
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                android.content.ContentProviderResult acontentproviderresult[] = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist);
                Log.d(TAG, (new StringBuilder("updateContacts() updated ")).append(acontentproviderresult.length).append(" operations").toString());
                return;
            }
            updateContact(((Contact)iterator.next()).getRawId(), contactdata, arraylist);
        } while(true);
    }

    private static final int ACCOUNT_NAME = 8;
    private static final int ACCOUNT_TYPE = 9;
    private static final int COLUMN_DATA1 = 4;
    private static final int COLUMN_GROUP_SOURCE_ID = 3;
    private static final int CONTACT_ID = 1;
    private static final String DATA_KEYS[];
    private static final int EMAIL_ADDRESS = 3;
    private static final int EMAIL_DISPLAY_NAME = 6;
    private static final int EMAIL_TYPE = 4;
    private static final int FAMILY_NAME = 5;
    private static final int GIVEN_NAME = 4;
    private static final int ID = 0;
    private static final int MIDDLE_NAME = 7;
    private static final int MIMETYPE = 2;
    private static final int NAME = 3;
    private static final int NUMBER = 3;
    private static final int NUMBER_TYPE = 4;
    private static final String PROJECTION[];
    private static final String PROJECTION_ALL_DATA[];
    private static final String SELECTION = "deleted=0 AND mimetype IN ('vnd.android.cursor.item/name','vnd.android.cursor.item/phone_v2','vnd.android.cursor.item/email_v2')";
    private static final String SELECTION_ID_IN = "_id IN ";
    private static final String SELECTION_MOVE = "_id=? AND mimetype NOT IN ('vnd.android.cursor.item/name','vnd.android.cursor.item/phone_v2','vnd.android.cursor.item/email_v2','vnd.android.cursor.item/photo')";
    private static final String SELECTION_NO_MATCH_DATA = "mimetype NOT IN ('vnd.android.cursor.item/name','vnd.android.cursor.item/phone_v2','vnd.android.cursor.item/email_v2','vnd.android.cursor.item/photo')";
    private static final String SELECTION_PHOTO = "mimetype=?";
    private static final String SELECTION_WITH_GROUP = "deleted=0 AND account_type=? AND mimetype='vnd.android.cursor.item/group_membership'";
    private static final String TAG = ContactModelImpl.class.getSimpleName();
    private AggregationModel mAggregationModel;
    private Context mContext;

    static 
    {
        String as[] = new String[10];
        as[0] = "_id";
        as[1] = "contact_id";
        as[2] = "mimetype";
        as[3] = "data1";
        as[4] = "data2";
        as[5] = "data3";
        as[6] = "data4";
        as[7] = "data5";
        as[8] = "account_name";
        as[9] = "account_type";
        PROJECTION = as;
        String as1[] = new String[19];
        as1[0] = "mimetype";
        as1[1] = "is_primary";
        as1[2] = "is_super_primary";
        as1[3] = "group_sourceid";
        as1[4] = "data1";
        as1[5] = "data2";
        as1[6] = "data3";
        as1[7] = "data4";
        as1[8] = "data5";
        as1[9] = "data6";
        as1[10] = "data7";
        as1[11] = "data8";
        as1[12] = "data9";
        as1[13] = "data10";
        as1[14] = "data11";
        as1[15] = "data12";
        as1[16] = "data13";
        as1[17] = "data14";
        as1[18] = "data15";
        PROJECTION_ALL_DATA = as1;
        String as2[] = new String[15];
        as2[0] = "data1";
        as2[1] = "data2";
        as2[2] = "data3";
        as2[3] = "data4";
        as2[4] = "data5";
        as2[5] = "data6";
        as2[6] = "data7";
        as2[7] = "data8";
        as2[8] = "data9";
        as2[9] = "data10";
        as2[10] = "data11";
        as2[11] = "data12";
        as2[12] = "data13";
        as2[13] = "data14";
        as2[14] = "data15";
        DATA_KEYS = as2;
    }
}
