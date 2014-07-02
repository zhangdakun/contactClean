// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model.producers;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import se.tactel.contactcleanapp.model.PhoneNumber;

// Referenced classes of package se.tactel.contactcleanapp.model.producers:
//            DeletingDataRowProducer

public class PhoneNumberProducer extends DeletingDataRowProducer {

	public PhoneNumberProducer(long l, ArrayList arraylist) {
		super("vnd.android.cursor.item/phone_v2", l);
		mNumbers = arraylist;
	}

	public void produce(ArrayList arraylist) {
		super.produce(arraylist);
		// if(mNumbers == null || mNumbers.size() <= 0) goto _L2; else goto _L1
		// _L1:
		// ContentValues contentvalues;
		// Iterator iterator;
		// contentvalues = new ContentValues();
		// iterator = mNumbers.iterator();
		// _L5:
		// if(iterator.hasNext()) goto _L3; else goto _L2
		// _L2:
		// return;
		// _L3:
		// PhoneNumber phonenumber = (PhoneNumber)iterator.next();
		// contentvalues.clear();
		// contentvalues.put("data1", phonenumber.getNumber());
		// contentvalues.put("data2", Integer.valueOf(phonenumber.getType()));
		// contentvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
		// contentvalues.put("raw_contact_id", Long.valueOf(mId));
		// Log.d(TAG, (new
		// StringBuilder("adding new insert of number = ")).append(phonenumber.getNumber()).toString());
		// arraylist.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI).withValues(contentvalues).build());
		// if(true) goto _L5; else goto _L4
		// _L4:

		if (mNumbers == null || mNumbers.size() <= 0) {
			return;
		}

		ContentValues contentvalues;
		Iterator iterator;
		contentvalues = new ContentValues();
		iterator = mNumbers.iterator();

		while (iterator.hasNext()) {
			PhoneNumber phonenumber = (PhoneNumber) iterator.next();
			contentvalues.clear();
			contentvalues.put("data1", phonenumber.getNumber());
			contentvalues.put("data2", Integer.valueOf(phonenumber.getType()));
			contentvalues.put("mimetype", "vnd.android.cursor.item/phone_v2");
			contentvalues.put("raw_contact_id", Long.valueOf(mId));
			Log.d(TAG, (new StringBuilder("adding new insert of number = "))
					.append(phonenumber.getNumber()).toString());
			arraylist.add(ContentProviderOperation
					.newInsert(
							android.provider.ContactsContract.Data.CONTENT_URI)
					.withValues(contentvalues).build());
		}

	}

	private static final String TAG = PhoneNumberProducer.class.getSimpleName();
	private ArrayList mNumbers;

}
