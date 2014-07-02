// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp.purge;

import android.content.*;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import se.tactel.contactcleanapp.model.*;

// Referenced classes of package se.tactel.contactcleanapp.cleanapp.purge:
//            Purger

public class PurgerImpl
    implements Purger
{

    public PurgerImpl(Context context, ContactModelAPI contactmodelapi)
    {
        mContext = context;
        mContactModel = contactmodelapi;
        mAccountTypes = AccountTypes.all();
    }

    private boolean isDeleteable(Contact contact)
    {
        String s = contact.getData().getAccountType();
        
        if(TextUtils.isEmpty(s)) {
        	return true;
        }
 //        if(!mAccountTypes.contains(s)) goto _L2; else goto _L1
//_L1:
//        if(contact.getData().isGooglePlusContact()) goto _L4; else goto _L3
//_L3:
//        boolean flag = true;
//_L5:
//        return flag;
//_L4:
//        Log.d(TAG, "Contact is google plus contact");
//_L6:
//        flag = false;
//        if(true) goto _L5; else goto _L2
//_L2:
//        Log.d(TAG, (new StringBuilder(String.valueOf(s))).append(" is not a deleteable account type").toString());
//          goto _L6
        boolean flag = true;
        if(!mAccountTypes.contains(s)) {
        	Log.d(TAG, (new StringBuilder(String.valueOf(s))).append(" is not a deleteable account type").toString());
        	flag = false;
        } else {
        	  if(contact.getData().isGooglePlusContact()) {
        		  Log.d(TAG, "Contact is google plus contact");
        		  flag = false;
        	  }
        }
        
        return flag;
    }

    private void onError()
    {
        if(mListener != null)
            mListener.onError();
    }

    public void purge(List list)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        ArrayList arraylist2;
        ArrayList arraylist3;
        int i;
        int j;
        Iterator iterator;
        if(mListener != null)
            mListener.onStarted();
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        arraylist3 = new ArrayList();
        i = 0;
        j = list.size();
        iterator = list.iterator();
//_L5:
//        if(iterator.hasNext()) goto _L2; else goto _L1
//_L1:
//        if(arraylist2.size() > 0)
//        {
//            android.content.ContentProviderResult acontentproviderresult3[] = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist2);
//            Log.d(TAG, (new StringBuilder("purge() performed ")).append(acontentproviderresult3.length).append(" move operations").toString());
//        }
//        if(arraylist3.size() > 0)
//        {
//            android.content.ContentProviderResult acontentproviderresult2[] = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist3);
//            Log.d(TAG, (new StringBuilder("purge() performed ")).append(acontentproviderresult2.length).append(" delete operations").toString());
//        }
//        if(mListener != null)
//            mListener.onFinished();
//_L11:
//        return;
//_L2:
//        Contact contact = (Contact)iterator.next();
//        if(arraylist1.contains(contact.getData()) && isDeleteable(contact)) goto _L4; else goto _L3
//_L3:
//        arraylist1.add(contact.getData());
//        arraylist.add(contact);
//_L7:
//        i++;
//        if(mListener != null)
//            mListener.onProgress((i * 100) / j);
//          goto _L5
//_L4:
//        Log.d(TAG, "purger found duplicate");
//        Contact contact1 = (Contact)arraylist.get(arraylist1.indexOf(contact.getData()));
//        android.content.ContentProviderResult acontentproviderresult[];
//        ArrayList arraylist4;
//        android.content.ContentProviderResult acontentproviderresult1[];
//        ArrayList arraylist5;
//        try
//        {
//            mContactModel.move(contact, contact1, arraylist2);
//        }
//        catch(RemoteException remoteexception1)
//        {
//            remoteexception1.printStackTrace();
//        }
//        catch(OperationApplicationException operationapplicationexception)
//        {
//            operationapplicationexception.printStackTrace();
//        }
//        mContactModel.delete(contact, arraylist3);
//        if(mListener != null)
//            mListener.onPurged();
//        if(arraylist2.size() + arraylist3.size() <= 400) goto _L7; else goto _L6
//_L6:
//        if(arraylist2.size() <= 0) goto _L9; else goto _L8
//_L8:
//        acontentproviderresult1 = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist2);
//        arraylist5 = new ArrayList();
//        Log.d(TAG, (new StringBuilder("purge() performed ")).append(acontentproviderresult1.length).append(" move operations").toString());
//        arraylist2 = arraylist5;
//_L9:
//        if(arraylist3.size() <= 0) goto _L7; else goto _L10
//_L10:
//        acontentproviderresult = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist3);
//        arraylist4 = new ArrayList();
//        Log.d(TAG, (new StringBuilder("purge() performed ")).append(acontentproviderresult.length).append(" delete operations").toString());
//        arraylist3 = arraylist4;
//          goto _L7
//        RemoteException remoteexception;
//        remoteexception;
//_L14:
//        remoteexception.printStackTrace();
//        onError();
//          goto _L11
//        OperationApplicationException operationapplicationexception1;
//        operationapplicationexception1;
//_L13:
//        operationapplicationexception1.printStackTrace();
//        onError();
//          goto _L11
//        UnsupportedOperationException unsupportedoperationexception;
//        unsupportedoperationexception;
//_L12:
//        unsupportedoperationexception.printStackTrace();
//        onError();
//          goto _L11
//        RemoteException remoteexception2;
//        remoteexception2;
//        remoteexception2.printStackTrace();
//        onError();
//          goto _L11
//        OperationApplicationException operationapplicationexception2;
//        operationapplicationexception2;
//        operationapplicationexception2.printStackTrace();
//        onError();
//          goto _L11
//        UnsupportedOperationException unsupportedoperationexception1;
//        unsupportedoperationexception1;
//        unsupportedoperationexception1.printStackTrace();
//        onError();
//          goto _L11
//        unsupportedoperationexception;
//          goto _L12
//        unsupportedoperationexception;
//          goto _L12
//        operationapplicationexception1;
//          goto _L13
//        operationapplicationexception1;
//          goto _L13
//        remoteexception;
//          goto _L14
//        remoteexception;
//          goto _L14
        
		while (iterator.hasNext()) {
			Contact contact = (Contact) iterator.next();
			if (arraylist1.contains(contact.getData()) && isDeleteable(contact)) {// goto
																					// _L4;
																					// else
																					// goto
																					// _L3
				Log.d(TAG, "purger found duplicate");
				Contact contact1 = (Contact) arraylist.get(arraylist1
						.indexOf(contact.getData()));
				android.content.ContentProviderResult acontentproviderresult[];
				ArrayList arraylist4;
				android.content.ContentProviderResult acontentproviderresult1[];
				ArrayList arraylist5;
				try {
					mContactModel.move(contact, contact1, arraylist2);
				} catch (RemoteException remoteexception1) {
					remoteexception1.printStackTrace();
				} catch (OperationApplicationException operationapplicationexception) {
					operationapplicationexception.printStackTrace();
				}
				mContactModel.delete(contact, arraylist3);
				if (mListener != null)
					mListener.onPurged();
				if (arraylist2.size() + arraylist3.size() <= 400) {// goto _L7;
																	// else goto
																	// _L6
					i++;
					if (mListener != null) {
						mListener.onProgress((i * 100) / j);
					}
				} else {
					if (arraylist2.size() <= 0) {// goto _L9; else goto _L8
						if (arraylist3.size() <= 0) {// goto _L7; else goto _L10
							i++;
							if (mListener != null)
								mListener.onProgress((i * 100) / j);
						} else {
							try {
								acontentproviderresult = mContext
										.getContentResolver().applyBatch(
												"com.android.contacts",
												arraylist3);

								arraylist4 = new ArrayList();
								Log.d(TAG,
										(new StringBuilder("purge() performed "))
												.append(acontentproviderresult.length)
												.append(" delete operations")
												.toString());
								arraylist3 = arraylist4;
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (OperationApplicationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else {
						try {
							acontentproviderresult1 = mContext
									.getContentResolver().applyBatch(
											"com.android.contacts", arraylist2);

							arraylist5 = new ArrayList();
							Log.d(TAG,
									(new StringBuilder("purge() performed "))
											.append(acontentproviderresult1.length)
											.append(" move operations")
											.toString());
							arraylist2 = arraylist5;
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OperationApplicationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
            
          } else {
            arraylist1.add(contact.getData());
            arraylist.add(contact);
//    _L7:
            i++;
            if(mListener != null)
                mListener.onProgress((i * 100) / j);
//              goto _L5
          }
        }
		
		try {		
      if(arraylist2.size() > 0)
      {
          android.content.ContentProviderResult acontentproviderresult3[];

			acontentproviderresult3 = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist2);

          Log.d(TAG, (new StringBuilder("purge() performed ")).append(acontentproviderresult3.length).append(" move operations").toString());
      }
      if(arraylist3.size() > 0)
      {
          android.content.ContentProviderResult acontentproviderresult2[] = mContext.getContentResolver().applyBatch("com.android.contacts", arraylist3);
          Log.d(TAG, (new StringBuilder("purge() performed ")).append(acontentproviderresult2.length).append(" delete operations").toString());
      }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      if(mListener != null)
          mListener.onFinished();
    }

    public void purgeAll()
    {
        purge(mContactModel.getContactsWithMatchInfo());
    }

    public void setPurgeListener(Purger.PurgeListener purgelistener)
    {
        mListener = purgelistener;
    }

    private static final String TAG = PurgerImpl.class.getSimpleName();
    private HashSet mAccountTypes;
    private ContactModelAPI mContactModel;
    private Context mContext;
    private Purger.PurgeListener mListener;

}
