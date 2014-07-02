// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model.producers;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.util.Log;
import java.util.ArrayList;
import se.tactel.contactcleanapp.model.Name;

// Referenced classes of package se.tactel.contactcleanapp.model.producers:
//            DeletingDataRowProducer

public class StructuredNameProducer extends DeletingDataRowProducer
{

    public StructuredNameProducer(long l, Name name)
    {
        super("vnd.android.cursor.item/name", l);
        mName = name;
    }

    public void produce(ArrayList arraylist)
    {
        super.produce(arraylist);
        if(mName != null)
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("data1", mName.getDisplayName());
            contentvalues.put("data2", mName.getGivenName());
            contentvalues.put("data3", mName.getFamilyName());
            contentvalues.put("data5", mName.getMiddleName());
            contentvalues.put("mimetype", "vnd.android.cursor.item/name");
            contentvalues.put("raw_contact_id", Long.valueOf(mId));
            Log.d(TAG, (new StringBuilder("adding new insert for name = ")).append(mName.getDisplayName()).toString());
            arraylist.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI).withValues(contentvalues).build());
        }
    }

    private static final String TAG = StructuredNameProducer.class.getSimpleName();
    private Name mName;

}
